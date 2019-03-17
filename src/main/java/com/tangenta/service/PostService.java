package com.tangenta.service;

import com.tangenta.data.pojo.User;
import com.tangenta.data.pojo.graphql.Post;
import com.tangenta.data.pojo.graphql.SortMethod;
import com.tangenta.data.pojo.mybatis.MPost;
import com.tangenta.repositories.PostRepository;
import com.tangenta.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private ValidationService validationService;
    private UserRepository userRepository;
    private PostRepository postRepository;

    public PostService(ValidationService validationService, UserRepository userRepository, PostRepository postRepository) {
        this.validationService = validationService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Post> allPost(int numbers, Long from, SortMethod sortBy) {
        List<MPost> allMPosts = postRepository.getAllPosts();
        if (sortBy == null) sortBy = SortMethod.Time;

        Comparator<MPost> comparator;
        switch (sortBy) {
            case ReplyNumber: comparator = Comparator.comparingLong(MPost::getReplyNumber).thenComparing(MPost::getPostId); break;
            case ViewNumber: comparator = Comparator.comparingLong(MPost::getViewNumber).thenComparing(MPost::getPostId); break;
            case Time:
            default: comparator = Comparator.comparing(MPost::getPublishTime).thenComparing(MPost::getPostId);
        }
        allMPosts.sort(comparator);
        int index = 0;
        for (MPost mPost : allMPosts) {
            if (mPost.getPostId().equals(from)) break;
            index++;
        }

        return allMPosts.stream()
                .skip(from.equals(0L) ? 0 : index + 1)
                .limit(numbers)
                .map(p -> new Post(p.getPostId(), p.getPublishTime(), p.getContent(),
                        p.getViewNumber(), p.getReplyNumber(),
                        userRepository.findById(p.getStudentId()),
                        p.getTitle()))
                .collect(Collectors.toList());
    }

    public Post viewPost(Long postId) {
        MPost p = postRepository.findById(postId);
        User user = userRepository.findById(p.getStudentId());
        return new Post(p.getPostId(), p.getPublishTime(), p.getContent(), p.getViewNumber(),
                p.getReplyNumber(), user, p.getTitle());
    }


    public void createPost(Long studentId, String title, String content) {
        String trimTitle = title.trim();
        String trimContent = content.trim();
        validationService.ensureNonEmptyString(trimTitle, "标题");
        validationService.ensureNonEmptyString(trimContent, "内容");

        postRepository.create(new MPost(-1L, new Date(), trimContent,
                0L, 0L, studentId, trimTitle));
    }

    public void deletePost(Long postId) {
        validationService.ensurePostExistence(postId);
        postRepository.deleteById(postId);
    }

    public void updatePost(Long postId, String title, String content) {
        postRepository.update(postId, title, content);
    }
}
