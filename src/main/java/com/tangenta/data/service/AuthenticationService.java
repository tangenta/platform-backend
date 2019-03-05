package com.tangenta.data.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class AuthenticationService {
    private static ConcurrentMap<Long, String> tokens = new ConcurrentHashMap<>();

    public String allocateToken(Long forStudentId) {
        String newToken = UUID.randomUUID().toString();
        tokens.put(forStudentId, newToken);
        return newToken;
    }

    public boolean authenticate(Long studentId, String token) {
        String studentToken = tokens.get(studentId);
        if (studentToken == null) return false;
        return studentToken.equals(token);
    }

    public boolean hasLoggedIn(Long studentId) {
        return tokens.containsKey(studentId);
    }

    public boolean logout(Long studentId, String token) {
        if (!tokens.containsKey(studentId) || !tokens.get(studentId).equals(token)) return false;
        tokens.remove(studentId);
        return true;
    }

    public void clear() {
        tokens.clear();
    }
}
