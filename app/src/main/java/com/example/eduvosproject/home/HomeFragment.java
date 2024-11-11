package com.example.eduvosproject.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eduvosproject.R;
import com.example.eduvosproject.datamodel.LoginResponse;
import com.google.gson.Gson;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "jsonString";

    TextView tvHomeMessage;
    LoginResponse loginResponse;
    private String jsonString;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvHomeMessage = view.findViewById(R.id.tvHomeMessage);

        if (getArguments() != null) {
            jsonString = getArguments().getString(ARG_PARAM1);
            loginResponse = new Gson().fromJson(jsonString, LoginResponse.class);
            tvHomeMessage.setText(String.format("Welcome %s", loginResponse.user.getName()));
        } else {
            tvHomeMessage.setText("Welcome user");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}