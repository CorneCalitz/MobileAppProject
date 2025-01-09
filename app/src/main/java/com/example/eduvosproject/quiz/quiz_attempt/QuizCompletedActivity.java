package com.example.eduvosproject.quiz.quiz_attempt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eduvosproject.LoginResponse;
import com.example.eduvosproject.MainActivity;
import com.example.eduvosproject.NavActivity;
import com.example.eduvosproject.R;
import com.example.eduvosproject.api.ApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizCompletedActivity extends AppCompatActivity {

    TextView tvTitle, tvMsg;
    Button btnReturn;
    ImageView imgComplete;

    String loginResponseString, quizDataString, quizResponsesString, quizQuestionsString;
    LoginResponse loginResponse;
    QuizData quizData;
    ArrayList<QuizResponse> quizResponses;
    ArrayList<QuizQuestions> quizQuestions;

    Gson convert =  new Gson();

    Boolean passed;
    int numCorrect, numQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_completed);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvTitle = findViewById(R.id.tvCompletedTitle);
        tvMsg = findViewById(R.id.tvCompletedMsg);
        btnReturn = findViewById(R.id.btnReturnToHome);
        imgComplete = findViewById(R.id.imgCompleted);

        //Get extra strings from intent.
        loginResponseString = getIntent().getStringExtra("loginResponse");
        quizDataString = getIntent().getStringExtra("quizData");
        quizResponsesString = getIntent().getStringExtra("quizResponses");
        quizQuestionsString =  getIntent().getStringExtra("quizQuestions");

        //Populate models using string conversion
        loginResponse = convert.fromJson(loginResponseString, LoginResponse.class);
        quizData = convert.fromJson(quizDataString, QuizData.class);
        quizResponses = convert.fromJson(quizResponsesString, new TypeToken<ArrayList<QuizResponse>>(){}.getType());
        quizQuestions = convert.fromJson(quizQuestionsString, new TypeToken<ArrayList<QuizQuestions>>(){}.getType());

        Log.d("login", loginResponse.user.getName());
        Log.d("quiz", quizData.quiz_data.getName());
        Log.d("response", Integer.toString(quizResponses.get(0).getQuestionResponse()));
        Log.d("question",  quizQuestions.get(0).getQuestion());


        numCorrect = 0;
        Log.d("size of quizResponses", String.format("%s",quizResponses.size()));

        for (int i=0; i < quizResponses.size(); i++) {

            if (quizResponses.get(i).questionResponse == quizQuestions.get(i).getAnswer()) {
                Log.d(String.format("response:%s",i), String.format("%s",quizResponses.get(i).getQuestionResponse()));
                Log.d(String.format("answer:%s",i), String.format("%s",quizQuestions.get(i).getAnswer()));
                numCorrect = numCorrect + 1;
            } else {
                Log.d(String.format("response:%s",i), String.format("%s",quizResponses.get(i).getQuestionResponse()));
                Log.d(String.format("answer:%s",i), String.format("%s",quizQuestions.get(i).getAnswer()));
            }
        }

        //Checks that the user scores above or equal to 50%.
        double minPassMark = Math.ceil(quizData.score.getQuestionAmount()/ 2.0);
        if (numCorrect >= minPassMark) {
            passed = true;
        } else {
            passed = false;;
        }

        setGUI();
        updateQuizData();
        updateProfile();

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizCompletedActivity.this, NavActivity.class);
                intent.putExtra("jsonString", convert.toJson(loginResponse)); // Passing login data
                startActivity(intent);
                finish();
            }
        });
    }

    public void setGUI() {
        //Set gui according to quiz results.
        tvTitle.setText(String.format("%s completed.",quizData.quiz_data.getName()));

        if (passed) {
            //Set image to party poppers
            tvMsg.setText(String.format("Well done! You passed %s with a score of %s out of %s.", quizData.quiz_data.getName(), numCorrect, quizData.score.getQuestionAmount()));
            imgComplete.setImageResource(R.drawable.confetti);
        } else {
            //Set image to sad clown.
            tvMsg.setText(String.format("You failed %s with a score of %s out of %s. Better luck next time.", quizData.quiz_data.getName(), numCorrect, quizData.score.getQuestionAmount()));
            imgComplete.setImageResource(R.drawable.sad_clown);
        }

    }

    public void saveResponse(){
        //Save response of quiz in db.
        //Not relevant for showcase.
    }

    public void updateQuizData() {
        // Update data relating to the quiz and save in db.
        quizData.score.setScore(numCorrect);
        Call<QuizData> quizDataPost = ApiClient.getUserService().quizDataPost(quizData);
        quizDataPost.enqueue(new Callback<QuizData>() {
            @Override
            public void onResponse(Call<QuizData> call, Response<QuizData> response) {
                Log.d("QuizDataUpdate", response.message());
            }

            @Override
            public void onFailure(Call<QuizData> call, Throwable t) {
                Log.e("QuizDataUpdate", t.getMessage());
            }
        });

    }

    public void updateProfile(){
        // Update profile data and save in db.
        int takenCount = loginResponse.profile.getTests_taken();
        int passedCount = loginResponse.profile.getTests_passed();

        loginResponse.profile.setTests_taken(takenCount + 1);

        if (passed) {
            loginResponse.profile.setTests_passed(passedCount + 1);
        }

        Call<LoginResponse> profilePost = ApiClient.getUserService().profilePost(loginResponse);
        profilePost.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("profileUpdate", response.message());
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("profileUpdate", t.getMessage());
            }
        });

    }
}