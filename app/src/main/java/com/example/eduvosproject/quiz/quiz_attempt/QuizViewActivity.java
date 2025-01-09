package com.example.eduvosproject.quiz.quiz_attempt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eduvosproject.LoginResponse;
import com.example.eduvosproject.R;
import com.example.eduvosproject.api.ApiClient;
import com.example.eduvosproject.course.course_view.CourseViewActivity;
import com.example.eduvosproject.quiz.QuizItems;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizViewActivity extends AppCompatActivity {

    TextView tvQuizTitle, tvQuestionsAmount, tvScore, tvPercent, tvCourseName, tvPassedCheck;
    Button btnAttempt, btnCourseLink, btnQuizBack;
    QuizItems quizItem;
    LoginResponse loginResponse;
    String loginResponseString, quizItemString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvQuizTitle = findViewById(R.id.tvQuizTitle);
        tvQuestionsAmount = findViewById(R.id.tvQuestionsAmount);
        tvScore = findViewById(R.id.tvScore);
        tvCourseName = findViewById(R.id.tvCourseName);
        tvPassedCheck = findViewById(R.id.tvPassedCheck);


        btnAttempt = findViewById(R.id.btnAttempt);

        loginResponseString = getIntent().getStringExtra("loginResponse");
        loginResponse = new Gson().fromJson(loginResponseString, LoginResponse.class);

        quizItemString = getIntent().getStringExtra("quizItem");
        quizItem = new Gson().fromJson(quizItemString, QuizItems.class);

        populateQuizPage();


    }

    public void populateQuizPage() {

        //Retrieve data to be displayed on quizview page.
        Call<QuizData> quizDataCall = ApiClient.getUserService().quizDataCall(quizItem.getId(),loginResponse.profile.getProfile_id());

        quizDataCall.enqueue(new Callback<QuizData>() {
            @Override
            public void onResponse(Call<QuizData> call, Response<QuizData> response) {

                if (response.isSuccessful() && response.body() != null) {
                    QuizData quizData = response.body();
                    Log.d("QuizDataCall", "Full Response: " + new Gson().toJson(quizData));

                    if (quizData.result.getError().equals(false)) {
                        // Set textviews and buttons to match data.
                        int score = quizData.score.getScore();
                        int questionAmount = quizData.score.getQuestionAmount();

                        tvQuizTitle.setText(quizData.quiz_data.getName());
                        tvCourseName.setText(quizData.course_data.getName());
                        tvQuestionsAmount.setText(String.format("Questions: %s",questionAmount));
                        tvScore.setText(String.format("Score: %s / %s",score, questionAmount));
                        //TODO add a pass check
                        tvPassedCheck.setText("Failed");

                        btnAttempt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String quizDataString = new Gson().toJson(quizData);
                                Intent intent = new Intent(QuizViewActivity.this, QuizAttemptActivity.class);
                                intent.putExtra("quizData", quizDataString);
                                intent.putExtra("loginResponse", loginResponseString);
                                startActivity(intent);
                                finish();
                            }
                        });


                    } else {
                        // Set textview for error message and hide buttons.

                        Log.e("QuizDataCall", quizData.result.getMessage());

                    }
                } else {
                    // Set textview for error message and hide buttons.

                    Log.e("QuizDataCall", response.message());
                }
            }

            @Override
            public void onFailure(Call<QuizData> call, Throwable t) {
                // Set textview for error message and hide buttons.

                Log.e("QuizDataCall failed", t.getMessage());
            }
        });
    }

    public double calcPercentage(int score, int question) {
        return ((double) score / (double) question) * 100;
    }
}