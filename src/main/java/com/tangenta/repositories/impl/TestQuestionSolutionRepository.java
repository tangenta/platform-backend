package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.mybatis.QuestionSolution;
import com.tangenta.repositories.QuestionSolutionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Profile("dev-test")
public class TestQuestionSolutionRepository implements QuestionSolutionRepository {
    private static Logger logger = LoggerFactory.getLogger(TestQuestionSolutionRepository.class);

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
        logger.info("get questionId = {}", questionId);
        solutions.forEach(s -> logger.info("{}", s.getQuestionId()));
        return solutions.stream()
                .filter(qs -> qs.getQuestionId().equals(questionId))
                .collect(Collectors.toList());
    }

    @Override
    public void createQuestionSolution(Long questionId, String solution) {
        solutions.add(new QuestionSolution(questionId, solution));
    }

}
