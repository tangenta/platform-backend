package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.repositories.QuestionRepository;
import com.tangenta.utils.QuestionIdGenerator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
@Profile("dev-test")
public class TestQuestionRepository implements QuestionRepository {
    public static final Long currentMaxLength = 2L;
    private static List<MQuestion> allQuestions = new LinkedList<MQuestion>() {{
        add(new MQuestion(1L, "Which object is red?", QuestionType.SingleChoice,
                QuestionClassification.Daodepingjia, "Apple",
                "Apple is red.", true, 1L));
        add(new MQuestion(2L, "Which word's length is < 10?", QuestionType.MultipleChoice,
                QuestionClassification.Daodepingjia, "Egg, Fxxx, Good, High",
                "answer description2", true, 2L));
    }};
    private QuestionIdGenerator questionIdGenerator;

    public TestQuestionRepository(QuestionIdGenerator questionIdGenerator) {
        this.questionIdGenerator = questionIdGenerator;
    }

    @Override
    public List<MQuestion> getAllQuestions() {
        return allQuestions;
    }

    @Override
    public List<MQuestion> getQuestionsByClassAndType(List<QuestionClassification> classifications, List<QuestionType> types) {
        // TODO: not enough data
        return allQuestions;
    }

    @Override
    public MQuestion findQuestionById(Long questionId) {
        for (MQuestion q: allQuestions) {
            if (q.getQuestionId().equals(questionId)) return q;
        }
        return null;
    }

    @Override
    public void createQuestion(MQuestion q) {
        allQuestions.add(new MQuestion(q.getQuestionId(), q.getDescription(), q.getType(),
                q.getClassification(), q.getCorrectAnswer(), q.getAnswerDescription(),
                q.getPass(), q.getBelongToStudentId()));
    }
}
