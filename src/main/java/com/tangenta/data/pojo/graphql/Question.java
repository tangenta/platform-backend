package com.tangenta.data.pojo.graphql;

import com.tangenta.data.pojo.QuestionClassification;

public class Question {
    private Long questionId;
    private String description;
    private QuestionClassification classification;

    public Question(Long questionId, String description, QuestionClassification classification) {
        this.questionId = questionId;
        this.description = description;
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
}
