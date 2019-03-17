package com.tangenta.repositories;

import java.util.Date;

public interface CommentRepository {
    void addComment(Long studentId, Long postId, String content, Date creationDate);
}
