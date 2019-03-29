package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.QuestionSolutionMapper;
import com.tangenta.data.pojo.mybatis.QuestionSolution;
import com.tangenta.repositories.QuestionSolutionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("dev")
public class MyQuestionSolutionRepository implements QuestionSolutionRepository {
    private QuestionSolutionMapper questionSolutionMapper;

    public MyQuestionSolutionRepository(QuestionSolutionMapper questionSolutionMapper) {
        this.questionSolutionMapper = questionSolutionMapper;
    }

    @Override
    public List<QuestionSolution> getByQuestionId(Long questionId) {
        return questionSolutionMapper.getByQuestionId(questionId);
    }

    @Override
    public void createQuestionSolution(Long questionId, String solution) {
        questionSolutionMapper.createQuestionSolution(questionId, solution);
    }

}
