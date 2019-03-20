package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.CommentMapper;
import com.tangenta.data.pojo.mybatis.MComment;
import com.tangenta.repositories.CommentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<MComment> showComments(Long postId) {
        return commentMapper.findByPostId(postId);
    }

    @Override
    public MComment findById(Long commentId) {
        return commentMapper.findByCommentId(commentId);
    }

    @Override
    public List<MComment> showUserComments(Long studentId) {
        return commentMapper.findByStudentId(studentId);
    }

    @Override
    public void deleteById(Long commentId) {
        commentMapper.deleteComment(commentId);
    }
}
