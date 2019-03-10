package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.Post;
import com.tangenta.repositories.PostRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
@Profile("dev-test")
public class TestPostRepository implements PostRepository {
    private static List<Post> allPost = new LinkedList<Post>(){{
        add(new Post(1L, new Date(), "It's a nice day",
                1L, 0L, 1L, "title1"));
        add(new Post(2L, new Date(), "It's a good day",
                1L, 0L, 2L, "title2"));
        add(new Post(3L, new Date(), "It's a wonderful day",
                1L, 0L, 3L, "title3"));
    }};
    private static Long postId = 4L;

    @Override
    public List<Post> getAllPosts() {
        return allPost;
    }

    @Override
    public void createPost(Post p) {
        allPost.add(new Post(postId++, new Date(), p.getContent(),
                0L, 0L, p.getStudentId(), p.getTitle()));
    }
}
