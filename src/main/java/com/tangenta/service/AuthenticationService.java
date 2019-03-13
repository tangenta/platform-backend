package com.tangenta.service;

import com.tangenta.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class AuthenticationService {
    private static final int CLEAR_SESSION_RATE_IN_MINUTES = 3;
    private static final int SESSION_MAX_LIFE_IN_MINUTES = 5;

    private class TokenTimeTuple {
        String token;
        long time;

        TokenTimeTuple(String token, long time) {
            this.token = token;
            this.time = time;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    private static final Map<Long, TokenTimeTuple> idTokensMap = new HashMap<>();
    private static final SortedMap<Long, Long> timeIdMap = new TreeMap<>();
    private static final Object lock = new Object();

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    static {
        executorService.scheduleAtFixedRate(() -> {
            long currentTime = System.currentTimeMillis();
            logger.info("start checking for expired user token...");
            synchronized (lock) {
                while (!timeIdMap.isEmpty() && Duration.ofMillis(currentTime - timeIdMap.firstKey())
                        .compareTo(Duration.ofMinutes(SESSION_MAX_LIFE_IN_MINUTES)) > 0) {
                    long id = timeIdMap.get(timeIdMap.firstKey());
                    timeIdMap.remove(timeIdMap.firstKey());
                    logger.info("revoke token for student id = {}", id);
                    idTokensMap.remove(id);
                }
            }
            logger.info("finish checking.");
        }, CLEAR_SESSION_RATE_IN_MINUTES, CLEAR_SESSION_RATE_IN_MINUTES, TimeUnit.MINUTES);
    }

    public String allocateToken(Long studentId) {
        String newToken = UUID.randomUUID().toString();
        synchronized (lock) {
            long creationTime = System.currentTimeMillis();
            idTokensMap.put(studentId, new TokenTimeTuple(newToken, creationTime));
            timeIdMap.put(creationTime, studentId);
        }
        return newToken;
    }

    public String reallocateToken(Long studentId) {
        synchronized (lock) {
            long time = idTokensMap.get(studentId).time;
            idTokensMap.remove(studentId);
            timeIdMap.remove(time);
        }
        return allocateToken(studentId);
    }

    public boolean authenticate(Long studentId, String token) {
        TokenTimeTuple tuple;
        synchronized (lock) {
            tuple = idTokensMap.get(studentId);
        }
        if (tuple == null) return false;
        return tuple.token.equals(token);
    }

    public boolean hasLoggedIn(Long studentId) {
        synchronized (lock) {
            boolean hasLoggedIn = idTokensMap.containsKey(studentId);
            if (hasLoggedIn) refreshTokenTime(studentId);
            return hasLoggedIn;
        }
    }

    private void refreshTokenTime(Long studentId) {
        synchronized (lock) {
            long newAccessTime = System.currentTimeMillis();
            TokenTimeTuple tup = idTokensMap.get(studentId);
            idTokensMap.remove(studentId);
            idTokensMap.put(studentId, new TokenTimeTuple(tup.token, newAccessTime));
            timeIdMap.remove(tup.time);
            timeIdMap.put(newAccessTime, studentId);
            logger.info("update lastAccessTime from {} to {}", new Date(tup.time).toString(),
                    new Date(newAccessTime).toString());
        }
    }

    public boolean logout(Long studentId, String token) {
        synchronized (lock) {
            if (!idTokensMap.containsKey(studentId) || !idTokensMap.get(studentId).token.equals(token))
                return false;
            Long creationTime = idTokensMap.get(studentId).time;
            idTokensMap.remove(studentId);
            timeIdMap.remove(creationTime);
        }
        return true;
    }

    public void ensureLoggedIn(Long studentId) {
        if (!hasLoggedIn(studentId)) throw new BusinessException("用户尚未登录");
        // TODO: authenticate
    }



}
