package com.tangenta.service;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.graphql.Feedback;
import com.tangenta.data.pojo.graphql.Question;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.data.pojo.mybatis.QuestionSolution;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.QuestionRepository;
import com.tangenta.repositories.QuestionSolutionRepository;
import com.tangenta.utils.QuestionIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static com.tangenta.data.pojo.QuestionType.MultiChoice;
import static com.tangenta.data.pojo.QuestionType.SingleChoice;

@Service
public class QuestionService {
    private static Logger logger = LoggerFactory.getLogger(QuestionService.class);
    private Random random = new Random();

    private QuestionRepository questionRepository;
    private ValidationService validationService;
    private QuestionIdGenerator questionIdGenerator;
    private QuestionSolutionRepository questionSolutionRepository;

    public QuestionService(QuestionRepository questionRepository,
                           ValidationService validationService, QuestionIdGenerator questionIdGenerator, QuestionSolutionRepository questionSolutionRepository) {
        this.questionRepository = questionRepository;
        this.validationService = validationService;
        this.questionIdGenerator = questionIdGenerator;
        this.questionSolutionRepository = questionSolutionRepository;
    }

    public Question randomQuestion(List<QuestionClassification> classifications, List<QuestionType> types) {
        List<MQuestion> questions = questionRepository.getQuestionsByClassAndType(classifications, types);
        // TODO: filter visited questions

        MQuestion q = questions.stream().skip(random.nextInt(questions.size()))
                .findFirst().orElseThrow(() -> new BusinessException("题库已经没有题了"));

        List<String> solutions = null;
        if (q.getType().equals(SingleChoice) || q.getType().equals(MultiChoice)) {
            List<QuestionSolution> questionSolution = questionSolutionRepository.getByQuestionId(q.getQuestionId());
            solutions  = questionSolution.stream().map(QuestionSolution::getSolution).collect(Collectors.toList());
        }
        Optional<List<String>> solution = Optional.ofNullable(solutions);
        logger.info("show question: {} {}", q.getType(), q.getDescription());
        return new Question(q.getQuestionId(), q.getDescription(), solution, q.getClassification(), q.getType());
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
                               String answerDescription, Optional<List<String>> solutions) {
        String trimQuestion = questionDescription.trim();
        String trimAnswer = correctAnswer.trim();
        validationService.ensureNonEmptyString(trimQuestion, "问题描述");
        validationService.ensureNonEmptyString(trimAnswer, "正确答案");

        Long newQuestionId = questionIdGenerator.generateId();

        MQuestion partialQuestion = new MQuestion(newQuestionId, trimQuestion, type, classification,
                trimAnswer, answerDescription, false, studentId);
        questionRepository.createQuestion(partialQuestion);

        if (type.equals(SingleChoice) || type.equals(MultiChoice)) {
            List<String> nonNullSolutions = solutions.filter(s -> !s.isEmpty())
                    .orElseThrow(() -> new BusinessException("选择题必须包含选项"));

            if (!nonNullSolutions.contains(correctAnswer)) throw new BusinessException("正确答案必须包含在选项中");
            nonNullSolutions.forEach(s -> {
                questionSolutionRepository.createQuestionSolution(newQuestionId, s);
            });
        }
    }

    // TODO: maybe show similar question
}
