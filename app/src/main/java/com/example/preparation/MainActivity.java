package com.example.preparation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.general_category_button).setOnClickListener(v -> startQuestionActivity("general"));
        findViewById(R.id.object_security_category_button).setOnClickListener(v -> startQuestionActivity("object_security"));
        findViewById(R.id.settings_button).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    private void startQuestionActivity(String category) {
        Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}
