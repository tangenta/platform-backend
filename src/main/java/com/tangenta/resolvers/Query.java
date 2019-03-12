package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tangenta.data.pojo.Post;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.graphql.Question;
import com.tangenta.data.pojo.graphql.SortMethod;
import com.tangenta.service.QuestionService;
import com.tangenta.service.SecurityService;
import com.tangenta.repositories.PostRepository;
import com.tangenta.repositories.UserRepository;
import com.tangenta.data.pojo.User;
import com.tangenta.utils.Utils;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;
import static com.tangenta.data.pojo.graphql.SortMethod.*;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Query implements GraphQLQueryResolver {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    private final SecurityService securityService;
    private final QuestionService questionService;

    public Query(UserRepository userRepository, PostRepository postRepository,
                 SecurityService securityService, QuestionService questionService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.securityService = securityService;
        this.questionService = questionService;
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

    public List<Post> posts() {
        return postRepository.getAllPosts();
    }

//    public List<MQuestion> questions() {
//        return questionRepository.getAllQuestions();
//    }

    public Question randomQuestion(List<QuestionClassification> classifications, List<QuestionType> types) {
        return questionService.randomQuestion(classifications, types);
    }

    public List<Post> showPosts(int numbers, Long from, SortMethod sortBy) {
        List<Post> allPosts = postRepository.getAllPosts();
        Comparator<Post> comparator;
        switch (sortBy) {
            case ReplyNumber: comparator = Comparator.comparingLong(Post::getReplyNumber).thenComparing(Post::getPostId); break;
            case ViewNumber: comparator = Comparator.comparingLong(Post::getViewNumber).thenComparing(Post::getPostId); break;
            case Time:
            default: comparator = Comparator.comparing(Post::getPublishTime).thenComparing(Post::getPostId);
        }
        allPosts.sort(comparator);
        int index = 0;
        for (Post post: allPosts) {
            if (post.getPostId().equals(from)) break;
            index++;
        }
        return allPosts.stream().skip(index).limit(numbers).collect(Collectors.toList());
    }

}
