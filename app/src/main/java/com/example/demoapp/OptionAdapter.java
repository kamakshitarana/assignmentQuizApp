package com.example.demoapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder> {

    private ArrayList<Question> options = new ArrayList<>();
    private OnOptionClickListener listener;

    private boolean isUser1 = true;

    public OptionAdapter(ArrayList<Question> options, OnOptionClickListener listener) {
        this.options = options;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_option, parent, false);
        return new OptionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
        Question option = options.get(position);
        // Code to bind the data from the 'option' object to the views in the ViewHolder.

        holder.tvQuestion.setText(option.getQuestion());
        Log.e("user", String.valueOf(isUser1));

        RadioGroup radioGroup = new RadioGroup(holder.llMain.getContext());
        holder.llMain.removeAllViews();
        radioGroup.removeAllViews();
        for (int i = 0; i < option.getAllQuestions().size(); i++) {
            RadioButton checkBox = new RadioButton(radioGroup.getContext());
            checkBox.setText(option.getAllQuestions().get(i));
            checkBox.setChecked(false);
            Log.e("user11", String.valueOf(isUser1));

            if (isUser1) {
                if (option.getUser1SelectedPos().equals(checkBox.getText().toString())) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
            } else {
                if (option.getUser2SelectedPos().equals(checkBox.getText().toString())) {
                    checkBox.setChecked(true);
                } else {
                    checkBox.setChecked(false);
                }
            }
            radioGroup.addView(checkBox);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("isUser1e", String.valueOf(isUser1));
                    if (isUser1) {
                        option.setUser1SelectedPos(checkBox.getText().toString());
                    } else {
                        option.setUser2SelectedPos(checkBox.getText().toString());
                    }
                    if (listener != null) {
                        listener.onOptionClick(option, isUser1);
                    }
                    notifyDataSetChanged();
                }
            });
        }
        holder.llMain.addView(radioGroup);
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class OptionViewHolder extends RecyclerView.ViewHolder {

        // ViewHolder class definition
        private TextView tvQuestion;
        private LinearLayout llMain;

        public OptionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            llMain = itemView.findViewById(R.id.llMain);
        }

    }

    public interface OnOptionClickListener {
        //This is an interface that defines the onOptionClick method.
        void onOptionClick(Question question, boolean isChecked);
    }

    public void add(ArrayList<Question> question) {
        for (Question question1 : question) {
            ArrayList<String> questions = new ArrayList<>();
            questions.add(question1.getCorrectAnswer());
            for (int j = 0; j < question1.getIncorrectAnswers().size(); j++) {
                questions.add(question1.getIncorrectAnswers().get(j));
            }
            Collections.shuffle(questions);
            question1.setAllQuestions(questions);
            options.add(question1);
        }
        notifyDataSetChanged();

    }

    // Code to clear the 'options' list.
    public void clear() {
        options.clear();
        notifyDataSetChanged();
    }

    // Code to set the user (User 1 or User 2).
    public void setUser(boolean isUser1) {
        this.isUser1 = isUser1;
        Log.e("user00", String.valueOf(isUser1));

        notifyDataSetChanged();
    }
}

