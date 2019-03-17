package com.tangenta.service;

import com.tangenta.data.pojo.graphql.Comment;
import com.tangenta.repositories.CommentRepository;
import com.tangenta.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }


    public void addComment(Long studentId, Long postId, String content) {
        commentRepository.addComment(studentId, postId, content, new Date());
    }

    public List<Comment> showComments(Long postId) {
        return commentRepository.showComments(postId).stream()
                .map(mc -> new Comment(mc.getPostId(),
                        userRepository.findById(mc.getStudentId()).getUsername(),
                        mc.getContent(), mc.getCreationTime()))
                .sorted(Comparator.comparing(Comment::getCreationTime))
                .collect(Collectors.toList());
    }
}
