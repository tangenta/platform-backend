package com.tangenta.data.pojo.mybatis;

import java.util.Date;

public class AnswerCountDatePair {
    private int count;
    private Date date;

    public AnswerCountDatePair() {
    }

    public AnswerCountDatePair(int number, Date date) {
        this.count = number;
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public Date getDate() {
        return date;
    }
}
