package com.tangenta.aop;

import com.google.common.collect.Streams;
import com.tangenta.data.pojo.mybatis.MStatistic;
import com.tangenta.repositories.StatisticRepository;
import com.tangenta.utils.Critic;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

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
        if (weight == null) updatedScores(statisticRepository.allStatistics());
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
                updatedScores(statisticRepository.allStatistics());
                logger.info("Weight updated.");
            } else {
                logger.info("Weight is already updated.");
            }
            weightIsDirty = false;
        }, 0, weightCalDelayMinutes, TimeUnit.MINUTES);
    }


    private List<Double> pack(List<List<Double>> indicators, int[][] indexes) {
        double[][] result = indicators.stream()
                .map(ds -> ds.stream().mapToDouble(Double::doubleValue).toArray())
                .toArray(double[][]::new);

        double[] localWeights = Critic.CRITIC(result);
        for (int i = 0; i != localWeights.length; ++i) {
            for (int j = 0; j != indexes[i].length; ++j) {
                weight[indexes[i][j]] *= localWeights[i];
            }
        }

        return Streams.zip(indicators.stream(), DoubleStream.of(localWeights).boxed(),
                (column, weight) -> column.stream()
                        .map(x -> x * weight)
                        .collect(Collectors.toList()))
                .reduce(Collections.nCopies(result[0].length, 0.0), (columnA, columnB) ->
                        Streams.zip(columnA.stream(), columnB.stream(), (a, b) -> a + b).collect(Collectors.toList()));
    }

    private void updatedScores(List<MStatistic> allStatistics) {
        weight = IntStream.range(0, 9).mapToDouble(x -> 1.0).toArray();
        pack(new ArrayList<List<Double>>(){{
            add(extractKey(allStatistics, s -> (double)s.getAnswerQuestionNumber()));
            add(extractKey(allStatistics, MStatistic::getAnnualScore));
            add(pack(new ArrayList<List<Double>>(){{
                add(pack(new ArrayList<List<Double>>(){{
                    add(extractKey(allStatistics, s -> (double)s.getOnlineLearningTime()));
                    add(extractKey(allStatistics, s -> (double)s.getOfflineLearningTime()));
                }}, new int[][]{{0}, {1}}));
                add(pack(new ArrayList<List<Double>>(){{
                    add(extractKey(allStatistics, s -> (double)s.getPostQuestionNumber()));
                    add(extractKey(allStatistics, s -> (double)s.getPassQuestionNumber()));
                }}, new int[][]{{2}, {3}}));
                add(extractKey(allStatistics, MStatistic::getAttendanceRate));
                add(extractKey(allStatistics, MStatistic::getHomeworkScore));
                add(extractKey(allStatistics, MStatistic::getPaperScore));
            }}, new int[][]{{0, 1}, {2, 3}, {4}, {5}, {6}}));
        }}, new int[][]{{8}, {7}, {0, 1, 2, 3, 4, 5, 6}});
    }

    private List<Double> extractKey(List<MStatistic> allStatistics, Function<MStatistic, Double> extractor) {
        return allStatistics.stream().map(extractor).collect(Collectors.toList());
    }
}
