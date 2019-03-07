package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.QuestionMapper;
import com.tangenta.data.pojo.Question;
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
    public List<Question> getAllQuestions() {
        return questionMapper.getAllQuestions();
    }
}
