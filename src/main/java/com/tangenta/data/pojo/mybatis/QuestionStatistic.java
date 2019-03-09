package com.tangenta.data.pojo.mybatis;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.graphql.Question;

public class QuestionStatistic {
    private Long studentId;
    private QuestionClassification classification;
    private QuestionType type;
    private Long total;
    private Long correct;

    public QuestionStatistic() {}

    public QuestionStatistic(Long studentId, QuestionClassification classification,
                             QuestionType type, Long total, Long correct) {
        this.studentId = studentId;
        this.classification = classification;
        this.type = type;
        this.total = total;
        this.correct = correct;
    }

    public Long getStudentId() {
        return studentId;
    }

    public QuestionClassification getClassification() {
        return classification;
    }

    public QuestionType getType() {
        return type;
    }

    public Long getTotal() {
        return total;
    }

    public Long getCorrect() {
        return correct;
    }
}
