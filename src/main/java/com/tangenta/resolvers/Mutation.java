package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.tangenta.data.pojo.Gender;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.graphql.Feedback;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.service.*;
import com.tangenta.types.LoginPayload;
import graphql.schema.DataFetchingEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {
    private static Logger logger = LoggerFactory.getLogger(Mutation.class);

    private UserLoginService loginService;
    private RegisterService registerService;
    private ValidationService validationService;
    private AuthenticationService authenticationService;
    private QuestionService questionService;
    private StatisticService statisticService;
    private PostService postService;
    private CommentService commentService;
    private FollowService followService;
    private FavouriteService favouriteService;
    private StudentInfoService studentInfoService;


    public Mutation(UserLoginService loginService, RegisterService registerService,
                    ValidationService validationService, AuthenticationService authenticationService,
                    QuestionService questionService, StatisticService statisticService,
                    PostService postService, CommentService commentService, FollowService followService,
                    FavouriteService favouriteService, StudentInfoService studentInfoService) {
        this.loginService = loginService;
        this.registerService = registerService;
        this.validationService = validationService;
        this.authenticationService = authenticationService;
        this.questionService = questionService;
        this.statisticService = statisticService;
        this.postService = postService;
        this.commentService = commentService;
        this.followService = followService;
        this.favouriteService = favouriteService;
        this.studentInfoService = studentInfoService;
    }

    public LoginPayload login(String username, String password) {
        return loginService.login(username, password);
    }

    public boolean logout(Long studentId, DataFetchingEnvironment env) {
        return loginService.logout(studentId, env);
    }

    public boolean register(String username, String password, String email) {
        validationService.ensureUserNonExist(username);
        validationService.ensureValidEmailAddress(email);
        validationService.ensureNoDuplication(username, email);
        validationService.ensurePasswordValid(password);

        registerService.beginRegisterProcess(username, password, email);
        return true;
    }

    public boolean changePassword(Long studentId, String oldPassword, String newPassword, DataFetchingEnvironment env) {
        authenticationService.ensureLoggedIn(studentId);
        authenticationService.ensureAuthenticated(studentId, env);
        if (!loginService.authMatched(studentId, oldPassword)) {
            throw new BusinessException("旧密码不正确");
        }
        loginService.changePassword(studentId, newPassword);
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
                                  String answerDescription, List<String> choices) {

        authenticationService.ensureLoggedIn(studentId);
        questionService.createQuestion(studentId, questionDescription, type,
                classification, correctAnswer, answerDescription, choices);
        return true;
    }

    public boolean createPost(Long studentId, String title, String content, DataFetchingEnvironment env) {
        authenticationService.ensureAuthenticated(studentId, env);
        postService.createPost(studentId, title, content);
        return true;
    }

    public boolean deletePost(Long studentId, Long postId, DataFetchingEnvironment env) {
        authenticationService.ensureAuthenticated(studentId, env);
        postService.deletePost(postId);
        return true;
    }

    public boolean updatePost(Long studentId, Long postId, String title, String content, DataFetchingEnvironment env) {
        authenticationService.ensureAuthenticated(studentId, env);
        validationService.ensurePostExistence(postId);
        validationService.ensurePostBelongToStudent(postId, studentId);

        postService.updatePost(postId, title, content);
        return true;
    }

    public boolean addFavPost(Long studentId, Long postId, DataFetchingEnvironment env) {
//        authenticationService.ensureAuthenticated(studentId, env);
        validationService.ensureUserExistence(studentId);
        validationService.ensureUserExistence(postId);
        validationService.ensureFavNotExist(studentId, postId);

        favouriteService.addFavouritePost(studentId, postId);
        return true;
    }

    public boolean deleteFavPost(Long studentId, Long postId, DataFetchingEnvironment env) {
//        authenticationService.ensureAuthenticated(studentId, env);
        validationService.ensureUserExistence(studentId);
        validationService.ensureUserExistence(postId);
        validationService.ensureFavExist(studentId, postId);

        favouriteService.deleteFavouritePost(studentId, postId);
        return true;
    }

    public boolean addComment(Long studentId, Long postId, String content) {
        authenticationService.ensureLoggedIn(studentId);
        validationService.ensurePostExistence(postId);

        commentService.addComment(studentId, postId, content);
        postService.increaseReplyNumber(postId);
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

    public boolean updateStudentInfo(Long studentId, String studentName, Gender gender, Long picture, String partyBranch, LocalDate birthday,
                                     String nation, String nativePlace, String politicalLandscape, String college, String major,
                                     String currentClass, String lengthOfSchooling, String state, String professionalDirection,
                                     LocalDate admissionDate, String dormitoryNumber, String phone, String mailCode, String idNumber,
                                     String academicLevel, LocalDate joinLeagueDate, String englishLevel, String graduateSchool,
                                     String familyCode, String familyPhone, String fatherName, String fatherWorkPlace,
                                     String fatherPhone, String motherName, String motherWorkPlace, String motherPhone,
                                     String familyAddress, String hmtCode, DataFetchingEnvironment env) {
        validationService.ensureUserExistence(studentId);
//        authenticationService.ensureAuthenticated(studentId, env);
        studentInfoService.update(studentId, studentName, gender, picture, partyBranch, birthday,
                nation, nativePlace, politicalLandscape, college, major,
                currentClass, lengthOfSchooling, state, professionalDirection,
                admissionDate, dormitoryNumber, phone, mailCode, idNumber,
                academicLevel, joinLeagueDate, englishLevel, graduateSchool,
                familyCode, familyPhone, fatherName, fatherWorkPlace,
                fatherPhone, motherName, motherWorkPlace, motherPhone,
                familyAddress, hmtCode);
        return true;
    }

}
