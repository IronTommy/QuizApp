package com.example.preparation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.List;

public class QuestionActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ViewPager2 viewPager;
    private QuestionAdapter adapter;
    private QuestionPagerAdapter pagerAdapter;
    private List<Question> questionList;
    private int score;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        recyclerView = findViewById(R.id.recycler_view);
        viewPager = findViewById(R.id.view_pager);

        SharedPreferences prefs = getSharedPreferences(SettingsActivity.PREFS_NAME, MODE_PRIVATE);
        String cardViewMode = SettingsActivity.getCardViewMode(this); // Передаем this вместо prefs

        String category = getIntent().getStringExtra("category");

        if ("object_security".equals(category)) {
            questionList = ObjectSecurityQuestionData.getQuestions();
        } else {
            questionList = QuestionData.getQuestions();
        }

        if (questionList != null && !questionList.isEmpty()) {
            if (SettingsActivity.VIEW_MODE_SCROLL.equals(cardViewMode)) {
                viewPager.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new QuestionAdapter(questionList, this);
                recyclerView.setAdapter(adapter);
            } else {
                recyclerView.setVisibility(View.GONE);
                viewPager.setVisibility(View.VISIBLE);
                pagerAdapter = new QuestionPagerAdapter(questionList, this, viewPager);
                viewPager.setAdapter(pagerAdapter);
            }
        }

        findViewById(R.id.submit_button).setOnClickListener(v -> {
            Intent intent = new Intent(QuestionActivity.this, ResultsActivity.class);
            intent.putExtra("totalQuestions", questionList.size());
            intent.putExtra("correctAnswers", score);
            startActivity(intent);
        });
    }

    public void increaseScore() {
        score++;
    }

}
