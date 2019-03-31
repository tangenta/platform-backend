package com.tangenta.service;

import com.google.common.base.CharMatcher;
import com.google.common.io.CharStreams;
import com.tangenta.data.pojo.User;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Arrays;
import java.util.stream.Stream;

@Service
public class ValidationService {
    private static Logger logger = LoggerFactory.getLogger(ValidationService.class);
    private UserRepository userRepository;
    private QuestionRepository questionRepository;
    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private FavouriteRepository favouriteRepository;

    public ValidationService(UserRepository userRepository, QuestionRepository questionRepository,
                             PostRepository postRepository, CommentRepository commentRepository,
                             FavouriteRepository favouriteRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.favouriteRepository = favouriteRepository;
    }

    public void ensureValidEmailAddress(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new BusinessException("邮箱地址无效");
        }
    }

    public void ensureNoDuplication(String username, String email) {
        if (isDuplicateUsername(username)) throw new BusinessException("用户名已存在");
        if (isDuplicateEmail(email)) throw new BusinessException("该邮箱已注册");
    }

    private boolean isDuplicateUsername(String username) {
        User u1 = userRepository.findByUsername(username);
        logger.info("username " + username + (u1 == null ? " not exists" : "exist"));
        return u1 != null;
    }

    private boolean isDuplicateEmail(String email) {
        User u2 = userRepository.findByEmail(email);
        return u2 != null;
    }

    public void ensureQuestionExistence(Long questionId) {
        MQuestion q = questionRepository.findQuestionById(questionId);
        if (q == null) throw new BusinessException("该题目不存在");
    }

    public void ensureNonEmptyString(String obj, String itemDescription) {
        if (obj.isEmpty()) throw new BusinessException(itemDescription + "不能为空");
    }

    public void ensureUserExistence(Long studentId) {
        if (userRepository.findById(studentId) == null) throw new BusinessException("找不到该用户");
    }

    public void ensureUserExistence(String username) {
        if (userRepository.findByUsername(username) == null) throw new BusinessException("找不到该用户名");
    }
    public void ensureUserNonExist(String username) {
        if (userRepository.findByUsername(username) != null) throw new BusinessException("该用户名已被占用");
    }

    public boolean userExist(Long studentId) {
        return userRepository.findById(studentId) != null;
    }

    public void ensurePostExistence(Long postId) {
        if (postRepository.findById(postId) == null) throw new BusinessException("帖子不存在");
    }

    public void ensurePostBelongToStudent(Long postId, Long studentId) {
        if (!postRepository.findById(postId).getStudentId().equals(studentId))
            throw new BusinessException("帖子所有者不匹配");
    }

    public void ensureCommentExistence(Long commentId) {
        if (commentRepository.findById(commentId) == null)
            throw new BusinessException("评论不存在");
    }

    public void ensureCommentBelongToStudent(Long commentId, Long studentId) {
        logger.info("comment: {}", commentRepository.findById(commentId));
        if (!commentRepository.findById(commentId).getStudentId().equals(studentId))
            throw new BusinessException("评论所有者不匹配");
    }

    public void ensureFavNotExist(Long studentId, Long postId) {
        if (favouriteRepository.favouritePosts(studentId).stream()
                .anyMatch(fp -> fp.getPostId().equals(postId))) {
            throw new BusinessException("该帖子已收藏");
        }
    }

    public void ensureFavExist(Long studentId, Long postId) {
        if (favouriteRepository.favouritePosts(studentId).stream()
                .noneMatch(fp -> fp.getPostId().equals(postId))) {
            throw new BusinessException("未收藏该帖子");
        }
    }

    public void ensurePasswordValid(String password) {
        if (password.length() < 6) throw new BusinessException("密码少于6位");
        for (int i = 0; i != password.length(); ++i) {
            char ch = password.charAt(i);
            if (!CharMatcher.forPredicate(c -> Character.isLetter(c) ||
                    Character.isDigit(c) ||
                    c.equals('_')).matchesAllOf(password))
                throw new BusinessException("密码应由字母、数字、下划线组成");
        }
    }
}
