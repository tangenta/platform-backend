package com.tangenta.data.pojo.mybatis;

import java.util.Date;

public class DoneTag {
    private Long studentId;
    private Long questionId;
    private Date doneDate;

    public DoneTag() {}

    public DoneTag(Long studentId, Long questionId, Date doneDate) {
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

    public Date getDoneDate() {
        return doneDate;
    }
}
