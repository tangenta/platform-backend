package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.mybatis.Comment;
import com.tangenta.repositories.CommentRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
@Profile("dev-test")
public class TestCommentRepository implements CommentRepository {
    private static List<Comment> allComments = new LinkedList<Comment>() {{
        add(new Comment(1L, 1L, 1L, "I am the king of dance yo", new Date()));
        add(new Comment(2L, 2L, 1L, "I don't think so", new Date()));
        add(new Comment(3L, 3L, 1L, "What are you talking about?", new Date()));
    }};
    private static Long currentMaxId = 3L;

    @Override
    public void addComment(Long studentId, Long postId, String content, Date creationDate) {
        allComments.add(new Comment(currentMaxId++, studentId, postId, content, creationDate));
    }
}
