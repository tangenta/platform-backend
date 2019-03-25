package com.tangenta.repositories;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.AnswerCountDatePair;
import com.tangenta.data.pojo.mybatis.DoneTag;
import com.tangenta.data.pojo.mybatis.MStatistic;
import com.tangenta.data.pojo.mybatis.QuestionStatistic;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface StatisticRepository {
    MStatistic getUserStatisticByStudentId(Long studentId);

    QuestionStatistic getByKeys(Long studentId, QuestionClassification classification,
                                QuestionType type);

    List<QuestionStatistic> getQuestionStatisticByStudentId(Long studentId);

    void insertQuestionStatistic(Long studentId, QuestionClassification classification,
                                 QuestionType type, Long total, Long correct);

    void updateQuestionStatistic(Long studentId, QuestionClassification classification,
                                 QuestionType type, Long total, Long correct);

    DoneTag getDoneTagByKeys(Long studentId, Long questionId);

    void insertDoneTag(Long studentId, Long questionId, LocalDate doneDate);

    List<AnswerCountDatePair> countAndGroupByDate(Long studentId);


}
