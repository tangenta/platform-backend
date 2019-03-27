package com.tangenta.aop;

import com.tangenta.data.pojo.mybatis.MStatistic;
import com.tangenta.repositories.StatisticRepository;
import com.tangenta.utils.Critic;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.ujmp.core.Matrix;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Aspect
@Component
public class WeightProvider {
    private static Logger logger = LoggerFactory.getLogger(WeightProvider.class);

    private volatile double[] weight = null;
    private volatile boolean weightIsDirty = true;
    private final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(2);
    private final StatisticRepository statisticRepository;

    @Value("${weight-calculate-delay-minutes}")
    private Long weightCalDelayMinutes;

    public WeightProvider(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public double[] fetch() {
        if (weight == null) return updatedWeight(statisticRepository.allStatistics());
        return weight;
    }

    @AfterReturning("execution(* com.tangenta.repositories.StatisticRepository.increaseQuestionCreation(..))" +
            "&& execution(* com.tangenta.repositories.StatisticRepository.increaseQuestionPassing(..))" +
            "&& execution(* com.tangenta.repositories.StatisticRepository.updateQuestionStatistic(..))")
    private void accessedStatisticRepository() {
        weightIsDirty = true;
    }

    @PostConstruct
    void launchScheduleThread() {
        exec.scheduleWithFixedDelay(() -> {
            if (weightIsDirty) {
                logger.info("Updating weight...");
                weight = updatedWeight(statisticRepository.allStatistics());
                logger.info("Weight updated.");
            } else {
                logger.info("Weight is already updated.");
            }
            weightIsDirty = false;
        }, 0, weightCalDelayMinutes, TimeUnit.MINUTES);
    }

    private double[] updatedWeight(List<MStatistic> allStatistics) {
        List<double[]> result = allStatistics.stream()
                .map(s ->
                        new double[]{s.getOfflineLearningTime(), s.getOnlineLearningTime(),
                                s.getPostQuestionNumber(), s.getPassQuestionNumber(), s.getAttendanceRate(),
                                s.getPaperScore(), s.getHomeworkScore(), s.getAnnualScore(), s.getAnswerQuestionNumber(),
                                s.getAnswerQuestionScore()}
                ).collect(Collectors.toList());

        double[][] dresult = new double[allStatistics.size()][];
        for (int i = 0; i != allStatistics.size(); ++i) {
            dresult[i] = result.get(i);
        }

        Matrix matrix = Matrix.Factory.importFromArray(dresult);
        boolean[] booleanArr = new boolean[10];
        Arrays.fill(booleanArr, true);
        return Critic.CRITIC(matrix, booleanArr);
    }
}
