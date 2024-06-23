package com.example.preparation;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionPagerAdapter extends RecyclerView.Adapter<QuestionPagerAdapter.QuestionViewHolder> {

    private List<Question> questions;
    private Context context;
    private LayoutInflater inflater;
    private ViewPager2 viewPager;

    public QuestionPagerAdapter(List<Question> questions, Context context, ViewPager2 viewPager) {
        this.questions = questions != null ? questions : new ArrayList<>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.viewPager = viewPager;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.questionText.setText(question.getText());

        List<String> shuffledOptions = new ArrayList<>(question.getOptions());
        Collections.shuffle(shuffledOptions);

        holder.option1.setText(shuffledOptions.get(0));
        holder.option2.setText(shuffledOptions.get(1));
        holder.option3.setText(shuffledOptions.get(2));
        holder.option4.setText(shuffledOptions.get(3));

        holder.option1.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        holder.option2.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        holder.option3.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));
        holder.option4.setBackgroundColor(context.getResources().getColor(android.R.color.darker_gray));

        holder.option1.setOnClickListener(v -> checkAnswer(holder, question, shuffledOptions.get(0), holder.option1));
        holder.option2.setOnClickListener(v -> checkAnswer(holder, question, shuffledOptions.get(1), holder.option2));
        holder.option3.setOnClickListener(v -> checkAnswer(holder, question, shuffledOptions.get(2), holder.option3));
        holder.option4.setOnClickListener(v -> checkAnswer(holder, question, shuffledOptions.get(3), holder.option4));
    }

    private void checkAnswer(QuestionViewHolder holder, Question question, String selectedAnswer, TextView selectedOption) {
        boolean isCorrect = question.getOptions().indexOf(selectedAnswer) == question.getCorrectAnswer();
        if (isCorrect) {
            selectedOption.setBackgroundColor(context.getResources().getColor(android.R.color.holo_green_light));
            ((QuestionActivity) context).increaseScore();
            holder.explanationText.setText("Правильно! " + question.getExplanation());
        } else {
            selectedOption.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
            holder.explanationText.setText("Неправильно. " + question.getExplanation());
        }
        holder.explanationText.setVisibility(View.VISIBLE);

        // Переключаем на следующий вопрос через 1 секунду
        new Handler().postDelayed(() -> {
            int nextItem = viewPager.getCurrentItem() + 1;
            if (nextItem < getItemCount()) {
                viewPager.setCurrentItem(nextItem);
            }
        }, 1000);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionText, option1, option2, option3, option4, explanationText;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_text);
            option1 = itemView.findViewById(R.id.option_1);
            option2 = itemView.findViewById(R.id.option_2);
            option3 = itemView.findViewById(R.id.option_3);
            option4 = itemView.findViewById(R.id.option_4);
            explanationText = itemView.findViewById(R.id.explanation_text);
        }
    }
}
