package com.tangenta.repositories;

import com.tangenta.data.pojo.mybatis.QuestionSolution;

import java.util.List;

public interface QuestionSolutionRepository {
    List<QuestionSolution> getByQuestionId(Long questionId);
    void createQuestionSolution(Long questionId, String solution);
}
