package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tangenta.data.pojo.*;
import com.tangenta.data.pojo.graphql.*;
import com.tangenta.data.pojo.mybatis.AnswerCountDatePair;
import com.tangenta.repositories.AnnouncementRepository;
import com.tangenta.repositories.PictureRepository;
import com.tangenta.repositories.UserRepository;
import com.tangenta.service.*;
import com.tangenta.utils.Utils;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
    private static Logger logger = LoggerFactory.getLogger(Query.class);

    @Value("${image-resource-path}")
    private String imageResourcePath;

    @Value("${hostname}")
    private String hostname;

    private final UserRepository userRepository;

    private final UserSecurityService userSecurityService;
    private final QuestionService questionService;
    private final AuthenticationService authenticationService;
    private final StatisticService statisticService;
    private final PostService postService;
    private final CommentService commentService;
    private final FollowService followService;
    private final ValidationService validationService;
    private final PagingService pagingService;
    private final FavouriteService favouriteService;
    private final StudentInfoService studentInfoService;
    private final PictureRepository pictureRepository;
    private final AnnouncementRepository announcementRepository;

    public Query(UserRepository userRepository,
                 UserSecurityService userSecurityService, QuestionService questionService,
                 AuthenticationService authenticationService, StatisticService statisticService,
                 PostService postService, CommentService commentService, FollowService followService,
                 ValidationService validationService, PagingService pagingService,
                 FavouriteService favouriteService, StudentInfoService studentInfoService,
                 PictureRepository pictureRepository, AnnouncementRepository announcementRepository) {
        this.userRepository = userRepository;
        this.userSecurityService = userSecurityService;
        this.questionService = questionService;
        this.authenticationService = authenticationService;
        this.statisticService = statisticService;
        this.postService = postService;
        this.commentService = commentService;
        this.followService = followService;
        this.validationService = validationService;
        this.pagingService = pagingService;
        this.favouriteService = favouriteService;
        this.studentInfoService = studentInfoService;
        this.pictureRepository = pictureRepository;
        this.announcementRepository = announcementRepository;
    }

    public List<User> users(DataFetchingEnvironment env) {
        String authToken = Utils.getAuthToken(env).orElse("");
        List<User> rawAllUsers = userRepository.getAllUsers();
        return userSecurityService.filterUsersByToken(rawAllUsers, authToken);
    }

    public User user(String username, DataFetchingEnvironment env) {
        validationService.ensureUserExistence(username);

        String authToken = Utils.getAuthToken(env).orElse("");
        User rawUser = userRepository.findByUsername(username);
        return userSecurityService.filterUserByToken(rawUser, authToken);
    }

    public User userById(Long studentId, DataFetchingEnvironment env) {
        authenticationService.ensureLoggedIn(studentId);
        validationService.ensureUserExistence(studentId);

        String authToken = Utils.getAuthToken(env).orElse("");
        User rawUser = userRepository.findById(studentId);
        return userSecurityService.filterUserByToken(rawUser, authToken);
    }

    public Question randomQuestion(Long studentId, List<QuestionClassification> classifications,
                                   List<QuestionType> types, DataFetchingEnvironment env) {
        String token = Utils.getAuthToken(env).orElse("");
        if (!authenticationService.authenticate(studentId, token)) {
            studentId = null;
        }
        return questionService.randomQuestion(studentId, classifications, types);
    }

    public List<Post> showPosts(int number, int from, SortMethod sortBy, DataFetchingEnvironment env) {
        List<Post> allPosts = postService.allPost(number, from, sortBy);

        String token = Utils.getAuthToken(env).orElse("");
        return userSecurityService.filterUsersInPost(allPosts, token);
    }

    public List<Post> showUserPosts(Long studentId, int number, int from, SortMethod sortBy,
                                    DataFetchingEnvironment env) {
        List<Post> allPosts = postService.allUserPosts(studentId, number, from, sortBy);

        String token = Utils.getAuthToken(env).orElse("");
        return userSecurityService.filterUsersInPost(allPosts, token);
    }

    public Post viewPost(Long postId, DataFetchingEnvironment env) {
        validationService.ensurePostExistence(postId);

        String token = Utils.getAuthToken(env).orElse("");
        Post p = postService.viewPost(postId);
        return userSecurityService.filterUserInPost(p, token);
    }

    public List<Post> favPosts(Long studentId, int number, int from,  DataFetchingEnvironment env) {
        validationService.ensureUserExistence(studentId);
        authenticationService.ensureAuthenticated(studentId, env);
        return favouriteService.favouritePosts(studentId, number, from);
    }

    public AnswerStatistic answerStatisticByClassAndType(Long studentId, List<QuestionClassification> classes, List<QuestionType> types) {
        validationService.ensureUserExistence(studentId);
        authenticationService.ensureLoggedIn(studentId);
        return statisticService.showAnswerStatistic(studentId, classes, types);
    }

    public List<Comment> showComments(Long postId, int number, int from) {
        validationService.ensurePostExistence(postId);
        return commentService.showComments(postId, number, from);
    }

    public List<Comment> showUserComments(Long studentId, int number, int from) {
        validationService.ensureUserExistence(studentId);
        return commentService.showUserComments(studentId, number, from);
    }


    public List<User> following(Long studentId, int number, int from) {
        validationService.ensureUserExistence(studentId);
        return pagingService.paging(followService.following(studentId), number, from);
    }

    public List<User> followers(Long studentId, int number, int from) {
        validationService.ensureUserExistence(studentId);
        return pagingService.paging(followService.followers(studentId), number, from);
    }

    public List<AnswerStatistic> answerStatisticByClass(Long studentId, List<QuestionClassification> classes) {
        validationService.ensureUserExistence(studentId);
        authenticationService.ensureLoggedIn(studentId); // TODO: authenticated

        return statisticService.showAnswerStatisticByClass(studentId, classes);
    }

    public List<AnswerStatistic> answerStatisticByType(Long studentId, List<QuestionType> types) {
        validationService.ensureUserExistence(studentId);
        authenticationService.ensureLoggedIn(studentId);

        return statisticService.showAnswerStatisticByType(studentId, types);
    }

    public List<AnswerCountDatePair> answersCountRecently(Long studentId, List<LocalDate> dates) {
        validationService.ensureUserExistence(studentId);
        authenticationService.ensureLoggedIn(studentId);

        return statisticService.answersCountRecently(studentId, dates);
    }

    public StudentInfo studentInfo(Long studentId, DataFetchingEnvironment env) {
        validationService.ensureUserExistence(studentId);
        authenticationService.ensureAuthenticated(studentId, env);
        return studentInfoService.get(studentId);
    }

    public Double studentScore(Long studentId, DataFetchingEnvironment env) {
        validationService.ensureUserExistence(studentId);
        authenticationService.ensureAuthenticated(studentId, env);
        return statisticService.studentScore(studentId);
    }

    public StudentStatistic studentStatistic(Long studentId, DataFetchingEnvironment env) {
        validationService.ensureUserExistence(studentId);
        authenticationService.ensureAuthenticated(studentId, env);

        return statisticService.getStudentStatistic(studentId);
    }

    public List<TopStudent> topStudents(int number, int from) {
        return statisticService.topStudents(number, from);
    }

    public String profileImageLocation(Long studentId) {
        validationService.ensureUserExistence(studentId);
        String filename = pictureRepository.getUserPicture(studentId);
        if (filename == null || filename.isEmpty()) {
            return "";
        }
        return hostname + imageResourcePath + filename;
    }

    public List<Announcement> announcements() {
        return announcementRepository.allAnnouncements();
    }
}
