package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.graphql.Feedback;
import com.tangenta.service.*;
import com.tangenta.types.LoginPayload;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private static Logger logger = LoggerFactory.getLogger(Mutation.class);

    private LoginService loginService;
    private RegisterService registerService;
    private ValidationService validationService;
    private AuthenticationService authenticationService;
    private QuestionService questionService;
    private StatisticService statisticService;
    private PostService postService;
    private CommentService commentService;
    private FollowService followService;


    public Mutation(LoginService loginService, RegisterService registerService,
                    ValidationService validationService, AuthenticationService authenticationService,
                    QuestionService questionService, StatisticService statisticService,
                    PostService postService, CommentService commentService, FollowService followService) {
        this.loginService = loginService;
        this.registerService = registerService;
        this.validationService = validationService;
        this.authenticationService = authenticationService;
        this.questionService = questionService;
        this.statisticService = statisticService;
        this.postService = postService;
        this.commentService = commentService;
        this.followService = followService;
    }

    public LoginPayload login(String username, String password) {
        return loginService.login(username, password);
    }

    public boolean logout(Long studentId, DataFetchingEnvironment env) {
        return loginService.logout(studentId, env);
    }

    public boolean register(String username, String password, String email) {
        validationService.ensureValidEmailAddress(email);
        validationService.ensureNoDuplication(username, email);
        registerService.beginRegisterProcess(username, password, email);
        return true;
    }

    public Feedback validateAnswer(Long studentId, Long questionId, String answer) {
        authenticationService.ensureLoggedIn(studentId);

        Feedback fb =  questionService.validateAnswer(questionId, answer);
        statisticService.storeUsersFeedback(studentId, fb);
        return fb;
    }

    public boolean createQuestion(Long studentId, String questionDescription, QuestionType type,
                                  QuestionClassification classification, String correctAnswer,
                                  String answerDescription, Optional<List<String>> choices) {

        authenticationService.ensureLoggedIn(studentId);
        logger.info("{}", choices);
        questionService.createQuestion(studentId, questionDescription, type,
                classification, correctAnswer, answerDescription, choices);
        return true;
    }

    public boolean createPost(Long studentId, String title, String content) {
        authenticationService.ensureLoggedIn(studentId);
        postService.createPost(studentId, title, content);
        return true;
    }

    public boolean deletePost(Long studentId, Long postId) {
        authenticationService.ensureLoggedIn(studentId);
        postService.deletePost(postId);
        return true;
    }

    public boolean updatePost(Long studentId, Long postId, String title, String content) {
        authenticationService.ensureLoggedIn(studentId);
        validationService.ensurePostExistence(postId);
        validationService.ensurePostBelongToStudent(postId, studentId);

        postService.updatePost(postId, title, content);
        return true;
    }

    public boolean addComment(Long studentId, Long postId, String content) {
        authenticationService.ensureLoggedIn(studentId);
        validationService.ensurePostExistence(postId);

        commentService.addComment(studentId, postId, content);
        return true;
    }

    public boolean deleteComment(Long studentId, Long commentId) {
        authenticationService.ensureLoggedIn(studentId);
        validationService.ensureCommentExistence(commentId);
        validationService.ensureCommentBelongToStudent(studentId, commentId);

        commentService.deleteComment(commentId);
        return true;
    }

    public boolean follow(Long subjectId, Long objectId) {
        authenticationService.ensureLoggedIn(subjectId);
        validationService.ensureUserExistence(objectId);

        followService.follow(subjectId, objectId);
        return true;
    }

    public boolean unFollow(Long subjectId, Long objectId) {
        authenticationService.ensureLoggedIn(subjectId);

        followService.unFollow(subjectId, objectId);
        return true;
    }

}
