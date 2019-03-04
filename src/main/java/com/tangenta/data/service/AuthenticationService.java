package com.tangenta.data.service;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthenticationService {
    private static ConcurrentHashMap<Long, String> tokens = new ConcurrentHashMap<>();

    public String allocateToken(Long forStudentId) {
        String newToken = UUID.randomUUID().toString();
        tokens.put(forStudentId, newToken);
        return newToken;
    }

    public boolean authenticate(Long studentId, String token) {
        if (token == null) return false;
        return tokens.get(studentId).equals(token);
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
