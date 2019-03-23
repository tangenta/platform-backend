package com.tangenta.repositories;

import com.tangenta.data.pojo.mybatis.MPost;

import java.util.List;

public interface PostRepository {
    List<MPost> getAllPosts();
    void create(MPost partialMPost);
    MPost findById(Long postId);
    List<MPost> findByUserId(Long studentId);
    void deleteById(Long postId);
    void update(Long postId, String title, String content);
}
