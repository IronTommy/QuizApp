// ResultsActivity.java
package com.example.preparation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView resultsText = findViewById(R.id.results_text);
        TextView percentageText = findViewById(R.id.percentage_text);
        Button backButton = findViewById(R.id.back_button);

        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);
        int correctAnswers = getIntent().getIntExtra("correctAnswers", 0);
        double percentage = (double) correctAnswers / totalQuestions * 100;

        String resultsMessage = String.format("Правильных ответов: %d из %d", correctAnswers, totalQuestions);
        resultsText.setText(resultsMessage);

        String percentageMessage = String.format("Процент ответов: %.2f%%", percentage);
        percentageText.setText(percentageMessage);

        backButton.setOnClickListener(v -> {
            // Вернуться к MainActivity
            Intent intent = new Intent(ResultsActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}
