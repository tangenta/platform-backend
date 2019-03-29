package com.tangenta.utils.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

public class QuestionIdGenerator implements IdGenerator {
    private static Logger logger = LoggerFactory.getLogger(QuestionIdGenerator.class);
    private final AtomicLong id;
    public QuestionIdGenerator(Long currentMaxQuestionId) {
        id = new AtomicLong(currentMaxQuestionId);
    }

    @Override
    public Long generateId() {
        return id.incrementAndGet();
    }

    @Override
    public Long currentId() {
        return id.get();
    }

}
