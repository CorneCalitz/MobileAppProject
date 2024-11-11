package com.example.eduvosproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
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
import com.example.eduvosproject.datamodel.LoginResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtName, edtPassword;
    TextView tvLoginMessage;
    String name, password, loginDataString;


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
        buttonLoginListener();

    }

    public void buttonLoginListener() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtName = findViewById(R.id.editTextText);
        edtPassword = findViewById(R.id.editTextNumberPassword);
        tvLoginMessage = findViewById(R.id.tvLoginMessage);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = edtName.getText().toString();
                password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(password)) {
                    tvLoginMessage.setText("Name and password required.");
                } else {
                    //proceed to login
                    attemptLogin();
                }
            }
        });
    }

    public void attemptLogin() {
        // Sends HTTP request and based on response handles the login attempt.

        // Creates a retrofit style call to the server
        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().loginPost(name, password);


        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {
                    //Populate login response model with incoming data.
                    LoginResponse loginResponse = response.body();

                    assert loginResponse != null;
                    if (!loginResponse.result.getError()) {
                        tvLoginMessage.setText("Login successful");

                        //Convert loginResponse object into a json string, this will be passed with our intent
                        loginDataString = new Gson().toJson(loginResponse);

                        //Gives a slight delay to login.
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(MainActivity.this, NavActivity.class).putExtra("jsonString", loginDataString));
                            }
                        }, 700);
                    } else {
                        tvLoginMessage.setText(loginResponse.result.getMessage());
                    }
                } else {
                    tvLoginMessage.setText("Response timed out");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                tvLoginMessage.setText("Something went wrong");
            }
        });

    }
}