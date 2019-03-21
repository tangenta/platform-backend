package com.tangenta.service;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.graphql.AnswerStatistic;
import com.tangenta.data.pojo.graphql.Feedback;
import com.tangenta.data.pojo.mybatis.AnswerCountDatePair;
import com.tangenta.data.pojo.mybatis.DoneTag;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.data.pojo.mybatis.QuestionStatistic;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.QuestionRepository;
import com.tangenta.repositories.StatisticRepository;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.StaticScopeForKotlinEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        statisticRepository.insertDoneTag(studentId, question.getQuestionId(), new Date());

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

    public List<AnswerCountDatePair> answersCountRecently(Long studentId, List<Date> dates) {
        List<AnswerCountDatePair> found =  statisticRepository.countAndGroupByDate(studentId).stream()
                .filter(acPair -> dates.contains(acPair.getDate()))
                .collect(Collectors.toList());
        dates.forEach(d -> logger.info(d.toString()));
        statisticRepository.countAndGroupByDate(studentId).forEach(d -> logger.info(d.getDate().toString()));
        return dates.stream().map(date -> found.stream()
                .filter(p -> p.getDate().equals(date))
                .findFirst()
                .orElse(new AnswerCountDatePair(0, date))).collect(Collectors.toList());
    }
}
