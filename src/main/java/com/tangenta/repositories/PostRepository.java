package com.tangenta.repositories;

import com.tangenta.data.pojo.Post;

import java.util.List;

public interface PostRepository {
    List<Post> getAllPosts();
}
