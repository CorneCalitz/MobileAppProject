package com.example.eduvosproject.api;

import com.example.eduvosproject.datamodel.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserService {
    //Creates retrofit style HTTP requests.

    // Login request method
    // trailing "/" is required
    @FormUrlEncoded
    @POST("/app_php/login.php/")
    Call<LoginResponse> loginPost(@Field("name") String name, @Field("password") String password);


}