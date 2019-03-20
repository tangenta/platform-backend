package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.User;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Repository
@Profile("dev-test")
public class TestUserRepository implements UserRepository {
    static private List<User> allUsers = new LinkedList<User>() {{
        add(new User(1L, "mike", "pass",
                "mike@email.com", new Date().toString()));
        add(new User(2L, "sara", "word",
                "sara@email.com", new Date().toString()));
        add(new User(3L, "jack", "password",
                "jack@email.com", new Date().toString()));
    }};
    static private Long id = 4L;

    @Override
    public List<User> getAllUsers() {
//        throw new BusinessException("test");
        return allUsers;
    }

    @Override
    public User findById(Long studentId) {
        return allUsers.stream()
                .filter(user -> user.getStudentId().equals(studentId))
                .findFirst().orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        for(User user: allUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        for(User user: allUsers) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void createUser(User partialUser) {
        allUsers.add(
                new User(id++, partialUser.getUsername(), partialUser.getPassword(),
                        partialUser.getEmail(), new Date().toString())
        );
    }

    @Override
    public void updateUser(User user) {
        Iterator<User> i = allUsers.iterator();
        User newUser = null;
        while (i.hasNext()) {
            User u = i.next();
            if (u.getStudentId().equals(user.getStudentId())) {
                newUser = new User(user.getStudentId(), user.getUsername(), user.getPassword(),
                        user.getEmail(), u.getCreationDate());
                i.remove();
                break;
            }
        }
        if (newUser == null) throw new BusinessException("用户不存在");
        allUsers.add(newUser);
    }
}
