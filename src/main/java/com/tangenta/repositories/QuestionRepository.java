package com.tangenta.repositories;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.MQuestion;

import java.util.List;

public interface QuestionRepository {
    List<MQuestion> getAllQuestions();
    List<MQuestion> getQuestionsByClassAndType(QuestionClassification classification, QuestionType type);
    MQuestion findQuestionById(Long questionId);
    void createQuestion(MQuestion partialQuestion);
}
