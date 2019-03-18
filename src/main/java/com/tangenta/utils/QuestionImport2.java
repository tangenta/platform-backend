package com.tangenta.utils;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.data.pojo.mybatis.QuestionSolution;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class QuestionImport2 {

    public static void readQuestions(String path, Consumer<List<MQuestion>> questionConsumer,
                            Consumer<List<QuestionSolution>> questionSolutionConsumer) throws IOException {
        final Long[] questionId = {1L};
        List<MQuestion> questions = new LinkedList<>();
        List<QuestionSolution> questionSolutions = new LinkedList<>();
        Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8).stream()
                .map(line -> line.split("\t"))
                .forEach(ln -> {
                    if (isChoice(ln)) {
                        String[] desOptions = splitDescription(descriptionField(ln));
                        String description = desOptions[0];
                        List<String> options = splitOptions(desOptions[1]);

                        questions.add(new MQuestion(questionId[0], description, typeField(ln),
                                classField(ln), correctAnswerField(ln), answerDescriptionField(ln), false, 0L));
                        questionSolutions.addAll(options.stream()
                                .map(o -> new QuestionSolution(questionId[0], o))
                                .collect(Collectors.toList())
                        );
                    } else {
                        questions.add(new MQuestion(questionId[0], descriptionField(ln), typeField(ln),
                                classField(ln), correctAnswerField(ln), answerDescriptionField(ln), false, 0L));
                    }

                    questionId[0]++;
                });

        questionConsumer.accept(questions);
        questionSolutionConsumer.accept(questionSolutions);
    }

    private static boolean isChoice(String[] line) {
        return line[2].equals("0") || line[2].equals("1");
    }

    private static String descriptionField(String[] line) {
        return line[3];
    }

    private static String[] splitDescription(String description) {
        int splitIndex = description.indexOf('A');
        String[] result = new String[2];
        result[0] = description.substring(0, splitIndex);
        result[1] = description.substring(splitIndex);
        return result;
    }

    private static List<String> splitOptions(String options) {
        return Arrays.stream(options.split("[  ]"))
                .filter(s -> !s.isEmpty())
                .map(s -> s.substring(2))
                .collect(Collectors.toList());
    }

    private static QuestionClassification classField(String[] line) {
        return mapClass(line[1]);
    }

    private static QuestionClassification mapClass(String numStr) {
        switch (numStr) {
            case "0": return QuestionClassification.Lilunjichu;
            case "1": return QuestionClassification.Jilvxing;
            case "2": return QuestionClassification.Jiazhiguan;
            case "3": return QuestionClassification.Daodepingjia;
            case "4": return QuestionClassification.Sixiangxianjinxing;
            default: throw new RuntimeException("class format exception");
        }
    }

    private static QuestionType typeField(String[] line) {
        return mapType(line[2]);
    }

    private static QuestionType mapType(String numStr) {
        switch (numStr) {
            case "0": return QuestionType.SingleChoice;
            case "1": return QuestionType.MultipleChoice;
            case "2": return QuestionType.TrueOrFalse;
            case "3": return QuestionType.BlanksFilling;
            default: throw new RuntimeException("type format exception");
        }
    }

    private static String correctAnswerField(String[] line) {
        return line[4];
    }

    private static String answerDescriptionField(String[] line) {
        return line[5];
    }

    public static void main(String[] args) throws IOException {
        QuestionImport2.readQuestions("D:\\questionData\\question.txt",
                q -> q.forEach(question -> {
                    System.out.println(
                            "add(new MQuestion(" +
                                    question.getQuestionId() +
                                    "L, \""+
                                    question.getDescription() +
                                    "\", QuestionType." +
                                    question.getType() +
                                    ", QuestionClassification." +
                                    question.getClassification() +
                                    ", \"" +
                                    question.getCorrectAnswer() +
                                    "\", \"" +
                                    question.getAnswerDescription() +
                                    "\", true, 0L));");
                }),
                qs -> qs.forEach(questionSolution -> {
                    System.out.println(
                        "add(new QuestionSolution(" +
                                questionSolution.getQuestionId() +
                                "L, \"" +
                                questionSolution.getOption() +
                                "\"));"
                    );
                }));
    }
}
