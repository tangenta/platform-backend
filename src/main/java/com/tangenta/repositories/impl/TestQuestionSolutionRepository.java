package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.mybatis.QuestionSolution;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.QuestionSolutionRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TestQuestionSolutionRepository implements QuestionSolutionRepository {
    private static List<QuestionSolution> solutions = new LinkedList<QuestionSolution>(){{
        add(new QuestionSolution(1L, "Apple"));
        add(new QuestionSolution(1L, "Banana"));
        add(new QuestionSolution(1L, "Cat"));
        add(new QuestionSolution(1L, "Dog"));
        add(new QuestionSolution(2L, "Egg"));
        add(new QuestionSolution(2L, "Fxxx"));
        add(new QuestionSolution(2L, "Good"));
        add(new QuestionSolution(2L, "High"));
    }};


    @Override
    public List<QuestionSolution> getByQuestionId(Long questionId) {
        return solutions.stream()
                .filter(qs -> qs.getQuestionId().equals(questionId))
                .collect(Collectors.toList());
    }

    @Override
    public void createQuestionSolution(Long questionId, String solution) {

    }

}
