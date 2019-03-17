package com.tangenta.repositories;

import com.tangenta.data.pojo.mybatis.MComment;

import java.util.Date;
import java.util.List;

public interface CommentRepository {
    void addComment(Long studentId, Long postId, String content, Date creationDate);
    List<MComment> showComments(Long postId);
}
