package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.mybatis.MPost;
import com.tangenta.repositories.PostRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
@Profile("dev-test")
public class TestPostRepository implements PostRepository {
    private static List<MPost> allMPost = new LinkedList<MPost>(){{
        add(new MPost(1L, new Date(), "It's a nice day",
                1L, 0L, 1L, "title1"));
        add(new MPost(2L, new Date(), "It's a good day",
                1L, 0L, 2L, "title2"));
        add(new MPost(3L, new Date(), "It's a wonderful day",
                1L, 0L, 3L, "title3"));
    }};
    private static Long postId = 4L;

    @Override
    public List<MPost> getAllPosts() {
        return allMPost;
    }

    @Override
    public void createPost(MPost p) {
        allMPost.add(new MPost(postId++, new Date(), p.getContent(),
                0L, 0L, p.getStudentId(), p.getTitle()));
    }
}
