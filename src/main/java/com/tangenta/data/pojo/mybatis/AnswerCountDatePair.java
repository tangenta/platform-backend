package com.tangenta.data.pojo.mybatis;

import java.time.LocalDate;

public class AnswerCountDatePair {
    private int count;
    private LocalDate date;

    public AnswerCountDatePair() {
    }

    public AnswerCountDatePair(int number, LocalDate date) {
        this.count = number;
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public LocalDate getDate() {
        return date;
    }
}
