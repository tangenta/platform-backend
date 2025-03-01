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
    private final ValidationService validationService;
    private final PagingService pagingService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostService(ValidationService validationService, PagingService pagingService, UserRepository userRepository, PostRepository postRepository) {
        this.validationService = validationService;
        this.pagingService = pagingService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Post> allPost(int numbers, int from, SortMethod sortBy) {
        List<MPost> allMPosts = postRepository.getAllPosts();
        List<MPost> pagedMPosts = pagingService.paging(sort(allMPosts, sortBy), numbers, from);

        return mapMPostToPost(pagedMPosts);
    }

    public List<Post> allUserPosts(Long studentId, int numbers, int from, SortMethod sortBy) {
        List<MPost> allMPosts = postRepository.findByUserId(studentId);
        List<MPost> pagedMPosts = pagingService.paging(sort(allMPosts, sortBy), numbers, from);

        return mapMPostToPost(pagedMPosts);
    }

    public Post viewPost(Long postId) {
        postRepository.increaseViewNumber(postId);
        return viewPostTraceless(postId);
    }

    public Post viewPostTraceless(Long postId) {
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


    public void increaseReplyNumber(Long postId) {
        postRepository.increaseReplyNumber(postId);
    }

    public void decreaseReplyNumber(Long postId) {
        postRepository.decreaseReplyNumber(postId);
    }

    private static List<MPost> sort(List<MPost> allPosts, SortMethod sortBy) {
        Comparator<MPost> comparator;
        if (sortBy == null) sortBy = SortMethod.Time;
        switch (sortBy) {
            case ReplyNumber: comparator = Comparator.comparingLong(MPost::getReplyNumber).thenComparing(MPost::getPostId); break;
            case ViewNumber: comparator = Comparator.comparingLong(MPost::getViewNumber).thenComparing(MPost::getPostId); break;
            case Time:
            default: comparator = Comparator.comparing(MPost::getPublishTime).thenComparing(MPost::getPostId);
        }
        allPosts.sort(comparator);
        return allPosts;
    }

    private List<Post> mapMPostToPost(List<MPost> mPosts) {
        return mPosts.stream().map(p -> new Post(p.getPostId(), p.getPublishTime(), p.getContent(),
                p.getViewNumber(), p.getReplyNumber(),
                userRepository.findById(p.getStudentId()),
                p.getTitle()))
                .collect(Collectors.toList());
    }

}
