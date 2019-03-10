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

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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
        MQuestion q = questionRepository.findQuestionById(questionId);
        if (q == null) throw new BusinessException("该题目不存在");

        // TODO: build a robust validation system
        boolean isCorrect = q.getCorrectAnswer().equals(answer);
        return new Feedback(questionId, isCorrect, q.getAnswerDescription(), q.getCorrectAnswer());
    }

    public void createQuestion(Long studentId, String questionDescription, QuestionType type,
                               QuestionClassification classification, String correctAnswer,
                               String answerDescription) {
        if (questionDescription.trim().isEmpty()) throw new BusinessException("题目描述不能为空");
        if (correctAnswer.trim().isEmpty()) throw new BusinessException("正确答案不能为空");

        MQuestion partialQuestion = new MQuestion(-1L, questionDescription, type, classification,
                correctAnswer, answerDescription, false, studentId);

    }

    // TODO: maybe show similar question
}
