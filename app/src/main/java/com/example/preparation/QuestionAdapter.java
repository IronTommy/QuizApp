package com.example.preparation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private List<Question> questions;
    private QuestionActivity activity;
    private Map<Integer, Integer> selectedAnswers = new HashMap<>();

    public static class QuestionViewHolder extends RecyclerView.ViewHolder {
        public TextView questionText;
        public Button option1;
        public Button option2;
        public Button option3;
        public Button option4;
        public TextView explanationText;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_text);
            option1 = itemView.findViewById(R.id.option_1);
            option2 = itemView.findViewById(R.id.option_2);
            option3 = itemView.findViewById(R.id.option_3);
            option4 = itemView.findViewById(R.id.option_4);
            explanationText = itemView.findViewById(R.id.explanation_text);
        }
    }

    public QuestionAdapter(List<Question> questions, QuestionActivity activity) {
        this.questions = questions;
        this.activity = activity;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.questionText.setText(question.getText());

        List<String> shuffledOptions = new ArrayList<>(question.getOptions());
        int correctAnswerIndex = question.getCorrectAnswer();
        Collections.shuffle(shuffledOptions);

        int newCorrectAnswerIndex = shuffledOptions.indexOf(question.getOptions().get(correctAnswerIndex));
        question.setCorrectAnswer(newCorrectAnswerIndex);

        holder.option1.setText(shuffledOptions.get(0));
        holder.option2.setText(shuffledOptions.get(1));
        holder.option3.setText(shuffledOptions.get(2));
        holder.option4.setText(shuffledOptions.get(3));

        // Сброс состояния кнопок
        holder.option1.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.darker_gray));
        holder.option2.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.darker_gray));
        holder.option3.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.darker_gray));
        holder.option4.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.darker_gray));

        // Проверка выбранного ответа
        if (selectedAnswers.containsKey(position)) {
            int selectedOption = selectedAnswers.get(position);
            switch (selectedOption) {
                case 0:
                    holder.option1.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_light));
                    break;
                case 1:
                    holder.option2.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_light));
                    break;
                case 2:
                    holder.option3.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_light));
                    break;
                case 3:
                    holder.option4.setBackgroundColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_light));
                    break;
            }
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition == RecyclerView.NO_POSITION) return;

                int selectedOption = -1;
                if (v.getId() == holder.option1.getId()) {
                    selectedOption = 0;
                } else if (v.getId() == holder.option2.getId()) {
                    selectedOption = 1;
                } else if (v.getId() == holder.option3.getId()) {
                    selectedOption = 2;
                } else if (v.getId() == holder.option4.getId()) {
                    selectedOption = 3;
                }

                selectedAnswers.put(currentPosition, selectedOption);

                if (question.getCorrectAnswer() == selectedOption) {
                    v.setBackgroundColor(v.getContext().getResources().getColor(android.R.color.holo_green_light));
                    activity.increaseScore();
                } else {
                    v.setBackgroundColor(v.getContext().getResources().getColor(android.R.color.holo_red_light));
                }

                holder.explanationText.setVisibility(View.VISIBLE);
                holder.explanationText.setText(question.getExplanation());

                Animation slideOut = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_out_left);
                Animation slideIn = AnimationUtils.loadAnimation(v.getContext(), R.anim.slide_in_right);
                holder.itemView.startAnimation(slideOut);
                slideOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) { }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int nextPosition = holder.getAdapterPosition() + 1;
                        if (nextPosition < getItemCount()) {
                            notifyItemChanged(nextPosition);
                            holder.itemView.startAnimation(slideIn);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) { }
                });
            }
        };

        holder.option1.setOnClickListener(listener);
        holder.option2.setOnClickListener(listener);
        holder.option3.setOnClickListener(listener);
        holder.option4.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }
}
