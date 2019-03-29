package com.tangenta.aop;

import com.tangenta.data.pojo.mybatis.MStatistic;
import com.tangenta.repositories.StatisticRepository;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OnlineTimeRecorder {
    private final Map<Long, Long> timeRecord = new ConcurrentHashMap<>();
    private final StatisticRepository statisticRepository;

    public OnlineTimeRecorder(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public void loggingIn(Long studentId) {
        timeRecord.put(studentId, System.currentTimeMillis());
    }

    public void loggedOut(Long studentId, Long lastAccessTime) {
        MStatistic m = statisticRepository.getUserStatisticByStudentId(studentId).orElse(new MStatistic());
        long increasedMinutes = Duration.ofMillis(lastAccessTime - timeRecord.get(studentId)).toMinutes();

        MStatistic newMStatistic = new MStatistic(studentId, m.getOfflineLearningTime(),
                m.getOnlineLearningTime() + increasedMinutes, m.getPostQuestionNumber(),
                m.getPassQuestionNumber(), m.getAttendanceRate(), m.getPaperScore(), m.getHomeworkScore(),
                m.getAnnualScore(), m.getAnswerQuestionNumber(), m.getAnswerQuestionScore());

        statisticRepository.updateStatistic(newMStatistic);
    }

}
