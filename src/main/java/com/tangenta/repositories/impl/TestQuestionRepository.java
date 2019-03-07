package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.Question;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.repositories.QuestionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
@Profile("dev-test")
public class TestQuestionRepository implements QuestionRepository {
    private static List<Question> allQuestions = new LinkedList<Question>() {{
        add(new Question(1L, "how are you?", QuestionType.Daodepingjia, "I am fine",
                "answer description1", true, 1L));
        add(new Question(2L, "r u ok?", QuestionType.Daodepingjia, "whatever",
                "answer description2", true, 3L));
    }};

    @Override
    public List<Question> getAllQuestions() {
        return allQuestions;
    }
}
