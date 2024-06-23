package com.example.preparation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectSecurityQuestionData {

    public static List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();

        questionList.add(new Question(
                "1. ВОПРОС:",
                Arrays.asList(
                        "a) ответ",
                        "b) ",
                        "c) ",
                        "d) "
                ),
                0, // правильный ответ: a
                "пояснение."
        ));

        questionList.add(new Question(
                "2. ВОПРОС:",
                Arrays.asList(
                        "a) ответ",
                        "b) ",
                        "c) ",
                        "d) "
                ),
                0, // правильный ответ: a
                "пояснение."
        ));

        questionList.add(new Question(
                "3. ВОПРОС:",
                Arrays.asList(
                        "a) ответ",
                        "b) ",
                        "c) ",
                        "d) "
                ),
                0, // правильный ответ: a
                "пояснение."
        ));


        return questionList;
    }
}
