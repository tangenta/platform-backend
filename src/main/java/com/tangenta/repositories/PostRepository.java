package com.tangenta.repositories;

import com.tangenta.data.pojo.mybatis.MPost;

import java.util.List;

public interface PostRepository {
    List<MPost> getAllPosts();
    void createPost(MPost partialMPost);
}
