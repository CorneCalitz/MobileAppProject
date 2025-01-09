package com.example.eduvosproject.quiz.quiz_attempt;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eduvosproject.LoginResponse;
import com.example.eduvosproject.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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


        numCorrect = 1;

        //Consider putting this in a separate function.
        //TODO need to sort hashmap and arraylist for good measure before comparing answer values
        //for (int i=0; i <= quizResponses.size(); i++) {
         //   if (quizResponses.get(i).questionResponse == quizQuestions.get(i).getAnswer()) {
         //       Log.d("testCorrect", Integer.toString(quizResponses.get(i).getQuestionResponse()));
         //       numCorrect = numCorrect + 1;
         //   } else {
          //      Log.d("testWrong", Integer.toString(quizResponses.get(i).getQuestionResponse()));
//        }

        //As longs as the number of correct is larger than half of the questions.
        if (numCorrect > 0) {
            passed = true;
        } else {
            passed = false;
        }

        setGUI();

        saveResponse();

        updateQuizData();

        updateProfile();

        //Button on click
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
        //Save response of quiz in db
    }

    public void updateQuizData() {
        // Update data relating to the quiz and save in db.
    }

    public void updateProfile(){
        // Update profile data and save in db.
    }
}