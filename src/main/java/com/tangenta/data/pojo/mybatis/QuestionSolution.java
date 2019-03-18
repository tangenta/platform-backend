package com.tangenta.data.pojo.mybatis;

public class QuestionSolution {
    private Long questionId;
    private String option;

    public QuestionSolution() {
    }

    public QuestionSolution(Long questionId, String option) {
        this.questionId = questionId;
        this.option = option;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getOption() {
        return option;
    }
}
