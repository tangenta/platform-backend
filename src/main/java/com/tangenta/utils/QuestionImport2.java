package com.tangenta.utils;

import com.tangenta.data.pojo.QuestionClassification;
import com.tangenta.data.pojo.QuestionType;
import com.tangenta.data.pojo.mybatis.MQuestion;
import com.tangenta.data.pojo.mybatis.QuestionSolution;

import java.io.IOException;
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
        final Long[] questionId = {220L};
        List<MQuestion> questions = new LinkedList<>();
        List<QuestionSolution> questionSolutions = new LinkedList<>();
        Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8).stream()
                .map(line -> line.replace(' ', ' ').split("\t"))
                .forEach(ln -> {
                    if (isChoice(ln)) {
                        String[] desOptions = splitDescription(descriptionField(ln));
                        String description = desOptions[0];
                        List<String> options = splitOptions(desOptions[1]);
                        String correctAnswer = mapAlphaToNumber(correctAnswerField(ln)).stream()
                                .map(options::get)
                                .sorted()
                                .collect(Collectors.joining(", "));

                        questions.add(new MQuestion(questionId[0], description, typeField(ln),
                                classField(ln), correctAnswer, answerDescriptionField(ln), false, 0L));
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
        return Arrays.stream(options.split("[  ([a-zA-Z].)]"))
                .filter(s -> !s.isEmpty())
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

    private static List<Integer> mapAlphaToNumber(String alpha) {
        return alpha.trim().chars().map(i -> i - 'A').boxed().collect(Collectors.toList());
    }

    public static void generateCode() throws IOException {
        QuestionImport2.readQuestions("D:\\questionData\\question2.txt",
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

    public static void main(String[] args) throws IOException {
        generateCode();

//         Test exception
//        QuestionImport2.readQuestions("D:\\\\questionData\\\\question2.txt",
//                q -> {}, qs -> qs.forEach(i -> System.out.println(i.getOption())));

//        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\\\questionData\\\\question2.txt"));
//        String line = bufferedReader.readLine();
//        System.out.println(line);
//        String option = splitDescription(line.split("\t")[3])[1];
//        System.out.println(Arrays.toString(option.split("[  ([a-zA-Z][.、])]")));
    }
}
