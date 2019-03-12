package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.QuestionMapper;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.repositories.QuestionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dev")
public class MyQuestionRepository implements QuestionRepository {
    private QuestionMapper questionMapper;

    public MyQuestionRepository(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public List<MQuestion> getAllQuestions() {
        return questionMapper.getAllQuestions();
    }

    @Override
    public List<MQuestion> getQuestionsByClassAndType(List<QuestionClassification> classifications, List<QuestionType> types) {
        List<MQuestion> result = new LinkedList<>();
        for (QuestionType type: types) {
            List<MQuestion> specificTypeQuestion = questionMapper.getQuestionsByType(type);
            result.addAll(specificTypeQuestion.stream()
                    .filter(question -> classifications.contains(question.getClassification()))
                    .collect(Collectors.toList()));
        }
        return result;
    }

    @Override
    public MQuestion findQuestionById(Long questionId) {
        return questionMapper.getById(questionId);
    }

    @Override
    public void createQuestion(MQuestion partialQuestion) {
        questionMapper.createQuestion(partialQuestion);
    }
}
