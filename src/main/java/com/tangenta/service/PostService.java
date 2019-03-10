package com.tangenta.service;

import com.tangenta.data.pojo.Post;
import com.tangenta.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostService {
    private ValidationService validationService;
    private PostRepository postRepository;

    public PostService(ValidationService validationService, PostRepository postRepository) {
        this.validationService = validationService;
        this.postRepository = postRepository;
    }


    public void createPost(Long studentId, String title, String content) {
        String trimTitle = title.trim();
        String trimContent = content.trim();
        validationService.ensureNonEmptyString(trimTitle, "标题");
        validationService.ensureNonEmptyString(trimContent, "内容");

        postRepository.createPost(new Post(-1L, new Date(), trimContent,
                0L, 0L, studentId, trimTitle));
    }
}
