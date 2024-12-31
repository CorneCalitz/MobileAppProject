package com.example.eduvosproject.api;

import com.example.eduvosproject.LoginRequest;
import com.example.eduvosproject.LoginResponse;
import com.example.eduvosproject.course.CourseItems;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("login.php")  // Ensure the correct path for your PHP login script
    Call<LoginResponse> loginPost(@Body LoginRequest loginRequest);

    Call<ArrayList<CourseItems>> courseGet();
}
