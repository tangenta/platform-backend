package com.tangenta.repositories.impl;

import com.tangenta.data.mapper.DoneTagMapper;
import com.tangenta.data.mapper.QuestionStatisticMapper;
import com.tangenta.data.mapper.StatisticMapper;
import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.AnswerCountDatePair;
import com.tangenta.data.pojo.mybatis.DoneTag;
import com.tangenta.data.pojo.mybatis.MStatistic;
import com.tangenta.data.pojo.mybatis.QuestionStatistic;
import com.tangenta.repositories.StatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
@Profile("dev")
public class MyStatisticRepository implements StatisticRepository {
    private static Logger logger = LoggerFactory.getLogger(MyStatisticRepository.class);
    private StatisticMapper statisticMapper;
    private QuestionStatisticMapper questionStatisticMapper;
    private DoneTagMapper doneTagMapper;

    public MyStatisticRepository(StatisticMapper statisticMapper, QuestionStatisticMapper questionStatisticMapper, DoneTagMapper doneTagMapper) {
        this.statisticMapper = statisticMapper;
        this.questionStatisticMapper = questionStatisticMapper;
        this.doneTagMapper = doneTagMapper;
    }

    @Override
    public List<MStatistic> allStatistics() {
        return statisticMapper.allStatistics();
    }

    @Override
    public MStatistic getUserStatisticByStudentId(Long studentId) {
        return statisticMapper.getByStudentId(studentId);
    }

    @Override
    public QuestionStatistic getByKeys(Long studentId, QuestionClassification classification, QuestionType type) {
        return questionStatisticMapper.findByKeys(studentId, classification, type);
    }

    @Override
    public List<QuestionStatistic> getQuestionStatisticByStudentId(Long studentId) {
        return questionStatisticMapper.findByStudentId(studentId);
    }

    @Override
    public void insertQuestionStatistic(Long studentId, QuestionClassification classification, QuestionType type, Long total, Long correct) {
        questionStatisticMapper.insert(
                new QuestionStatistic(studentId, classification, type, total, correct));
    }

    @Override
    public void updateQuestionStatistic(Long studentId, QuestionClassification classification, QuestionType type, Long total, Long correct) {
        questionStatisticMapper.update(
                new QuestionStatistic(studentId, classification, type, total, correct));
    }

    @Override
    public DoneTag getDoneTagByKeys(Long studentId, Long questionId) {
        return doneTagMapper.findByStudentAndQuestion(studentId, questionId);
    }

    @Override
    public void insertDoneTag(Long studentId, Long questionId, LocalDate doneDate) {
        doneTagMapper.insert(studentId, questionId, doneDate);
    }

    @Override
    public List<AnswerCountDatePair> countAndGroupByDate(Long studentId) {
        return doneTagMapper.countAndGroupByDate(studentId);
    }


}
