package com.example.eduvosproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.eduvosproject.course.CoursesFragment;
import com.example.eduvosproject.databinding.ActivityNavBinding;
import com.example.eduvosproject.home.HomeFragment;
import com.example.eduvosproject.profile.ProfileFragment;
import com.example.eduvosproject.quiz.QuizFragment;
import com.google.gson.Gson;

public class NavActivity extends AppCompatActivity {

    ActivityNavBinding binding;
    LoginResponse loginResponse;
    String loginJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(view -> logout());

        loginJson = getIntent().getStringExtra("jsonString");
        Log.d("NavActivity", "Received loginJson: " + loginJson);
        loginResponse = new Gson().fromJson(loginJson, LoginResponse.class);

        replaceFragment(new HomeFragment(), loginJson);

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                replaceFragment(new HomeFragment(), loginJson);
            } else if (item.getItemId() == R.id.nav_courses) {
                replaceFragment(new CoursesFragment(), loginJson);
            } else if (item.getItemId() == R.id.nav_quiz) {
                replaceFragment(new QuizFragment(), loginJson);
            } else if (item.getItemId() == R.id.nav_profile) {
                replaceFragment(new ProfileFragment(), loginJson);
            }
            return true;
        });
    }

    public void replaceFragment(Fragment fragment, String jsonString) {
        Bundle args = new Bundle();
        args.putString("jsonString", jsonString);
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.addToBackStack(null);  // Optional: if you want back navigation
        transaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        Log.d("SharedPreferences", "Retrieved login data: " + sharedPreferences.getString("loginData", "No data found"));

        SharedPreferences.Editor edt = sharedPreferences.edit();
        edt.putString("loginData", loginJson);
        edt.apply();
    }

    public void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        Log.d("NavActivity", "User data cleared, redirecting to login");

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
