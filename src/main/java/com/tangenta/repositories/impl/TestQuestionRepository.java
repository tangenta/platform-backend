package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.repositories.QuestionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
@Profile("dev-test")
public class TestQuestionRepository implements QuestionRepository {
    private static List<MQuestion> allQuestions = new LinkedList<MQuestion>() {{
        add(new MQuestion(1L, "how are you?", QuestionType.MultiChoice,
                QuestionClassification.Daodepingjia, "I am fine",
                "answer description1", true, 1L));
        add(new MQuestion(2L, "r u ok?", QuestionType.SingleChoice,
                QuestionClassification.Daodepingjia, "whatever",
                "answer description2", true, 3L));
    }};

    @Override
    public List<MQuestion> getAllQuestions() {
        return allQuestions;
    }

    @Override
    public List<MQuestion> getQuestionsByClassAndType(QuestionClassification classification, QuestionType type) {
        return allQuestions;
    }

    @Override
    public MQuestion findQuestionById(Long questionId) {
        return null;
    }
}
