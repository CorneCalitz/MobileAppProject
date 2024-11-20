package com.example.eduvosproject.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eduvosproject.R;
import com.example.eduvosproject.LoginResponse;
import com.google.gson.Gson;

public class ProfileFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "jsonString";

    LoginResponse loginResponse;
    TextView tvUsername, tvTestsTaken, tvTestsPassed, tvAccountType;
    private String jsonString;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvUsername = view.findViewById(R.id.tvUserName);
        tvTestsTaken = view.findViewById(R.id.tvTestsTaken);
        tvTestsPassed = view.findViewById(R.id.tvTestsPassed);
        tvAccountType = view.findViewById(R.id.tvAccountType);

        if (getArguments() != null) {
            jsonString = getArguments().getString(ARG_PARAM1);
            loginResponse = new Gson().fromJson(jsonString, LoginResponse.class);
            tvUsername.setText(loginResponse.user.getName());


            tvTestsTaken.setText(String.format("Test(s) Taken: %s", loginResponse.profile.getTests_taken().toString()));
            tvTestsPassed.setText(String.format("Test(s) Passed: %s", loginResponse.profile.getTests_passed().toString()));


            tvAccountType.setText("Waiter");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}