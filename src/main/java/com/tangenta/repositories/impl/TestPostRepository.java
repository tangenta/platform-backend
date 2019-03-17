package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.mybatis.MPost;
import com.tangenta.repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Profile("dev-test")
public class TestPostRepository implements PostRepository {
    private static Logger logger = LoggerFactory.getLogger(TestPostRepository.class);
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
    public void create(MPost p) {
        allMPost.add(new MPost(postId++, p.getPublishTime(), p.getContent(),
                0L, 0L, p.getStudentId(), p.getTitle()));
    }

    @Override
    public MPost findById(Long postId) {
        return allMPost.stream()
                .filter(p -> p.getPostId().equals(postId))
                .findFirst().orElse(null);
    }

    @Override
    public void deleteById(Long postId) {
        Iterator<MPost> i = allMPost.iterator();
        while (i.hasNext()) {
            MPost p = i.next();
            if (p.getPostId().equals(postId)) {
                i.remove();
                break;
            }
        }
    }

    @Override
    public void update(Long postId, String title, String content) {
        Iterator<MPost> i = allMPost.iterator();
        MPost newPost = null;
        while (i.hasNext()) {
            MPost p = i.next();
            if (p.getPostId().equals(postId)) {
                newPost = new MPost(postId, p.getPublishTime(), content, p.getViewNumber(),
                        p.getReplyNumber(), p.getStudentId(), title);
                i.remove();
                break;
            }
        }
        Objects.requireNonNull(newPost);
        allMPost.add(newPost);
    }


}
