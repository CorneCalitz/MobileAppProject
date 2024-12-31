package com.example.eduvosproject.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// ApiClient should look like this
public class ApiClient {

    // Replace with your actual server IP address if running on a physical device
    private static final String BASE_URL = "http://10.0.2.2:8888/edu_api/"; // Use 10.0.2.2 for emulator or your machine IP for physical device
    private static Retrofit retrofit;

    // Method to get Retrofit client
    public static Retrofit getClient() {
        if (retrofit == null) {
            // Set up logging interceptor for HTTP requests and responses
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY); // Log all request and response data

            // Create an OkHttpClient with the logging interceptor
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();

            // Create a Gson instance with lenient parsing enabled for handling malformed JSON gracefully
            Gson gson = new GsonBuilder()
                    .setLenient() // Enable lenient parsing
                    .create();



                        // Build Retrofit instance
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8888/edu_api/")
                    .client(client) // Use OkHttpClient with logging
                    .addConverterFactory(GsonConverterFactory.create(gson)) // Use Gson converter with lenient parsing
                    .build();
        }
        return retrofit;
    }

    // Method to get the UserService interface to interact with the backend API
    public static UserService getUserService() {
        return getClient().create(UserService.class);
    }
}
