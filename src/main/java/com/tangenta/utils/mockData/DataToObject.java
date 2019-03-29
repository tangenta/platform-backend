package com.tangenta.utils.mockData;

import com.tangenta.data.pojo.mybatis.MStatistic;

import java.util.ArrayList;
import java.util.Random;

public class DataToObject {
    public static void main(String[] args) {
        dataImport().forEach(ms -> {
            System.out.println("add(new MStatistic(" +
                    ms.getStudentId() +
                    "L, " +
                    ms.getOfflineLearningTime() +
                    "L, " +
                    ms.getOnlineLearningTime() +
                    "L, " +
                    ms.getPostQuestionNumber() +
                    "L, " +
                    ms.getPassQuestionNumber() +
                    "L, " +
                    ms.getAttendanceRate() +
                    ", " +
                    ms.getPaperScore() +
                    ", " +
                    ms.getHomeworkScore() +
                    ", " +
                    ms.getAnnualScore() +
                    ", " +
                    ms.getAnswerQuestionNumber() +
                    "L, " +
                    ms.getAnswerQuestionScore() +
                    "L));");
        });
    }

    private static ArrayList<MStatistic> getData(Long studentId, Long offlineLearningTime,
                                                Long onlineLearningTime, Long postQuestionNumber, Long passQuestionNumber,
                                                Double attendanceRate,
                                                Double paperScore,
                                                Double homeworkScore,
                                                Double annualScore,
                                                Long answerQuestionNumber,
                                                Long answerQuestionScore, ArrayList<MStatistic> mStatistics) {

        mStatistics.add(new MStatistic(studentId, offlineLearningTime, onlineLearningTime, postQuestionNumber, passQuestionNumber,
                attendanceRate, paperScore, homeworkScore, annualScore, answerQuestionNumber, answerQuestionScore));

        return mStatistics;
    }

    private static long[][] Data(int userNumber, long min, long max, long[][] array) {
        for (int i = 0; i < userNumber; ++i) {
            for (int j = 0; j < array[0].length; ++j) {
                array[i][j] = 75 + new Random().nextInt(20 );
            }
        }
        return array;
    }

    public static ArrayList<MStatistic> dataImport(){
        int userNumber = 50;
        double min = 75.0;
        double max = 95.0;
        long Min = 75;
        long Max = 95;
        long id = 2017000001L;
        long[][] learningTime = new long[userNumber][2];
        long[][] gongXiandu = new long[userNumber][2];
        double[][] B = new double[userNumber][6];
        double[][] A = new double[userNumber][3];

//一级

        learningTime = Data(userNumber, Min, Max, learningTime);

        gongXiandu = Data(userNumber, Min, Max, gongXiandu);


//二级

        for (int i = 0; i < userNumber; ++i) {
            for (int j = 0; j < B[0].length; ++j) {
                if (j == 0)
                    B[i][j] = 0;
                else if (j == 1)
                    B[i][j] = 0;
                B[i][j] = min + ((max - min) * new Random().nextDouble());
            }
        }

//三级

        for (int i = 0; i < userNumber; ++i) {
            for (int j = 0; j < A[0].length; ++j) {
                if (j == 0)
                    A[i][j] = 0;
                A[i][j] = min + ((max - min) * new Random().nextDouble());
            }
        }

        //Scores(userNumber, A, scores, bufferedWriter, weightA)


        ArrayList<MStatistic> mStatistics = new ArrayList<>();
        for (int i = 0; i < userNumber; i++)
            mStatistics = DataToObject.getData(id++, learningTime[i][0], learningTime[i][1], gongXiandu[i][0], gongXiandu[i][1],
                    B[i][1], B[i][2], B[i][3], B[i][4], (long) A[i][1], (long) A[i][2], mStatistics);


//        for(int i = 0;i<mStatistics.size();i++)
//            System.out.println(mStatistics.get(i).toString());
        return mStatistics;
    }
}
