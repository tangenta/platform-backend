package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.PostMapper;
import com.tangenta.data.pojo.Post;
import com.tangenta.repositories.PostRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("dev")
public class MyPostRepository implements PostRepository {
    private PostMapper postMapper;

    public MyPostRepository(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Override
    public List<Post> getAllPosts() {
        return postMapper.getAllPosts();
    }
}
