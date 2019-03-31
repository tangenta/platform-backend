package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.PostMapper;
import com.tangenta.data.pojo.mybatis.MPost;
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
    public List<MPost> getAllPosts() {
        return postMapper.getAllPosts();
    }

    @Override
    public void create(MPost partialMPost) {
        postMapper.createPost(partialMPost);
    }

    @Override
    public MPost findById(Long postId) {
        return postMapper.findById(postId);
    }

    @Override
    public List<MPost> findByUserId(Long studentId) {
        return postMapper.findByStudentId(studentId);
    }

    @Override
    public void deleteById(Long postId) {
        postMapper.deleteById(postId);
    }

    @Override
    public void update(Long postId, String title, String content) {
        postMapper.update(postId, title, content);
    }

    @Override
    public void increaseViewNumber(Long postId) {
        postMapper.increaseViewNumber(postId);
    }

    @Override
    public void increaseReplyNumber(Long postId) {
        postMapper.increaseReplyNumber(postId);
    }

    @Override
    public void decreaseReplyNumber(Long postId) {
        postMapper.decreaseReplyNumber(postId);
    }
}
