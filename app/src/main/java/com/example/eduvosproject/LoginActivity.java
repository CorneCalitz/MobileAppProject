package com.example.eduvosproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eduvosproject.api.ApiClient;
import com.example.eduvosproject.api.UserService;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // the LoginRequest object with name and password
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Convert LoginRequest object to JSON
        String jsonRequest = new Gson().toJson(loginRequest);
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                jsonRequest
        );


        // Set up the request
        Request request = new Request.Builder()
                .url("http://192.168.125.94/") // Ensure this matches your server setup
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build();

        progressBar.setVisibility(View.VISIBLE);

        // API call using UserService
        UserService userService = ApiClient.getUserService();
        userService.loginPost(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBar.setVisibility(View.GONE);

                // Check if response is successful and body is not null
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    Log.d("LoginResponse", "Response body: " + loginResponse);

                    // Check if result is null to avoid NullPointerException
                    if (loginResponse.result != null) {
                        if (!loginResponse.result.getError()) {
                            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            // Navigate to the next screen
                            Intent intent = new Intent(LoginActivity.this, NavActivity.class);
                            intent.putExtra("username", loginResponse.user.getName());
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, loginResponse.result.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Log and show error if result is null
                        Log.e("LoginResponse", "Result is null.");
                        Toast.makeText(LoginActivity.this, "Unexpected response format.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("LoginResponse", "Response was not successful or body is null.");
                    Toast.makeText(LoginActivity.this, "Failed to login. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.e("LoginActivity", "Login error", t);
                Toast.makeText(LoginActivity.this, "Something went wrong. Check your connection.", Toast.LENGTH_SHORT).show();
            }
        });
    }}

