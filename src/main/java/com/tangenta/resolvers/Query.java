package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tangenta.data.pojo.Post;
import com.tangenta.data.pojo.Question;
import com.tangenta.data.service.SecurityService;
import com.tangenta.repositories.PostRepository;
import com.tangenta.repositories.QuestionRepository;
import com.tangenta.repositories.UserRepository;
import com.tangenta.data.pojo.User;
import com.tangenta.utils.Utils;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final QuestionRepository questionRepository;
    private final SecurityService securityService;

    public Query(UserRepository userRepository, PostRepository postRepository,
                 QuestionRepository questionRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.questionRepository = questionRepository;
        this.securityService = securityService;
    }

    public List<User> users(DataFetchingEnvironment env) {
        String authToken = Utils.getAuthToken(env).orElse("");
        List<User> rawAllUsers = userRepository.getAllUsers();
        return securityService.filterUsersByToken(rawAllUsers, authToken);
    }

    public List<Post> posts() {
        return postRepository.getAllPosts();
    }

    public List<Question> questions() {
        return questionRepository.getAllQuestions();
    }
}
