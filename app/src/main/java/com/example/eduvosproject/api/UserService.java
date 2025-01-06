package com.example.eduvosproject.api;

import com.example.eduvosproject.course.CourseItems;
import com.example.eduvosproject.LoginResponse;
import com.example.eduvosproject.LoginRequest;
import com.example.eduvosproject.course.courseView.CourseData;
import com.example.eduvosproject.quiz.QuizItems;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface UserService {
    @POST("app_php/login.php/")  // Ensure the correct path for your PHP login script
    Call<LoginResponse> loginPost(@Body LoginRequest loginRequest);

    // Login request method
    // trailing "/" is required
//    @FormUrlEncoded
//    @POST("/app_php/login.php/")
//    Call<LoginResponse> loginPost(@Field("name") String name, @Field("password") String password);

    // Course items fetch method
    @GET("/app_php/fetch_course_item.php/")
    Call<ArrayList<CourseItems>> courseItemsGet();

    // Quiz items fetch method
    @GET("/app_php/fetch_quiz_item.php/")
    Call<ArrayList<QuizItems>> quizItemsGet();

    // Fetch course contents and quiz id method
    @POST ("/app_php/fetch_course_data.php/")
    Call<CourseData> coursePost(@Body CourseItems courseItems);
}
