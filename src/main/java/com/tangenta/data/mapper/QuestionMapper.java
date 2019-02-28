package com.tangenta.data.mapper;

import com.tangenta.data.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Select("select * from question")
    @Results({
            @Result(property = "questionId", column = "question_id", id = true),
            @Result(property = "description", column = "question_description"),
            @Result(property = "correctAnswer", column = "correct_answer"),
            @Result(property = "answerDescription", column = "answer_description"),
            @Result(property = "isPass", column = "ispass"),
            @Result(property = "belongToStudentId", column = "Student_id"),
    })
    List<Question> getAllQuestions();
}
