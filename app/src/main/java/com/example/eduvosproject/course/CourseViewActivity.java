package com.example.eduvosproject.course;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eduvosproject.R;
import com.example.eduvosproject.NavActivity;
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

        // Setup Toolbar as ActionBar with Back Button and "Back" title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable back button
            getSupportActionBar().setTitle("Back"); // Set "Back" as the toolbar title
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvContent = findViewById(R.id.tvContent);

        // Load course data from intent
        String contentString = getIntent().getStringExtra("jsonString");
        courseItem = new Gson().fromJson(contentString, CourseItems.class);
        tvContent.setText(courseItem.getContent());

        // Setup the Start Quiz button
        Button btnStartQuiz = findViewById(R.id.btnStartQuiz);
        btnStartQuiz.setOnClickListener(v -> {
            // Navigate to the quiz screen
            Intent intent = new Intent(CourseViewActivity.this, NavActivity.class);
            intent.putExtra("navigateTo", "quiz"); // Pass a flag to indicate navigation to QuizFragment
            startActivity(intent);
        });
    }

    // Handle back button press
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
