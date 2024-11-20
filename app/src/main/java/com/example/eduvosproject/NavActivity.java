package com.example.eduvosproject;

import android.content.SharedPreferences;
import android.os.Bundle;

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

        // Get and convert data sent with the intent.
        loginJson = getIntent().getStringExtra("jsonString");
        loginResponse = new Gson().fromJson(loginJson, LoginResponse.class);

        // Sets the first fragment that the user views.
        replaceFragment(new HomeFragment(),loginJson);

        // Switches fragment to one that the user clicks on.
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                replaceFragment(new HomeFragment(),loginJson);
            }
            else if (item.getItemId() == R.id.nav_courses) {
                replaceFragment(new CoursesFragment(),loginJson);
            }
            else if (item.getItemId() == R.id.nav_quiz) {
                replaceFragment(new QuizFragment(),loginJson);
            }
            else if (item.getItemId() == R.id.nav_profile) {
                replaceFragment(new ProfileFragment(), loginJson);
            }
            return true;
        });
    }


    // Method used to replace the current on-screen fragment to a new fragment and pass data into it.
    public void replaceFragment(Fragment fragment, String string){
        Bundle args = new Bundle();
        args.putString("jsonString", string);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment);
        fragmentTransaction.commit();
    }

    //Store data if the user closes the app.
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPref", 0);
        SharedPreferences.Editor edt = sharedPreferences.edit();

        // write all the login data into sharedpref edit.
        edt.putString("loginData", loginJson);
        edt.apply();
    }



}