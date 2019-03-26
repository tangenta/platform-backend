package com.tangenta.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StatisticRepositoryMonitor {
    private static Logger logger = LoggerFactory.getLogger(StatisticRepositoryMonitor.class);
    private boolean isDirty = true;

    @AfterReturning("execution(* com.tangenta.repositories.StatisticRepository.increaseQuestionCreation(..))" +
            "&& execution(* com.tangenta.repositories.StatisticRepository.increaseQuestionPassing(..))" +
            "&& execution(* com.tangenta.repositories.StatisticRepository.updateQuestionStatistic(..))")
    public void accessedStatisticRepository() {
        logger.info("after statistic repository monitor called.");
    }

    @Before("execution(* com.tangenta.repositories.StatisticRepository.getByKeys(..))")
    public void test() {
        logger.info("aop work!");
    }
}
