package com.tangenta.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.tangenta.data.pojo.Post;
import com.tangenta.data.pojo.Question;
import com.tangenta.repositories.PostRepository;
import com.tangenta.repositories.QuestionRepository;
import com.tangenta.repositories.UserRepository;
import com.tangenta.data.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final QuestionRepository questionRepository;

    public Query(UserRepository userRepository, PostRepository postRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.questionRepository = questionRepository;
    }

    public List<User> users() {
        return userRepository.getAllUsers();
    }

    public List<Post> posts() {
        return postRepository.getAllPosts();
    }

    public List<Question> questions() {
        return questionRepository.getAllQuestions();
    }
}
