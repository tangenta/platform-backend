package com.tangenta.service;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.graphql.Feedback;
import com.tangenta.data.pojo.graphql.Question;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;
    private ValidationService validationService;

    public QuestionService(QuestionRepository questionRepository, ValidationService validationService) {
        this.questionRepository = questionRepository;
        this.validationService = validationService;
    }

    public Question randomQuestion(QuestionClassification classification, QuestionType type) {
        List<MQuestion> questions = questionRepository.getQuestionsByClassAndType(classification, type);
        // TODO: filter visited questions
        // TODO: implement random

        if (questions.isEmpty()) throw new BusinessException("题库已经没有题了");
        MQuestion q = questions.iterator().next();
        return new Question(q.getQuestionId(), q.getDescription(), q.getClassification());
    }

    public Feedback validateAnswer(Long questionId, String answer) {
        validationService.ensureQuestionExistence(questionId);

        MQuestion q = questionRepository.findQuestionById(questionId);
        // TODO: build a robust validation system
        boolean isCorrect = q.getCorrectAnswer().equals(answer);
        return new Feedback(questionId, isCorrect, q.getAnswerDescription(), q.getCorrectAnswer());
    }

    public void createQuestion(Long studentId, String questionDescription, QuestionType type,
                               QuestionClassification classification, String correctAnswer,
                               String answerDescription) {
        String trimQuestion = questionDescription.trim();
        String trimAnswer = correctAnswer.trim();
        validationService.ensureNonEmptyString(trimQuestion, "问题描述");
        validationService.ensureNonEmptyString(trimAnswer, "正确答案");

        MQuestion partialQuestion = new MQuestion(-1L, trimQuestion, type, classification,
                trimAnswer, answerDescription, false, studentId);
        questionRepository.createQuestion(partialQuestion);
    }

    // TODO: maybe show similar question
}
