package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tangenta.data.pojo.graphql.AnswerStatistic;
import com.tangenta.data.pojo.graphql.Post;
import com.tangenta.data.pojo.mybatis.MPost;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.graphql.Question;
import com.tangenta.data.pojo.graphql.SortMethod;
import com.tangenta.service.*;
import com.tangenta.repositories.PostRepository;
import com.tangenta.repositories.UserRepository;
import com.tangenta.data.pojo.User;
import com.tangenta.utils.Utils;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Comparator;
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

    public Query(UserRepository userRepository,
                 SecurityService securityService, QuestionService questionService,
                 AuthenticationService authenticationService, StatisticService statisticService, PostService postService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
        this.questionService = questionService;
        this.authenticationService = authenticationService;
        this.statisticService = statisticService;
        this.postService = postService;
    }

    public List<User> users(DataFetchingEnvironment env) {
        String authToken = Utils.getAuthToken(env).orElse("");
        List<User> rawAllUsers = userRepository.getAllUsers();
        return securityService.filterUsersByToken(rawAllUsers, authToken);
    }

    public User user(String username, DataFetchingEnvironment env) {
        String authToken = Utils.getAuthToken(env).orElse("");
        User rawUser = userRepository.findByUsername(username);
        return securityService.filterUserByToken(rawUser, authToken);
    }


//    public List<MQuestion> questions() {
//        return questionRepository.getAllQuestions();
//    }

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
        String token = Utils.getAuthToken(env).orElse("");
        Post p = postService.viewPost(postId);
        return new Post(p.getPostId(), p.getPublishTime(), p.getContent(), p.getViewNumber(),
                p.getReplyNumber(), securityService.filterUserByToken(p.getUser(), token), p.getTitle());

    }

    public AnswerStatistic showAnswerStatistic(Long studentId, List<QuestionClassification> classes, List<QuestionType> types) {
        authenticationService.ensureLoggedIn(studentId);
        return statisticService.showAnswerStatistic(studentId, classes, types);
    }

}
