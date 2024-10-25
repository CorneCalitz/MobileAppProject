package com.example.eduvosproject;

import android.graphics.drawable.Drawable;
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

import java.util.ArrayList;
import java.util.Objects;


public class CoursesFragment extends Fragment {

    // Create arraylist of items that we are placing in the course item view
    ArrayList<CourseItemModel> coursesModel = new ArrayList<>();

    // Create int array of drawables placed in course item view
    int [] testImages = {R.drawable.baseline_person_24, R.drawable.baseline_settings_24,
            R.drawable.ic_home_black_24dp,R.drawable.baseline_person_24,
            R.drawable.baseline_settings_24, R.drawable.ic_home_black_24dp,
            R.drawable.ic_home_black_24dp,R.drawable.baseline_person_24,
            R.drawable.baseline_settings_24, R.drawable.ic_home_black_24dp,
            R.drawable.baseline_person_24};


    private void setUpCourseItemModels(){
        String[] courseNames = getResources().getStringArray(R.array.courses_name);
        String[] courseDescription = getResources().getStringArray(R.array.courses_description);
        for (int i = 0; i<courseNames.length; i++){
            coursesModel.add(new CourseItemModel(testImages[i],courseNames[i],courseDescription[i]));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerviewCourses);

        setUpCourseItemModels();

        CourseRecyclerViewAdapter adapter = new CourseRecyclerViewAdapter(view.getContext(),
                coursesModel);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

}