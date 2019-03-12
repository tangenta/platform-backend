package com.tangenta.data.pojo.mybatis;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;

import java.util.List;

public class MQuestion {
    private Long questionId;
    private String description;
    private QuestionType type;
    private QuestionClassification classification;
    private String correctAnswer;
    private String answerDescription;
    private Boolean isPass;
    private Long belongToStudentId;

    public MQuestion() {}

    public MQuestion(Long questionId, String description, QuestionType type,
                     QuestionClassification classification, String correctAnswer,
                     String answerDescription, Boolean isPass,
                     Long belongToStudentId) {
        this.questionId = questionId;
        this.description = description;
        this.type = type;
        this.classification = classification;
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

    public QuestionClassification getClassification() {
        return classification;
    }

}
