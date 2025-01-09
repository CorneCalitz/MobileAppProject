package com.example.eduvosproject.quiz.quiz_attempt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eduvosproject.NavActivity;
import com.example.eduvosproject.R;
import com.example.eduvosproject.api.ApiClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizAttemptActivity extends AppCompatActivity {


    TextView tvContext, tvQuestion, tvQuizPos;
    RadioGroup rgChoices;
    RadioButton rb1, rb2, rb3, rb4;
    Button btnNextQuestion;

    QuizData quizData;
    String quizDataString;

    Map<Integer, Integer> responseDict = new HashMap<Integer, Integer>();

    ArrayList<QuizQuestions> quizQuestions = new ArrayList<>();
    ArrayList<QuizChoices> quizChoices = new ArrayList<>();

    int quizPosition = 0;
    int quizSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_attempt);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    tvContext = findViewById(R.id.tvQuestionContext);
    tvQuestion = findViewById(R.id.tvQuestion);
    tvQuizPos = findViewById(R.id.tvQuizPos);

    rgChoices = findViewById(R.id.rgChoices);
    rb1 = findViewById(R.id.rb1);
    rb2 = findViewById(R.id.rb2);
    rb3 = findViewById(R.id.rb3);
    rb4 = findViewById(R.id.rb4);


    btnNextQuestion = findViewById(R.id.btnNextQuestion);

    quizDataString = getIntent().getStringExtra("quizData");
    quizData = new Gson().fromJson(quizDataString, QuizData.class);

    btnNextQuestion.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // Check which choice was checked and store its response as the choice id.
            if (rgChoices.getCheckedRadioButtonId() == -1) {
                Log.d("radiobutton", "No button checked");
                // Set as zero since user automatically fails a null check
                responseDict.put(quizQuestions.get(quizPosition).getId(), 0);

            } else {
                RadioButton selected = rgChoices.findViewById(rgChoices.getCheckedRadioButtonId());
                responseDict.put(quizQuestions.get(quizPosition).getId(),selected.getId());
                Log.d("radiobutton",Integer.toString(selected.getId()));
            }

            Log.d("responses", responseDict.toString());

            quizPosition = quizPosition + 1;
            //Move to next set of questions if position of quiz is not at last stage.
            if (quizPosition != quizSize) {
                rgChoices.clearCheck();
                quizAttempt();
            } else {
                //go to next activity.
                Log.d("next act", "Lets go to marioland");
                Intent intent = new Intent(QuizAttemptActivity.this, NavActivity.class);
                startActivity(intent);
                finish();
            }


        }
    });
    populateQuestionModel();

    }

    public void populateQuestionModel() {
        // Populates the question model
        Call<ArrayList<QuizQuestions>> questionDataCall = ApiClient.getUserService().questionsDataCall(quizData.quiz_data.getId());
        questionDataCall.enqueue(new Callback<ArrayList<QuizQuestions>>() {
            @Override
            public void onResponse(Call<ArrayList<QuizQuestions>> call, Response<ArrayList<QuizQuestions>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        quizQuestions.add(new QuizQuestions(response.body().get(i).id,
                                response.body().get(i).answer,
                                response.body().get(i).question,
                                response.body().get(i).context));
                    }
                    quizAttempt();
                }
                // If this fails boot the user to the home fragment.

            }

            @Override
            public void onFailure(Call<ArrayList<QuizQuestions>> call, Throwable t) {
                Log.e("Call failed", t.getMessage());
            }
        });

    }

    public void setQuizQuestions(int pos, int size) {
        tvQuestion.setText(quizQuestions.get(pos).getQuestion());
        tvContext.setText(quizQuestions.get(pos).getContext());
        tvQuizPos.setText(Integer.toString(pos + 1) + " / " + Integer.toString(size));

    }

    public void quizAttempt() {
        //Initiates the quiz loop.

        quizSize = quizQuestions.size();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setQuizQuestions(quizPosition, quizSize);
            }
        }, 500);
        //  setQuizQuestions(quizPosition, quizSize);
        populateChoiceModel(quizPosition);
    }

    public void populateChoiceModel(int pos) {
        //Fetch choices and populate corresponding model.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Call<ArrayList<QuizChoices>> choicesDataCall = ApiClient.getUserService().choicesDataCall(quizQuestions.get(pos).getId());
                choicesDataCall.enqueue(new Callback<ArrayList<QuizChoices>>() {
                @Override
                public void onResponse(Call<ArrayList<QuizChoices>> call, Response<ArrayList<QuizChoices>> response) {
                    quizChoices.clear();

                    if (response.isSuccessful() && response.body() != null) {
                        for (int i = 0; i < response.body().size(); i++) {
                            quizChoices.add(new QuizChoices(response.body().get(i).id, response.body().get(i).choice));

                        }
                        Log.d("quizChoices", "Data model" + new Gson().toJson(quizChoices));
                        setRadioGroup();
                    }
                    // If this fails boot the user to the home fragment.

                }

                @Override
                public void onFailure(Call<ArrayList<QuizChoices>> call, Throwable t) {
                    Log.e("Call failed", t.getMessage());
                }
            });
            }
        }, 500);
    }

    public void setRadioGroup() {
        //Case statement to populate radiobuttons.
        int numChoices = quizChoices.size();
        switch (numChoices) {
            case 2:
                //Set stuff
                rb1.setText(quizChoices.get(0).getChoice());
                rb2.setText(quizChoices.get(1).getChoice());

                rb1.setId(quizChoices.get(0).getId());
                rb2.setId(quizChoices.get(1).getId());

                rb3.setVisibility(View.INVISIBLE);
                rb4.setVisibility(View.INVISIBLE);
            break;
            case 3:
                //Set stuff
                rb1.setText(quizChoices.get(0).getChoice());
                rb2.setText(quizChoices.get(1).getChoice());
                rb3.setText(quizChoices.get(2).getChoice());

                rb1.setId(quizChoices.get(0).getId());
                rb2.setId(quizChoices.get(1).getId());
                rb3.setId(quizChoices.get(2).getId());

                rb4.setVisibility(View.INVISIBLE);
            break;
            default:
                //Set stuff
                rb1.setText(quizChoices.get(0).getChoice());
                rb2.setText(quizChoices.get(1).getChoice());
                rb3.setText(quizChoices.get(2).getChoice());
                rb4.setText(quizChoices.get(3).getChoice());

                rb1.setId(quizChoices.get(0).getId());
                rb2.setId(quizChoices.get(1).getId());
                rb3.setId(quizChoices.get(2).getId());
                rb3.setId(quizChoices.get(3).getId());
        }

    }

}