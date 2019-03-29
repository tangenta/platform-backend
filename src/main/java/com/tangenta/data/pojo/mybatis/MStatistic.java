package com.tangenta.data.pojo.mybatis;

public class MStatistic {
    private Long studentId;
    private Long offlineLearningTime;
    private Long onlineLearningTime;
    private Long postQuestionNumber;
    private Long passQuestionNumber;
    private Double attendanceRate;
    private Double paperScore;
    private Double homeworkScore;
    private Double annualScore;
    private Long answerQuestionNumber;
    private Long answerQuestionScore;

    public MStatistic() {
        studentId = 0L;
        offlineLearningTime = 0L;
        onlineLearningTime = 0L;
        postQuestionNumber = 0L;
        passQuestionNumber = 0L;
        attendanceRate = 0.0;
        paperScore =  0.0;
        homeworkScore = 0.0;
        annualScore =  0.0;
        answerQuestionNumber = 0L;
        answerQuestionScore = 0L;
    }

    public MStatistic(Long studentId, Long offlineLearningTime, Long onlineLearningTime,
                      Long postQuestionNumber, Long passQuestionNumber, Double attendanceRate,
                      Double paperScore, Double homeworkScore, Double annualScore,
                      Long answerQuestionNumber, Long answerQuestionScore) {
        this.studentId = studentId;
        this.offlineLearningTime = offlineLearningTime;
        this.onlineLearningTime = onlineLearningTime;
        this.postQuestionNumber = postQuestionNumber;
        this.passQuestionNumber = passQuestionNumber;
        this.attendanceRate = attendanceRate;
        this.paperScore = paperScore;
        this.homeworkScore = homeworkScore;
        this.annualScore = annualScore;
        this.answerQuestionNumber = answerQuestionNumber;
        this.answerQuestionScore = answerQuestionScore;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getOfflineLearningTime() {
        return offlineLearningTime;
    }

    public Long getOnlineLearningTime() {
        return onlineLearningTime;
    }

    public Long getPostQuestionNumber() {
        return postQuestionNumber;
    }

    public Long getPassQuestionNumber() {
        return passQuestionNumber;
    }

    public Double getAttendanceRate() {
        return attendanceRate;
    }

    public Double getPaperScore() {
        return paperScore;
    }

    public Double getHomeworkScore() {
        return homeworkScore;
    }

    public Double getAnnualScore() {
        return annualScore;
    }

    public Long getAnswerQuestionNumber() {
        return answerQuestionNumber;
    }

    public Long getAnswerQuestionScore() {
        return answerQuestionScore;
    }
}
