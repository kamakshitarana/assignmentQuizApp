package com.example.demoapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class QuizActivity extends AppCompatActivity implements OptionAdapter.OnOptionClickListener {
    private TextView questionTextView;
    private Button answer1Button;
    private Button answer2Button;
    private TextView scoreTextView;

    private ArrayList<Question> questions = new ArrayList<>();
    private int currentQuestionIndex;
    private int user1Score = 0;
    private int user2Score = 0;
    private boolean isTiebreaker;
    private boolean isUser1Selected = true;

    private Random random;
    RecyclerView recyclerView;
    Button button;
    ImageView iv_user1, iv_user2;
    OptionAdapter optionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        recyclerView = findViewById(R.id.rvRecyclerView);
        iv_user1 = findViewById(R.id.imageView);
        iv_user2 = findViewById(R.id.imageView2);
        button = findViewById(R.id.button);
        optionAdapter = new OptionAdapter(questions, this);
        recyclerView.setAdapter(optionAdapter);

        Glide.with(this).load(R.drawable.user1).circleCrop().into(iv_user1);
        Glide.with(this).load(R.drawable.user2).circleCrop().into(iv_user2);
        iv_user1.setForeground(getResources().getDrawable(R.drawable.circle));

        iv_user1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionAdapter.setUser(true);
                iv_user1.setForeground(getResources().getDrawable(R.drawable.circle));
                iv_user2.setForeground(null);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user1Score != user2Score) {
                    String msg = "User 1 score :" + user1Score + " and user 2 score :" + user2Score + "\n";
                    if (user1Score > user2Score) {
                        msg = msg + "User 1 win";
                    } else {
                        msg = msg + "User 2 win";
                    }
                    Toast.makeText(QuizActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizActivity.this, "Tei up, please try again", Toast.LENGTH_SHORT).show();
                    optionAdapter.clear();
                    fetchQuestions();
                }
            }
        });

        iv_user2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionAdapter.setUser(false);
                iv_user2.setForeground(getResources().getDrawable(R.drawable.circle));
                iv_user1.setForeground(null);
            }
        });

        fetchQuestions();
    }

    private void fetchQuestions() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url("https://opentdb.com/api.php?amount=5").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    Type listType = new TypeToken<MainQuestion>() {
                    }.getType();
                    MainQuestion mainQuestion = new Gson().fromJson(json, listType);
                    questions = mainQuestion.getResults();
                    Log.e("eeee11", String.valueOf(questions.size()));
                    runOnUiThread(() -> startQuiz());
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("eeee22", String.valueOf(e.getMessage()));
                e.printStackTrace();
            }
        });
    }

    private void startQuiz() {
        optionAdapter.add(questions);
    }


    @Override
    public void onOptionClick(Question question, boolean isUser1Selected) {
        if (isUser1Selected) {
            if (question.getCorrectAnswer().equals(question.getUser1SelectedPos())) {
                user1Score = user1Score + 5;
            } else {
                user1Score = user1Score - 2;
            }
        } else {
            if (question.getCorrectAnswer().equals(question.getUser2SelectedPos())) {
                user2Score = user2Score + 5;
            } else {
                user2Score = user2Score - 2;
            }
        }
        Log.e("score", "user1::" + user1Score + "::::user2 :::" + user2Score);
    }
}
