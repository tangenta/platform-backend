package com.tangenta.data.pojo.graphql;

import com.tangenta.data.pojo.QuestionClassification;

import java.util.List;
import java.util.Optional;

public class Question {
    private Long questionId;
    private String description;
    private Optional<List<String>> solution;
    private QuestionClassification classification;

    public Question(Long questionId, String description, Optional<List<String>> solution, QuestionClassification classification) {
        this.questionId = questionId;
        this.description = description;
        this.solution = solution;
        this.classification = classification;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getDescription() {
        return description;
    }

    public QuestionClassification getClassification() {
        return classification;
    }

    public Optional<List<String>> getSolution() {
        return solution;
    }
}
