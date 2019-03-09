package com.tangenta.service;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.graphql.Feedback;
import com.tangenta.data.pojo.mybatis.DoneTag;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.data.pojo.mybatis.QuestionStatistic;
import com.tangenta.exceptions.BusinessException;
import com.tangenta.repositories.QuestionRepository;
import com.tangenta.repositories.StatisticRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StatisticService {
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

        QuestionStatistic qs = statisticRepository.getQuestionStatisticByKeys(studentId, cls, type);

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

}
