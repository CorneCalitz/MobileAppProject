package com.example.eduvosproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.eduvosproject.course.CoursesFragment;
import com.example.eduvosproject.databinding.ActivityNavBinding;
import com.example.eduvosproject.home.HomeFragment;
import com.example.eduvosproject.datamodel.LoginResponse;
import com.example.eduvosproject.profile.ProfileFragment;
import com.example.eduvosproject.quiz.QuizFragment;
import com.google.gson.Gson;


public class NavActivity extends AppCompatActivity {

    ActivityNavBinding binding;
    LoginResponse loginResponse;
    String jsonString;

//    private Fragment home;
//    private Fragment course;
//    private Fragment profile;
//    private Fragment quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get and convert data sent with the intent.
        //TODO remove conversion if it is not necessary in this class.
        jsonString = getIntent().getStringExtra("jsonString");
        loginResponse = new Gson().fromJson(jsonString, LoginResponse.class);

        // Sets the first fragment that the user views.
        replaceFragment(new HomeFragment(),jsonString);

        // Switches fragment to one that the user clicks on.
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                replaceFragment(new HomeFragment(),jsonString);
            }
            else if (item.getItemId() == R.id.nav_courses) {
                replaceFragment(new CoursesFragment(),jsonString);
            }
            else if (item.getItemId() == R.id.nav_quiz) {
                replaceFragment(new QuizFragment(),jsonString);
            }
            else if (item.getItemId() == R.id.nav_profile) {
                replaceFragment(new ProfileFragment(), jsonString);
            }
            return true;
        });
    }

    // TODO: Something in this method is not allowing fragments to be created properly i.e. profileFragment is not running its onViewCreated class
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
}