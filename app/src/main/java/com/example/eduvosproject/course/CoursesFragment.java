package com.example.eduvosproject.course;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eduvosproject.LoginResponse;
import com.example.eduvosproject.R;
import com.example.eduvosproject.api.ApiClient;
import com.example.eduvosproject.course.course_view.CourseViewActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CoursesFragment extends Fragment {

    private static final String ARG_PARAM = "jsonString";

    private RecyclerView recyclerViewCourse;
    private CourseRecyclerViewAdapter courseRecyclerViewAdapter;

    private String jsonString;
    LoginResponse loginResponse;

    // Create arraylist of items that we are placing in the course item view
    ArrayList<CourseItems> courseItems = new ArrayList<>();


    public CoursesFragment() {
        //empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate layout for this fragment
        return inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            String jsonString = getArguments().getString("jsonString");
            Log.d("CoursesFragment", "Received JSON: " + jsonString);

            if (jsonString != null) {
                LoginResponse loginResponse = new Gson().fromJson(jsonString, LoginResponse.class);
                Log.d("CoursesFragment", "Parsed login response: " + new Gson().toJson(loginResponse));
            }

            // GET method to fetch data.
            Call<ArrayList<CourseItems>> fetchCourseListCall = ApiClient.getUserService().courseItemsGet();

            fetchCourseListCall.enqueue(new Callback<ArrayList<CourseItems>>() {
                @Override
                public void onResponse(Call<ArrayList<CourseItems>> call, Response<ArrayList<CourseItems>> response) {

                    for (int i = 0; i < response.body().size(); i++) {
                        courseItems.add(new CourseItems(response.body().get(i).id, response.body().get(i).name, response.body().get(i).description));
                    }

                    //Creates recyclerView object
                    RecyclerView recyclerView = view.findViewById(R.id.recyclerviewCourses);

                    //Create courseInterface object
                    CourseRecyclerViewInterface courseInterface = new CourseRecyclerViewInterface() {
                        @Override
                        public void onItemClick(int position) {

                            String courseItemString;
                            courseItemString = new Gson().toJson(courseItems.get(position));

                            Intent intent = new Intent(getActivity(), CourseViewActivity.class).putExtra("jsonString", courseItemString);
                            startActivity(intent);

                        }
                    };

                    //Create an instance of the adapter
                    CourseRecyclerViewAdapter adapter = new CourseRecyclerViewAdapter(view.getContext(),
                            courseItems, courseInterface);

                    recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<ArrayList<CourseItems>> call, Throwable t) {
                    Toast.makeText(getContext(), "Something went wrong. Try reloading", Toast.LENGTH_LONG).show();
                }
            });

        }
    }
}



