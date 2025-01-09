package com.example.eduvosproject.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eduvosproject.MainActivity;
import com.example.eduvosproject.NavActivity;
import com.example.eduvosproject.R;
import com.example.eduvosproject.LoginResponse;
import com.google.gson.Gson;

public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM1 = "jsonString";
    LoginResponse loginResponse;
    TextView tvUsername, tvTestsTaken, tvTestsPassed, tvAccountType;
    private String jsonString;
    Button btnLogout;
    Bundle bundle = new Bundle();


    private void buttonLogoutListener() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPref", getContext().MODE_PRIVATE);
                Log.d("Test", sharedPreferences.getString("loginData", ""));
                SharedPreferences.Editor edt = sharedPreferences.edit();
                edt.putString("loginData", "");
                edt.apply();
                Log.d("Test2", sharedPreferences.getString("loginData", ""));
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvUsername = view.findViewById(R.id.tvUserName);
        tvTestsTaken = view.findViewById(R.id.tvTestsTaken);
        tvTestsPassed = view.findViewById(R.id.tvTestsPassed);
        tvAccountType = view.findViewById(R.id.tvAccountType);
        btnLogout = view.findViewById(R.id.btnLogout);

        if (getArguments() != null) {
            jsonString = getArguments().getString(ARG_PARAM1);
            if (jsonString != null) {
                loginResponse = new Gson().fromJson(jsonString, LoginResponse.class);
                if (loginResponse != null && loginResponse.user != null && loginResponse.profile != null) {
                    tvUsername.setText(loginResponse.user.getName());
                    tvTestsTaken.setText(String.format("Test(s) Taken: %s", loginResponse.profile.getTests_taken()));
                    tvTestsPassed.setText(String.format("Test(s) Passed: %s", loginResponse.profile.getTests_passed()));
                    tvAccountType.setText("Waiter");
                } else {
                    Log.e("ProfileFragment", "Invalid loginResponse or profile data");
                    Toast.makeText(getContext(), "Error loading profile", Toast.LENGTH_SHORT).show();
                }


            } else {
                Log.e("ProfileFragment", "jsonString is null");
            }
        }
        Log.d("ProfileFragment", "onViewCreated called");
        Log.d("ProfileFragment", "jsonString: " + jsonString);
        Log.d("ProfileFragment", "LoginResponse: " + new Gson().toJson(loginResponse));

        setupLogoutButtonListener();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    // Logout button listener
    private void setupLogoutButtonListener() {
        btnLogout.setOnClickListener(view -> {
            // Clear shared preferences
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();

            Log.d("ProfileFragment", "User logged out");

            // Redirect to login activity
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            // Finish the current activity
            requireActivity().finish();
        });
    }

    // Show error toast
    private void showErrorToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}

