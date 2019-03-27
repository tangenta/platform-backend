package com.tangenta.utils;

import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Utils {
    private static Logger logger = LoggerFactory.getLogger(Utils.class);
    public static Optional<String> getAuthToken(DataFetchingEnvironment env) {
        GraphQLContext graphQLContext = env.getContext();
        return graphQLContext.getHttpServletRequest()
                .map(req -> req.getHeader("Authorization"))
                .map(authHeader -> authHeader.replace("Bearer ", ""));
    }

    public static <T, U> T mapToSameIndex(U[] src, U obj, T[] dest) {
        assert src.length == dest.length;
        for (int i = 0; i != src.length; ++i) {
            if (src[i].equals(obj)) {
                return dest[i];
            }
        }
        throw new RuntimeException("impossible to reach here");
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static int compareDateByDateField(Date a, Date b) {
        return dateToLocalDate(a).compareTo(dateToLocalDate(b));
    }

    public static <T> T orElse(T obj, T def) {
        return obj == null ? def : obj;
    }

    public static <T> void substitute(List<T> allItems,  Predicate<T> pred,  Function<T, T> transformer) {
        Iterator<T> iter = allItems.iterator();
        T newObject = null;
        while (iter.hasNext()) {
            T i = iter.next();
            if (pred.test(i)) {
                newObject = transformer.apply(i);
                iter.remove();
                break;
            }
        }
        if (newObject != null) {
            allItems.add(newObject);
        }
    }

    public static <L extends Lock, T> T wrapWithLock(L lock, Supplier<T> criticalStmt) {
        lock.lock();
        T ret = criticalStmt.get();
        lock.unlock();
        return ret;
    }

}
