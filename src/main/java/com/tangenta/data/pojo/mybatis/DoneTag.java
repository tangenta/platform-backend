package com.tangenta.data.pojo.mybatis;

import java.time.LocalDate;

public class DoneTag {
    private Long studentId;
    private Long questionId;
    private LocalDate doneDate;

    public DoneTag() {}

    public DoneTag(Long studentId, Long questionId, LocalDate doneDate) {
        this.studentId = studentId;
        this.questionId = questionId;
        this.doneDate = doneDate;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public LocalDate getDoneDate() {
        return doneDate;
    }
}
