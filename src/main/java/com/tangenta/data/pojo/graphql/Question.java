package com.tangenta.data.pojo.graphql;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;

import java.util.List;
import java.util.Optional;

public class Question {
    private Long questionId;
    private String description;
    private Optional<List<String>> options;
    private QuestionClassification classification;
    private QuestionType type;

    public Question(Long questionId, String description, Optional<List<String>> options, QuestionClassification classification, QuestionType type) {
        this.questionId = questionId;
        this.description = description;
        this.options = options;
        this.classification = classification;
        this.type = type;
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

    public Optional<List<String>> getOptions() {
        return options;
    }

    public QuestionType getType() {
        return type;
    }
}
