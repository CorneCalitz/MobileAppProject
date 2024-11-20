package com.example.eduvosproject.profile;

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

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "jsonString";

    LoginResponse loginResponse;
    TextView tvUsername, tvTestsTaken, tvTestsPassed, tvAccountType;
    private String jsonString;
    Button btnLogout;

    public ProfileFragment() {
        // Required empty public constructor
    }


    private void buttonLogoutListener() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sharedPref",getContext().MODE_PRIVATE);
                Log.d("Test",sharedPreferences.getString("loginData",""));
                SharedPreferences.Editor edt = sharedPreferences.edit();
                edt.putString("loginData", "");
                edt.apply();
                Log.d("Test2",sharedPreferences.getString("loginData",""));
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
            loginResponse = new Gson().fromJson(jsonString, LoginResponse.class);
            tvUsername.setText(loginResponse.user.getName());


            tvTestsTaken.setText(String.format("Test(s) Taken: %s", loginResponse.profile.getTests_taken().toString()));
            tvTestsPassed.setText(String.format("Test(s) Passed: %s", loginResponse.profile.getTests_passed().toString()));


            tvAccountType.setText("Waiter");
        }

        buttonLogoutListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}