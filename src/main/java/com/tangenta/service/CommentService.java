package com.tangenta.service;

import com.tangenta.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public void addComment(Long studentId, Long postId, String content) {
        commentRepository.addComment(studentId, postId, content, new Date());
    }
}
