package com.tangenta.utils.generator;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class StudentIdGenerator implements IdGenerator {
    @Value("${student-id-padding-digit-number}")
    private int paddingDigitNumber;

    private AtomicLong id;
    private long currentYear;

    public StudentIdGenerator(Long id) {
        this.id = new AtomicLong(id);
        updateToNewYear();
    }

    @Override
    public Long generateId() {
        if (LocalDate.now().getYear() != currentYear) {
            updateToNewYear();
        }
        // TODO: check overflow exception
        return id.incrementAndGet();
    }

    @Override
    public Long currentId() {
        return id.get();
    }

    private synchronized void updateToNewYear() {
        currentYear = (long)LocalDate.now().getYear();
        id = new AtomicLong(currentYear * (long)Math.pow(10, paddingDigitNumber));
    }
}
