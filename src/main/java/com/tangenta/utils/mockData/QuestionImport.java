package com.tangenta.utils.mockData;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.data.pojo.mybatis.QuestionSolution;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class QuestionImport {
    private List<MQuestion> questions = new ArrayList<>();
    private List<QuestionSolution> questionSolutions = new ArrayList<>();

    public List<MQuestion> readQuestion() {return questions;}
    public List<QuestionSolution> readQuestionSolution() {return questionSolutions;}

    public void questionImport() {
        File file = new File("D:\\questionData\\question.txt");
        BufferedReader bufferedReader = null;

        Long questionId = 0L;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String string = null;
            String[] strings = null;
            while ((string = bufferedReader.readLine()) != null) {
                strings = string.split("\t");
                List<String> option = Arrays.asList(strings[3].split(" "));

                QuestionClassification questionClassification = null;
                switch (strings[1]) {
                    case "0":
                        questionClassification = QuestionClassification.Lilunjichu;
                        break;
                    case "1":
                        questionClassification = QuestionClassification.Jilvxing;
                        break;
                    case "2":
                        questionClassification = QuestionClassification.Jiazhiguan;
                        break;
                    case "3":
                        questionClassification = QuestionClassification.Daodepingjia;
                        break;
                    case "4":
                        questionClassification = QuestionClassification.Sixiangxianjinxing;
                        break;
                }
                QuestionType questionType = null;
                switch (strings[2]) {
                    case "0":
                        questionType = QuestionType.SingleChoice;
                        break;
                    case "1":
                        questionType = QuestionType.MultipleChoice;
                        break;
                    case "2":
                        questionType = QuestionType.TrueOrFalse;
                        break;
                    case "3":
                        questionType = QuestionType.BlanksFilling;
                        break;
                }
                questions.add(new MQuestion(questionId, strings[3], questionType, questionClassification,strings[4],strings[5],true, 0L));
                if(!option.isEmpty()){
                    for(int i = 0;i<option.size();i++)
                        if(!option.get(i).isEmpty())
                            questionSolutions.add(new QuestionSolution(questionId, option.get(i).substring(2)));

                }
                questionId++;
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                }
            }
        }

    }
}
