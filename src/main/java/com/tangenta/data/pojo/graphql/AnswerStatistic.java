package com.tangenta.data.pojo.graphql;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;

import java.util.List;

public class AnswerStatistic {
    private Long studentId;
    private Long correct;
    private Long total;

    public AnswerStatistic(Long studentId, Long correct, Long total) {
        this.studentId = studentId;
        this.correct = correct;
        this.total = total;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getCorrect() {
        return correct;
    }

    public Long getTotal() {
        return total;
    }
}
