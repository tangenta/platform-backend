package com.tangenta.repositories;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.data.pojo.mybatis.QuestionSolution;

import java.util.List;

public interface QuestionRepository {
    List<MQuestion> getAllQuestions();
    List<MQuestion> getQuestionsByClassAndType(List<QuestionClassification> classifications,
                                               List<QuestionType> types);
    MQuestion findQuestionById(Long questionId);
    void createQuestion(MQuestion partialQuestion);
}
