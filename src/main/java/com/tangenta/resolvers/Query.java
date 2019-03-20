package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tangenta.data.pojo.graphql.*;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.service.*;
import com.tangenta.repositories.UserRepository;
import com.tangenta.data.pojo.User;
import com.tangenta.utils.Utils;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Query implements GraphQLQueryResolver {
    private static Logger logger = LoggerFactory.getLogger(Query.class);

    private final UserRepository userRepository;

    private final SecurityService securityService;
    private final QuestionService questionService;
    private final AuthenticationService authenticationService;
    private final StatisticService statisticService;
    private final PostService postService;
    private final CommentService commentService;
    private final FollowService followService;
    private final ValidationService validationService;

    public Query(UserRepository userRepository,
                 SecurityService securityService, QuestionService questionService,
                 AuthenticationService authenticationService, StatisticService statisticService,
                 PostService postService, CommentService commentService, FollowService followService, ValidationService validationService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.questionService = questionService;
        this.authenticationService = authenticationService;
        this.statisticService = statisticService;
        this.postService = postService;
        this.commentService = commentService;
        this.followService = followService;
        this.validationService = validationService;
    }

    public List<User> users(DataFetchingEnvironment env) {
        String authToken = Utils.getAuthToken(env).orElse("");
        List<User> rawAllUsers = userRepository.getAllUsers();
        return securityService.filterUsersByToken(rawAllUsers, authToken);
    }

    public User user(String username, DataFetchingEnvironment env) {
        validationService.ensureUserExistence(username);

        String authToken = Utils.getAuthToken(env).orElse("");
        User rawUser = userRepository.findByUsername(username);
        return securityService.filterUserByToken(rawUser, authToken);
    }

    public User userById(Long studentId, DataFetchingEnvironment env) {
        authenticationService.ensureLoggedIn(studentId);
        validationService.ensureUserExistence(studentId);

        String authToken = Utils.getAuthToken(env).orElse("");
        User rawUser = userRepository.findById(studentId);
        return securityService.filterUserByToken(rawUser, authToken);
    }

    public Question randomQuestion(List<QuestionClassification> classifications, List<QuestionType> types) {
        return questionService.randomQuestion(classifications, types);
    }

    public List<Post> showPosts(int numbers, Long from, SortMethod sortBy, DataFetchingEnvironment env) {
        List<Post> allMPosts = postService.allPost(numbers, from, sortBy);

        String token = Utils.getAuthToken(env).orElse("");

        return allMPosts.stream()
                .map(p -> new Post(p.getPostId(), p.getPublishTime(), p.getContent(),
                        p.getViewNumber(), p.getReplyNumber(),
                        securityService.filterUserByToken(p.getUser(), token),
                        p.getTitle()))
                .collect(Collectors.toList());
    }

    public Post viewPost(Long postId, DataFetchingEnvironment env) {
        validationService.ensurePostExistence(postId);

        String token = Utils.getAuthToken(env).orElse("");
        Post p = postService.viewPost(postId);
        return new Post(p.getPostId(), p.getPublishTime(), p.getContent(), p.getViewNumber(),
                p.getReplyNumber(), securityService.filterUserByToken(p.getUser(), token), p.getTitle());

    }

    public AnswerStatistic answerStatistic(Long studentId, List<QuestionClassification> classes, List<QuestionType> types) {
        validationService.ensureUserExistence(studentId);
        authenticationService.ensureLoggedIn(studentId);
        return statisticService.showAnswerStatistic(studentId, classes, types);
    }

    public List<Comment> showComments(Long postId) {
        validationService.ensurePostExistence(postId);
        return commentService.showComments(postId);
    }

    public List<User> following(Long studentId) {
        validationService.ensureUserExistence(studentId);
        return followService.following(studentId);
    }

    public List<User> followers(Long studentId) {
        validationService.ensureUserExistence(studentId);
        return followService.followers(studentId);
    }

    public List<AnswerStatistic> answerStatistic(Long studentId, List<QuestionClassification> classes) {
        validationService.ensureUserExistence(studentId);
//        authenticationService.ensureLoggedIn(studentId);

        return statisticService.showAnswerStatisticByClass(studentId, classes);
    }

}
