package com.example.preparation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionData {

    public static List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();

        questionList.add(new Question(
                "1. Вопрос:",
                Arrays.asList("ответ",
                        "ответ",
                        "ответ",
                        "ответ"),
                3,
                "пояснение."
        ));
        questionList.add(new Question(
                "2. Вопрос:",
                Arrays.asList("ответ",
                        "ответ",
                        "ответ",
                        "ответ"),
                0,
                "пояснение."
        ));

        return questionList;
    }
}
