package com.tangenta.service;

import com.tangenta.data.pojo.User;
import com.tangenta.data.pojo.graphql.Post;
import com.tangenta.data.pojo.mybatis.MPost;
import com.tangenta.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityService {
    private AuthenticationService authenticationService;

    public UserSecurityService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public User filterUserByToken(User user, String token) {
        boolean isAuthenticated = authenticationService.authenticate(user.getStudentId(), token);
        if (isAuthenticated) return user;
        else {
            return new User(user.getStudentId(), user.getUsername(), "", "", "");
        }
    }

    public List<User> filterUsersByToken(List<User> users, String token) {
        List<User> resultUsers = new LinkedList<>();
        users.forEach(user -> resultUsers.add(filterUserByToken(user, token)));
        return resultUsers;
    }

    public Post filterUserInPost(Post post, String token) {
        return new Post(post.getPostId(), post.getPublishTime(), post.getContent(),
                post.getViewNumber(), post.getReplyNumber(),
                filterUserByToken(post.getUser(), token),
                post.getTitle());
    }

    public List<Post> filterUsersInPost(List<Post> allPosts, String token) {
        return allPosts.stream()       // filter User's private fields
                .map(p -> new Post(p.getPostId(), p.getPublishTime(), p.getContent(),
                        p.getViewNumber(), p.getReplyNumber(),
                        filterUserByToken(p.getUser(), token),
                        p.getTitle()))
                .collect(Collectors.toList());
    }
}
