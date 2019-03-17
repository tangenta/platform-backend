package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tangenta.data.pojo.graphql.AnswerStatistic;
import com.tangenta.data.pojo.graphql.Post;
import com.tangenta.data.pojo.mybatis.MPost;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.graphql.Question;
import com.tangenta.data.pojo.graphql.SortMethod;
import com.tangenta.service.AuthenticationService;
import com.tangenta.service.QuestionService;
import com.tangenta.service.SecurityService;
import com.tangenta.repositories.PostRepository;
import com.tangenta.repositories.UserRepository;
import com.tangenta.data.pojo.User;
import com.tangenta.service.StatisticService;
import com.tangenta.utils.Utils;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Query implements GraphQLQueryResolver {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final SecurityService securityService;
    private final QuestionService questionService;
    private final AuthenticationService authenticationService;
    private final StatisticService statisticService;

    public Query(UserRepository userRepository, PostRepository postRepository,
                 SecurityService securityService, QuestionService questionService,
                 AuthenticationService authenticationService, StatisticService statisticService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.securityService = securityService;
        this.questionService = questionService;
        this.authenticationService = authenticationService;
        this.statisticService = statisticService;
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

    public List<Post> posts(DataFetchingEnvironment env) {
        String token = Utils.getAuthToken(env).orElse("");
        return postRepository.getAllPosts().stream()
                .map(p -> new Post(p.getPostId(), p.getPublishTime(), p.getContent(),
                        p.getViewNumber(), p.getReplyNumber(),
                        securityService.filterUserByToken(userRepository.findById(p.getStudentId()), token), p.getTitle()))
                .collect(Collectors.toList());
    }

//    public List<MQuestion> questions() {
//        return questionRepository.getAllQuestions();
//    }

    public Question randomQuestion(List<QuestionClassification> classifications, List<QuestionType> types) {
        return questionService.randomQuestion(classifications, types);
    }

    public List<Post> showPosts(int numbers, Long from, SortMethod sortBy, DataFetchingEnvironment env) {
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

        String token = Utils.getAuthToken(env).orElse("");

        return allMPosts.stream()
                .skip(from.equals(0L) ? 0 : index + 1)
                .limit(numbers)
                .map(p -> new Post(p.getPostId(), p.getPublishTime(), p.getContent(),
                        p.getViewNumber(), p.getReplyNumber(),
                        securityService.filterUserByToken(userRepository.findById(p.getStudentId()), token),
                        p.getTitle()))
                .collect(Collectors.toList());
    }

    public AnswerStatistic showAnswerStatistic(Long studentId, List<QuestionClassification> classes, List<QuestionType> types) {
        authenticationService.ensureLoggedIn(studentId);
        return statisticService.showAnswerStatistic(studentId, classes, types);
    }

}
