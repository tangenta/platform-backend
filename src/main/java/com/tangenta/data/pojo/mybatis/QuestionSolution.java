package com.tangenta.data.pojo.mybatis;

public class QuestionSolution {
    private Long questionId;
    private String solution;

    public QuestionSolution() {
    }

    public QuestionSolution(Long questionId, String solution) {
        this.questionId = questionId;
        this.solution = solution;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getSolution() {
        return solution;
    }
}
