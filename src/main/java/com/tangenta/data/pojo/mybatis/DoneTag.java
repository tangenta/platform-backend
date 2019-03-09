package com.tangenta.data.pojo.mybatis;

public class DoneTag {
    private Long studentId;
    private Long questionId;
    private String doneDate;

    public DoneTag() {}

    public DoneTag(Long studentId, Long questionId, String doneDate) {
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

    public String getDoneDate() {
        return doneDate;
    }
}
