package com.tangenta.service;

import com.tangenta.exceptions.BusinessException;
import com.tangenta.utils.Utils;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.concurrent.ThreadSafe;
import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class AuthenticationService {
    @Value("${session-clear-delay-minutes}")
    private int CLEAR_SESSION_RATE_IN_MINUTES;

    @Value("${session-max-life-minutes}")
    private int SESSION_MAX_LIFE_IN_MINUTES;


    private static Id_Token_Time authPack = new Id_Token_Time();

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @PostConstruct
    void launchScheduleThread() {
        executorService.scheduleWithFixedDelay(() -> {
            long currentTime = System.currentTimeMillis();
            logger.info("start checking for expired user token...");

            while (!authPack.isEmpty() && Duration.ofMillis(currentTime - authPack.oldestAccessTime())
                    .compareTo(Duration.ofMinutes(SESSION_MAX_LIFE_IN_MINUTES)) > 0) {
                Long id = authPack.removeOldest();
                logger.info("revoke token for student id = {}", id);
            }

            logger.info("finish checking.");
        }, CLEAR_SESSION_RATE_IN_MINUTES, CLEAR_SESSION_RATE_IN_MINUTES, TimeUnit.MINUTES);
    }

    public String allocateToken(Long studentId) {
        authPack.removeById(studentId);
        String newToken = UUID.randomUUID().toString();
        long creationTime = System.currentTimeMillis();
        authPack.insert(studentId, creationTime, newToken);
        return newToken;
    }

    public boolean authenticate(Long studentId, String token) {
        return token.equals(authPack.getById(studentId));
    }

    public boolean hasLoggedIn(Long studentId) {
        refreshTokenTime(studentId);
        return authPack.containsId(studentId);
    }

    public boolean logout(Long studentId, String token) {
        authPack.removeById(studentId);
        return authenticate(studentId, token);
    }

    public void ensureLoggedIn(Long studentId) {
        if (!hasLoggedIn(studentId)) throw new BusinessException("用户尚未登录");
    }

    public void ensureAuthenticated(Long studentId, DataFetchingEnvironment env) {
        BusinessException e = new BusinessException("用户未认证");
        String authToken = Utils.getAuthToken(env).orElseThrow(() -> e);
        if (!authenticate(studentId, authToken)) throw e;
    }

    private void refreshTokenTime(Long studentId) {
        long newAccessTime = System.currentTimeMillis();
        Long oldAccessTime = authPack.updateTimeById(studentId, newAccessTime);
        if (oldAccessTime == null) return;
        logger.info("update lastAccessTime from {} to {}", new Date(oldAccessTime).toString(),
                new Date(newAccessTime).toString());
    }

    @ThreadSafe private static class Id_Token_Time {
        private final Map<Long, TokenTimeTuple> idTokensMap = new HashMap<>();
        private final SortedMap<Long, Long> timeIdMap = new TreeMap<>();
        private final ReentrantReadWriteLock rwk = new ReentrantReadWriteLock();
        private static <L extends Lock, T> T wrapWithLock(L lock, Supplier<T> criticalStmt) {
            lock.lock();
            T ret = criticalStmt.get();
            lock.unlock();
            return ret;
        }

        private class TokenTimeTuple {
            String token;
            long time;

            TokenTimeTuple(String token, long time) {
                this.token = token;
                this.time = time;
            }
        }

        void insert(Long id, Long time, String token) {
            wrapWithLock(rwk.writeLock(), () -> {
                idTokensMap.put(id, new TokenTimeTuple(token, time));
                timeIdMap.put(time, id);
                return null;
            });
        }

        Long removeOldest() {
            return wrapWithLock(rwk.writeLock(), () -> {
                Long id = timeIdMap.get(timeIdMap.firstKey());
                timeIdMap.remove(timeIdMap.firstKey());
                idTokensMap.remove(id);
                return id;
            });
        }

        void removeById(Long id) {
            wrapWithLock(rwk.writeLock(), () -> {
                TokenTimeTuple tuple = idTokensMap.get(id);
                if (tuple == null) return null;
                idTokensMap.remove(id);
                timeIdMap.remove(tuple.time);
                return null;
            });
        }

        boolean isEmpty() {
            return wrapWithLock(rwk.readLock(), () ->
                    idTokensMap.isEmpty() && timeIdMap.isEmpty()
            );
        }

        boolean containsId(Long id) {
            return wrapWithLock(rwk.readLock(), () -> idTokensMap.containsKey(id));
        }

        Long oldestAccessTime() {
            return wrapWithLock(rwk.readLock(),
                    timeIdMap::firstKey
            );
        }

        String getById(Long id) {
            return wrapWithLock(rwk.readLock(), () -> {
                TokenTimeTuple tuple = idTokensMap.get(id);
                if (tuple == null) return "";
                return tuple.token;
            });
        }

        Long updateTimeById(Long id, Long time) {
            return wrapWithLock(rwk.writeLock(), () -> {
                TokenTimeTuple old = idTokensMap.get(id);
                if (old == null) return null;
                timeIdMap.remove(old.time);
                timeIdMap.put(time, id);
                idTokensMap.remove(id);
                idTokensMap.put(id, new TokenTimeTuple(old.token, time));
                return old.time;
            });
        }
    }
}
