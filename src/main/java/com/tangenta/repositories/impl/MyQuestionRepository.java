package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.QuestionMapper;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.repositories.QuestionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<MQuestion> getQuestionsByClassAndType(QuestionClassification classification, QuestionType type) {
        return questionMapper.getQuestionsByClassAndType(classification, type);
    }

    @Override
    public MQuestion findQuestionById(Long questionId) {
        return questionMapper.getById(questionId);
    }
}
