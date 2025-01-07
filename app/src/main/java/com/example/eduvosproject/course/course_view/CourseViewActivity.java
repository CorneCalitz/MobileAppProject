package com.example.eduvosproject.course.course_view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eduvosproject.R;
import com.example.eduvosproject.api.ApiClient;
import com.example.eduvosproject.course.CourseItems;
import com.google.gson.Gson;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseViewActivity extends AppCompatActivity {

    TextView tvContent, tvTitle;
    CourseItems courseItem;
    String courseDataString;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvContent = (TextView) findViewById(R.id.tvContent);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        courseDataString = getIntent().getStringExtra("jsonString");
        courseItem = new Gson().fromJson(courseDataString, CourseItems.class);

        populatePage();

        //TODO Create onclick for button that navigates to quiz.


    }

    public void populatePage() {
        //Retrieve data to be displayed on page.
        Call<CourseData> courseDataCall = ApiClient.getUserService().courseDataCall(courseItem);

        courseDataCall.enqueue(new Callback<CourseData>() {
            @Override
            public void onResponse(Call<CourseData> call, Response<CourseData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CourseData courseData = response.body();
                    Log.d("CourseDataCall", "Full Response: " + new Gson().toJson(courseData));

                    if ((courseData != null) && (courseData.result.getError().equals(false))) {
                        tvContent.setText(courseData.course_data.getContent());
                        tvTitle.setText(courseData.course_data.getName());

                        //Checks if the course has a corresponding quiz.
                        if (Objects.equals(courseData.result.getMessage(), "No quiz.")) {
                            // TODO: Set the button to hidden or alternative.


                            Log.d("CourseDataCall",courseData.result.getMessage());
                        } else {
                            //TODO: Set the text for the button that navigates to quiz here.


                            Log.d("CourseDataCall",courseData.result.getMessage());
                        }

                    } else {
                        tvContent.setText("Unable to load course data. Please try again later.");
                        tvTitle.setText("");
                        Log.e("CourseDataCall failed",courseData.result.getMessage());
                    }

                } else {
                    tvContent.setText("Unable to load course data. Please try again later.");
                    tvTitle.setText("");
                    Log.e("CourseDataCall failed", response.message());
                }
            }

            @Override
            public void onFailure(Call<CourseData> call, Throwable t) {
                tvContent.setText("Unable to load course data. Please try again later.");
                tvTitle.setText("");
                Log.e("CourseDataCall failed", t.getMessage());


            }
        });
    }
}