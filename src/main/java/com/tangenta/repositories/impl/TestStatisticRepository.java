package com.tangenta.repositories.impl;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.DoneTag;
import com.tangenta.data.pojo.mybatis.MStatistic;
import com.tangenta.data.pojo.mybatis.QuestionStatistic;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.StatisticRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import static com.tangenta.data.pojo.QuestionClassification.*;
import static com.tangenta.data.pojo.QuestionType.*;

@Repository
@Profile("dev-test")
public class TestStatisticRepository implements StatisticRepository {
    private static List<QuestionStatistic> mockQuestionStatistic = new LinkedList<QuestionStatistic>() {{
//        add(new QuestionStatistic(1L, Lilunjichu, BlanksFilling, 3L, 1L));
//        add(new QuestionStatistic(2L, Lilunjichu, BlanksFilling, 1L, 1L));
//        add(new QuestionStatistic(3L, Lilunjichu, BlanksFilling, 2L, 1L));
    }};

    private static List<DoneTag> mockDoneTag = new LinkedList<DoneTag>() {{
//        add(new DoneTag(1L, 1L, new Date()));
//        add(new DoneTag(2L, 1L, new Date()));
//        add(new DoneTag(3L, 1L, new Date()));
    }};


    @Override
    public MStatistic getByStudentId(Long studentId) {
        throw new BusinessException("不支持操作");
    }

    @Override
    public QuestionStatistic getQuestionStatisticByKeys(Long studentId, QuestionClassification classification, QuestionType type) {
        for (QuestionStatistic qs: mockQuestionStatistic) {
            if (qs.getStudentId().equals(studentId) && qs.getClassification().equals(classification)
                    && qs.getType().equals(type)) return qs;
        }
        return null;
    }

    @Override
    public void insertQuestionStatistic(Long studentId, QuestionClassification classification, QuestionType type, Long total, Long correct) {
        mockQuestionStatistic.add(new QuestionStatistic(studentId, classification, type, total, correct));
    }

    @Override
    public void updateQuestionStatistic(Long studentId, QuestionClassification classification, QuestionType type, Long total, Long correct) {
        QuestionStatistic copied = null;

        Iterator<QuestionStatistic> iter = mockQuestionStatistic.iterator();
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
            mockQuestionStatistic.add(copied);
        }

    }

    @Override
    public DoneTag getDoneTagByKeys(Long studentId, Long questionId) {
        for (DoneTag dt: mockDoneTag) {
            if (dt.getQuestionId().equals(studentId) && dt.getQuestionId().equals(questionId)) {
                return dt;
            }
        }
        return null;
    }

    @Override
    public void insertDoneTag(Long studentId, Long questionId, Date doneDate) {
        mockDoneTag.add(new DoneTag(studentId, questionId, doneDate));
    }
}
