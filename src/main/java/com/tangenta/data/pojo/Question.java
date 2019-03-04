package com.tangenta.data.pojo;

public class Question {
    private Long questionId;
    private String description;
    private QuestionType type;
    private String correctAnswer;
    private String answerDescription;
    private Boolean isPass;
    private Long belongToStudentId;

    public Question() {}

    public Question(Long questionId, String description, QuestionType type,
                    String correctAnswer, String answerDescription, Boolean isPass,
                    Long belongToStudentId) {
        this.questionId = questionId;
        this.description = description;
        this.type = type;
        this.correctAnswer = correctAnswer;
        this.answerDescription = answerDescription;
        this.isPass = isPass;
        this.belongToStudentId = belongToStudentId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getDescription() {
        return description;
    }

    public QuestionType getType() {
        return type;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getAnswerDescription() {
        return answerDescription;
    }

    public Boolean getPass() {
        return isPass;
    }

    public Long getBelongToStudentId() {
        return belongToStudentId;
    }
}
