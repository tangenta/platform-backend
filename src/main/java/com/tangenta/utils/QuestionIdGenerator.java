package com.tangenta.utils;

import com.tangenta.data.mapper.QuestionIdFetchingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

public class QuestionIdGenerator {
    private static Logger logger = LoggerFactory.getLogger(QuestionIdGenerator.class);
    private final AtomicLong id;
    public QuestionIdGenerator(Long currentMaxQuestionId) {
        id = new AtomicLong(currentMaxQuestionId);
    }

    public Long generateId() {
        return id.incrementAndGet();
    }

    public Long currentId() {
        return id.get();
    }

}
