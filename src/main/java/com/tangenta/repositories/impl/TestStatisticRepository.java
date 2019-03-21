package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.AnswerCountDatePair;
import com.tangenta.data.pojo.mybatis.DoneTag;
import com.tangenta.data.pojo.mybatis.MStatistic;
import com.tangenta.data.pojo.mybatis.QuestionStatistic;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static com.tangenta.data.pojo.QuestionClassification.Lilunjichu;
import static com.tangenta.data.pojo.QuestionType.BlanksFilling;

@Repository
@Profile("dev-test")
public class TestStatisticRepository implements StatisticRepository {
    private static Logger logger = LoggerFactory.getLogger(TestStatisticRepository.class);

    private static List<QuestionStatistic> allQuestionStatistic = new LinkedList<QuestionStatistic>() {{
        add(new QuestionStatistic(1L, Lilunjichu, BlanksFilling, 3L, 1L));
        add(new QuestionStatistic(2L, Lilunjichu, BlanksFilling, 1L, 1L));
        add(new QuestionStatistic(3L, Lilunjichu, BlanksFilling, 2L, 1L));
    }};

    private static Date buildDate(int year, int month, int day) {
        return new GregorianCalendar(year, month, day).getTime();
    }

    private static List<DoneTag> mockDoneTag = new LinkedList<DoneTag>() {{
        add(new DoneTag(1L, 1L, buildDate(2018, 5, 12)));
        add(new DoneTag(1L, 2L, buildDate(2018, 5, 12)));
        add(new DoneTag(1L, 3L, buildDate(2018, 5, 12)));
        add(new DoneTag(1L, 4L, buildDate(2018, 5, 13)));
        add(new DoneTag(1L, 5L, buildDate(2018, 5, 14)));
        add(new DoneTag(1L, 6L, buildDate(2018, 5, 15)));
        add(new DoneTag(1L, 7L, buildDate(2018, 5, 15)));
        add(new DoneTag(1L, 8L, buildDate(2018, 5, 16)));
        add(new DoneTag(1L, 9L, new Date()));
        add(new DoneTag(1L, 10L, new Date()));
    }};


    @Override
    public MStatistic getUserStatisticByStudentId(Long studentId) {
        throw new BusinessException("不支持操作");
    }

    @Override
    public QuestionStatistic getByKeys(Long studentId, QuestionClassification classification, QuestionType type) {
        for (QuestionStatistic qs: allQuestionStatistic) {
            if (qs.getStudentId().equals(studentId) && qs.getClassification().equals(classification)
                    && qs.getType().equals(type)) return qs;
        }
        return null;
    }

    @Override
    public List<QuestionStatistic> getQuestionStatisticByStudentId(Long studentId) {
        return allQuestionStatistic.stream()
                .filter(qs -> qs.getStudentId().equals(studentId))
                .collect(Collectors.groupingBy(
                        QuestionStatistic::getClassification,
                        Collectors.reducing(
                                (QuestionStatistic a, QuestionStatistic b) ->
                            new QuestionStatistic(studentId, b.getClassification(), null,
                                    a.getTotal() + b.getTotal(), a.getCorrect() + b.getCorrect())
                        ))).values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public void insertQuestionStatistic(Long studentId, QuestionClassification classification, QuestionType type, Long total, Long correct) {
        allQuestionStatistic.add(new QuestionStatistic(studentId, classification, type, total, correct));
    }

    @Override
    public void updateQuestionStatistic(Long studentId, QuestionClassification classification, QuestionType type, Long total, Long correct) {
        QuestionStatistic copied = null;

        Iterator<QuestionStatistic> iter = allQuestionStatistic.iterator();
        while (iter.hasNext()) {
            QuestionStatistic qs = iter.next();
            if (qs.getStudentId().equals(studentId) && qs.getClassification().equals(classification)
                    && qs.getType().equals(type)) {
                copied = new QuestionStatistic(qs.getStudentId(), qs.getClassification(), qs.getType(),
                        total, correct);
                iter.remove();
                break;
            }
        }
        if (copied == null) {
            throw new BusinessException("找不到更新对象");
        } else {
            allQuestionStatistic.add(copied);
        }

    }

    @Override
    public DoneTag getDoneTagByKeys(Long studentId, Long questionId) {
        for (DoneTag dt: mockDoneTag) {
            if (dt.getStudentId().equals(studentId) && dt.getQuestionId().equals(questionId)) {
                return dt;
            }
        }
        return null;
    }

    @Override
    public void insertDoneTag(Long studentId, Long questionId, Date doneDate) {
        mockDoneTag.add(new DoneTag(studentId, questionId, doneDate));
    }

    @Override
    public List<AnswerCountDatePair> countAndGroupByDate(Long studentId) {
        List<AnswerCountDatePair> answerCountDatePairs = new LinkedList<>();
        mockDoneTag.stream()
                .filter(dt -> dt.getStudentId().equals(studentId))
                .collect(Collectors.groupingBy(DoneTag::getDoneDate,
                        Collectors.counting()))
            .forEach((date, count) -> answerCountDatePairs.add(
                new AnswerCountDatePair(count.intValue(), date)));
        return answerCountDatePairs;
    }
}
