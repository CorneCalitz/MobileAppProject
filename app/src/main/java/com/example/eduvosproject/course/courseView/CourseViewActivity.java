package com.example.eduvosproject.course.courseView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eduvosproject.R;
import com.example.eduvosproject.course.CourseItems;
import com.google.gson.Gson;

public class CourseViewActivity extends AppCompatActivity {

    TextView tvContent;
    CourseItems courseItem;

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

        String contentString;
        contentString = getIntent().getStringExtra("jsonString");
        courseItem = new Gson().fromJson(contentString, CourseItems.class);
        tvContent.setText(courseItem.getContent());



    }
}