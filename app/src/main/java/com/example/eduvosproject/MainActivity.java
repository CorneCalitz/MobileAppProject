package com.example.eduvosproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eduvosproject.api.ApiClient;
import com.example.eduvosproject.NavActivity;

import com.google.gson.Gson;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtName, edtPassword;
    TextView tvLoginMessage;
    String username, password, loginDataString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Login button
        buttonLoginListener();

    }

    public void buttonLoginListener() {
        btnLogin = findViewById(R.id.btnLogin);
        edtName = findViewById(R.id.editTextText);
        edtPassword = findViewById(R.id.editTextNumberPassword);
        tvLoginMessage = findViewById(R.id.tvLoginMessage);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = edtName.getText().toString();
                password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    tvLoginMessage.setText("Name and Pin required.");
                } else {
                    //proceed to login
                    attemptLogin();
                }
            }
        });}



    public void attemptLogin() {
        //the LoginRequest object with name and password
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Log the LoginRequest object to verify its contents
        Log.d("LoginRequest", new Gson().toJson(loginRequest));

        // Call the loginPost method with the LoginRequest object
        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().loginPost(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    Log.d("LoginResponse", "Full Response: " + new Gson().toJson(loginResponse));

                    if ((loginResponse.result.getError().equals(false))) {
                        tvLoginMessage.setText("Login successful");

                        // Save login data in SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("loginData", new Gson().toJson(loginResponse));
                        editor.apply();

                        // Navigate to next activity
                        Intent intent = new Intent(MainActivity.this, NavActivity.class);
                        intent.putExtra("jsonString", new Gson().toJson(loginResponse)); // Passing login data
                        startActivity(intent);
                        finish();
                    } else {
                        tvLoginMessage.setText(loginResponse.result.getMessage());
                        Log.e("LoginError", "Error: " + (loginResponse != null ? loginResponse.message : "Unknown error"));
                    }
                } else {
                    Log.e("LoginError", "Response error: " + response.message());
                    Log.e("LoginError", "Raw response body: " + response.errorBody());
                    tvLoginMessage.setText("Something went wrong");
                }
            }



            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LoginError", "Failure: " + t.getMessage(), t);
                tvLoginMessage.setText("Something went wrong: " + t.getMessage());
            }
        });
    }
}

