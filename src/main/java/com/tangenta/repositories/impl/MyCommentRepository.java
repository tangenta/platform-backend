package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.CommentMapper;
import com.tangenta.repositories.CommentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Profile("dev")
public class MyCommentRepository implements CommentRepository {
    private CommentMapper commentMapper;

    public MyCommentRepository(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public void addComment(Long studentId, Long postId, String content, Date creationDate) {
        commentMapper.addComment(studentId, postId, content, creationDate);
    }
}
