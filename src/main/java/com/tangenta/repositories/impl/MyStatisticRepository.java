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
import java.util.List;
import java.util.Optional;

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
    public Optional<MStatistic> getUserStatisticByStudentId(Long studentId) {
        return Optional.ofNullable(statisticMapper.getByStudentId(studentId));
    }

    @Override
    public void increaseQuestionCreation(Long studentId) {
        statisticMapper.increaseCreateQuestion(studentId);
    }

    @Override
    public void increaseQuestionPassing(Long studentId) {
        statisticMapper.increasePassQuestion(studentId);
    }

    @Override
    public void updateStatistic(MStatistic mStatistic) {
        statisticMapper.update(mStatistic);
    }

    @Override
    public QuestionStatistic getByKeys(Long studentId, QuestionClassification classification, QuestionType type) {
        return questionStatisticMapper.findByKeys(studentId, classification, type);
    }

    @Override
    public List<QuestionStatistic> getQuestionStatisticGroupByClasses(Long studentId) {
        return questionStatisticMapper.findByStudentIdGroupByClass(studentId);
    }

    @Override
    public List<QuestionStatistic> getQuestionStatisticGroupByTypes(Long studentId) {
        return questionStatisticMapper.findByStudentIdGroupByType(studentId);
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
