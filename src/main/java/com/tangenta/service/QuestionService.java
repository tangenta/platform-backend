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
import com.tangenta.repositories.StatisticRepository;
import com.tangenta.utils.AnswerConverter;
import com.tangenta.utils.generator.IdGenerator;
import com.tangenta.utils.generator.QuestionIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static com.tangenta.data.pojo.QuestionType.*;

@Service
public class QuestionService {
    private static Logger logger = LoggerFactory.getLogger(QuestionService.class);
    private Random random = new Random();

    private QuestionRepository questionRepository;
    private ValidationService validationService;
    private IdGenerator questionIdGenerator;
    private QuestionSolutionRepository questionSolutionRepository;
    private StatisticRepository statisticRepository;

    public QuestionService(QuestionRepository questionRepository, ValidationService validationService,
                           @Qualifier("questionIdGenerator") IdGenerator questionIdGenerator,
                           QuestionSolutionRepository questionSolutionRepository,
                           StatisticRepository statisticRepository) {
        this.questionRepository = questionRepository;
        this.validationService = validationService;
        this.questionIdGenerator = questionIdGenerator;
        this.questionSolutionRepository = questionSolutionRepository;
        this.statisticRepository = statisticRepository;
    }

    public Question randomQuestion(Long studentId, List<QuestionClassification> classifications, List<QuestionType> types) {
        List<MQuestion> questions = questionRepository.getQuestionsByClassAndType(classifications, types);

        // filter visited question
        List<MQuestion> filteredQuestions =  studentId == null ? questions :
                questions.stream().filter(q ->
                        statisticRepository.getDoneTagByKeys(studentId, q.getQuestionId()) == null)
                .collect(Collectors.toList());

        if (filteredQuestions.isEmpty()) throw new BusinessException("题库已经没有题了");

        MQuestion q = filteredQuestions.stream()
                .skip(random.nextInt(filteredQuestions.size()))
                .findFirst().orElseThrow(() -> new BusinessException("题库已经没有题了"));

        List<String> solutions = null;
        if (q.getType().equals(SingleChoice) || q.getType().equals(MultipleChoice)) {
            List<QuestionSolution> questionSolution = questionSolutionRepository.getByQuestionId(q.getQuestionId());
            solutions  = questionSolution.stream().map(QuestionSolution::getOption).collect(Collectors.toList());
        }
        Optional<List<String>> solution = Optional.ofNullable(solutions);
        logger.info("show question: {} {} {}", q.getType(), q.getDescription(), solution);
        return new Question(q.getQuestionId(), q.getDescription(), solution, q.getClassification(), q.getType());
    }

    public Feedback validateAnswer(Long questionId, String answer) {
        validationService.ensureQuestionExistence(questionId);

        MQuestion q = questionRepository.findQuestionById(questionId);
        // TODO: build a robust validation system
        String correctAnswer;
        if (q.getType().equals(TrueOrFalse)) {
            correctAnswer = AnswerConverter.convertTrueOrFalse(q.getCorrectAnswer());
        } else {
            correctAnswer = q.getCorrectAnswer();
        }
        boolean isCorrect = correctAnswer.equals(answer);
        return new Feedback(questionId, isCorrect, q.getAnswerDescription(), correctAnswer);
    }

    public void createQuestion(Long studentId, String questionDescription, QuestionType type,
                               QuestionClassification classification, String correctAnswer,
                               String answerDescription, List<String> solutions) {
        String trimQuestion = questionDescription.trim();
        String trimAnswer = correctAnswer.trim();
        validationService.ensureNonEmptyString(trimQuestion, "问题描述");
        validationService.ensureNonEmptyString(trimAnswer, "正确答案");

        Long newQuestionId = questionIdGenerator.generateId();

        MQuestion partialQuestion = new MQuestion(newQuestionId, trimQuestion, type, classification,
                trimAnswer, answerDescription, false, studentId);
        questionRepository.createQuestion(partialQuestion);
        statisticRepository.increaseQuestionCreation(studentId);

        if (type.equals(SingleChoice) || type.equals(MultipleChoice)) {
            if (solutions == null || solutions.isEmpty())
                throw new BusinessException("选择题必须包含选项");

            if (!solutions.contains(correctAnswer))
                throw new BusinessException("正确答案必须包含在选项中");
            solutions.forEach(s -> {
                questionSolutionRepository.createQuestionSolution(newQuestionId, s);
            });
        }
    }

    // TODO: maybe show similar question
}
