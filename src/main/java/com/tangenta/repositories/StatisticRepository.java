package com.tangenta.repositories;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.DoneTag;
import com.tangenta.data.pojo.mybatis.MStatistic;
import com.tangenta.data.pojo.mybatis.QuestionStatistic;

import java.util.Date;

public interface StatisticRepository {
    MStatistic getByStudentId(Long studentId);

    QuestionStatistic getQuestionStatisticByKeys(Long studentId, QuestionClassification classification,
                                                 QuestionType type);

    void insertQuestionStatistic(Long studentId, QuestionClassification classification,
                                 QuestionType type, Long total, Long correct);

    void updateQuestionStatistic(Long studentId, QuestionClassification classification,
                                 QuestionType type, Long total, Long correct);

    DoneTag getDoneTagByKeys(Long studentId, Long questionId);

    void insertDoneTag(Long studentId, Long questionId, Date doneDate);
}
