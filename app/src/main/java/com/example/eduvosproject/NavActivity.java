package com.example.eduvosproject;

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

public class NavActivity extends AppCompatActivity {

    ActivityNavBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        // Switches fragment to one that the user clicks on.
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                replaceFragment(new HomeFragment());
            }
            else if (item.getItemId() == R.id.nav_courses) {
                replaceFragment(new CoursesFragment());
            }
            else if (item.getItemId() == R.id.nav_quiz) {
                replaceFragment(new QuizFragment());
            }
            else if (item.getItemId() == R.id.nav_profile) {
                replaceFragment(new ProfileFragment());
            }
            return true;
        });
    }

    // Method used to replace the current on-screen fragment to a new fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment);
        fragmentTransaction.commit();
    }
}