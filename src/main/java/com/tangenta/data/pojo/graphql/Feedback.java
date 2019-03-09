package com.tangenta.data.pojo.graphql;

/**
 * type Feedback {
 *     questionId: Long!
 *     correct: Boolean!
 *     answerDescription: String!
 *     correctAnswer: String!
 * }
 */
public class Feedback {
    private Long questionId;
    private boolean correct;
    private String answerDescription;
    private String correctAnswer;

    public Feedback(Long questionId, boolean correct, String answerDescription, String correctAnswer) {
        this.questionId = questionId;
        this.correct = correct;
        this.answerDescription = answerDescription;
        this.correctAnswer = correctAnswer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getAnswerDescription() {
        return answerDescription;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrect() {
        return correct;
    }
}
