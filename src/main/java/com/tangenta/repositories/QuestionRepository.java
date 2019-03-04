package com.tangenta.repositories;

import com.tangenta.data.pojo.Question;

import java.util.List;

public interface QuestionRepository {
    List<Question> getAllQuestions();
}
