package com.tangenta.data.pojo.graphql;

public class StudentStatistic {
    private Long offlineLearningMin;
    private Long onlineLearningMin;
    private Long createQuestionNumber;
    private Long passQuestionNumber;
    private Double attendanceRate;
    private Double paperScore;
    private Double homeworkScore;
    private Double annualScore;
    private Long answerQuestionNumber;

    public StudentStatistic(Long offlineLearningMin, Long onlineLearningMin, Long createQuestionNumber,
                            Long passQuestionNumber, Double attendanceRate, Double paperScore, Double homeworkScore,
                            Double annualScore, Long answerQuestionNumber) {
        this.offlineLearningMin = offlineLearningMin;
        this.onlineLearningMin = onlineLearningMin;
        this.createQuestionNumber = createQuestionNumber;
        this.passQuestionNumber = passQuestionNumber;
        this.attendanceRate = attendanceRate;
        this.paperScore = paperScore;
        this.homeworkScore = homeworkScore;
        this.annualScore = annualScore;
        this.answerQuestionNumber = answerQuestionNumber;
    }

    public Long getOfflineLearningMin() {
        return offlineLearningMin;
    }

    public Long getOnlineLearningMin() {
        return onlineLearningMin;
    }

    public Long getCreateQuestionNumber() {
        return createQuestionNumber;
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
}
