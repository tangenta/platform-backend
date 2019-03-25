package com.tangenta.service;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.graphql.AnswerStatistic;
import com.tangenta.data.pojo.graphql.Feedback;
import com.tangenta.data.pojo.graphql.StudentStatistic;
import com.tangenta.data.pojo.mybatis.*;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.QuestionRepository;
import com.tangenta.repositories.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import static com.tangenta.utils.Utils.orElse;

@Service
public class StatisticService {
    private static Logger logger = LoggerFactory.getLogger(StatisticService.class);
    private QuestionRepository questionRepository;
    private StatisticRepository statisticRepository;

    public StatisticService(QuestionRepository questionRepository, StatisticRepository statisticRepository) {
        this.questionRepository = questionRepository;
        this.statisticRepository = statisticRepository;
    }

    public void storeUsersFeedback(Long studentId, Feedback feedback) {
        MQuestion question = questionRepository.findQuestionById(feedback.getQuestionId());
        if (question == null) throw new BusinessException("该问题不存在");

        DoneTag doneTag = statisticRepository.getDoneTagByKeys(studentId, question.getQuestionId());
        if (doneTag != null) throw new BusinessException("该题已被做过");

        QuestionClassification cls = question.getClassification();
        QuestionType type = question.getType();

        QuestionStatistic qs = statisticRepository.getByKeys(studentId, cls, type);

        Long correctScore = feedback.isCorrect() ? 1L : 0L;

        // fixme: not thread safe
        if (qs == null) {
            statisticRepository.insertQuestionStatistic(studentId, cls, type, 1L, correctScore);
        } else {
            statisticRepository.updateQuestionStatistic(studentId, cls, type, qs.getTotal() + 1,
                    qs.getCorrect() + correctScore);
        }
        statisticRepository.insertDoneTag(studentId, question.getQuestionId(), LocalDate.now());

    }

    public AnswerStatistic showAnswerStatistic(Long studentId, List<QuestionClassification> classes, List<QuestionType> types) {
        AnswerStatistic id = new AnswerStatistic(studentId, 0L, 0L);
        BinaryOperator<AnswerStatistic> binOp = (a, b) -> new AnswerStatistic(studentId,
                a.getCorrect() + b.getCorrect() , a.getTotal() + b.getTotal());
        return classes.stream()
                .map(c ->
                    types.stream()
                            .map(t -> statisticRepository.getByKeys(studentId, c, t))
                            .map(qs -> new AnswerStatistic(studentId, qs.getCorrect(), qs.getTotal()))
                            .reduce(id, binOp))
                .reduce(id, binOp);
    }

    public List<AnswerStatistic> showAnswerStatisticByClass(Long studentId, List<QuestionClassification> classes) {
        List<QuestionStatistic> questionStatistics = statisticRepository.getQuestionStatisticByStudentId(studentId);
        return classes.stream()
                .map(cls -> questionStatistics.stream()
                    .filter(qs -> qs.getClassification().equals(cls))
                    .findFirst()
                    .orElse(new QuestionStatistic(studentId, cls, null, 0L, 0L)))
                .map(qs -> new AnswerStatistic(studentId, qs.getCorrect(), qs.getTotal()))
                .collect(Collectors.toList());
    }

    public List<AnswerCountDatePair> answersCountRecently(Long studentId, List<LocalDate> dates) {
        List<AnswerCountDatePair> found =  statisticRepository.countAndGroupByDate(studentId).stream()
                .filter(acPair -> dates.stream()
                        .anyMatch(inputDate -> inputDate.equals(acPair.getDate())))
                .collect(Collectors.toList());
        return dates.stream().map(date -> found.stream()
                .filter(p -> p.getDate().equals(date))
                .findFirst()
                .orElse(new AnswerCountDatePair(0, date)))
                .collect(Collectors.toList());
    }

    public StudentStatistic getStudentStatistic(Long studentId) {
        MStatistic ms = statisticRepository.getUserStatisticByStudentId(studentId);
        Long offline = orElse(ms.getOfflineLearningTime(), 0L);
        Long online =  orElse(ms.getOnlineLearningTime(), 0L);
        Long createQuesNum =  orElse(ms.getPostQuestionNumber(), 0L);
        Long pasQuesNum =  orElse(ms.getPassQuestionNumber(), 0L);
        Long answerQuestionNumber =  orElse(ms.getAnswerQuestionNumber(), 0L);
        double attendanceRate =  orElse(ms.getAttendanceRate(), 0.0);
        double paperScore =  orElse(ms.getPaperScore(), 0.0);
        double homeworkScore =  orElse(ms.getHomeworkScore(), 0.0);
        double annualScore = orElse(ms.getAnnualScore(), 0.0);


        return new StudentStatistic(offline, online,
                createQuesNum, pasQuesNum, attendanceRate,
                paperScore, homeworkScore, annualScore, answerQuestionNumber);
    }
}
