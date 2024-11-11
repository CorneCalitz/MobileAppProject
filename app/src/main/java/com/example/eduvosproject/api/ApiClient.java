package com.example.eduvosproject.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
//Creates instances of the retrofit client and interface used send requests.

    private static Retrofit getRetrofit(){
        // Creates and returns an HTTP client. {Retrofit}

        // OkHttp is used to log requests and response data from the server.
        // TODO: Either redact sensitive info or outright remove OkHttp before final release
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.0.180/")  //Server URL
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static UserService getUserService(){
        //Returns an interface instance

        UserService userService = getRetrofit().create(UserService.class);

        return userService;
    }

}
