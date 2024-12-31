package com.example.eduvosproject.quiz;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eduvosproject.LoginResponse;
import com.example.eduvosproject.R;
import com.example.eduvosproject.api.ApiClient;
import com.example.eduvosproject.quiz.quiz_attempt.QuizAttemptActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizFragment extends Fragment {

    private static final String ARG_PARAM1 = "jsonString";

    private RecyclerView recyclerViewQuiz;
    private QuizRecyclerViewAdapter quizRecyclerViewAdapter;

    private String jsonString;
    LoginResponse loginResponse;
    int profileId;

    //Create arraylist of items that we are placing in the quiz item view.
    ArrayList<QuizItems> quizItems = new ArrayList<>();

    public QuizFragment() {
        //empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Inflate layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            jsonString = getArguments().getString(ARG_PARAM1);
            loginResponse = new Gson().fromJson(jsonString, LoginResponse.class);
        }

        profileId = Integer.parseInt(loginResponse.profile.getProfile_id());

        Call<ArrayList<QuizItems>> fetchQuizListCall = ApiClient.getUserService().quizPost(profileId);

        fetchQuizListCall.enqueue(new Callback<ArrayList<QuizItems>>() {
            @Override
            public void onResponse(Call<ArrayList<QuizItems>> call, Response<ArrayList<QuizItems>> response) {

                //Populates quizItems model
                for (int i = 0; i<response.body().size(); i++) {
                    quizItems.add(new QuizItems(response.body().get(i).id, response.body().get(i).name, response.body().get(i).score));
                }

                //Creates recyclerView object
                RecyclerView recyclerView = view.findViewById(R.id.recyclerviewQuiz);

                //Create quizInterface object
                QuizRecyclerViewInterface quizInterface = new QuizRecyclerViewInterface() {
                    @Override
                    public void onItemClick(int position) {

                        String quizItemString;
                        quizItemString = new Gson().toJson(quizItems.get(position));

                        Intent intent = new Intent(getActivity(), QuizAttemptActivity.class).putExtra("jsonString", quizItemString);
                        startActivity(intent);
                    }
                };

                //Create an instance of the adapter
                QuizRecyclerViewAdapter adapter = new QuizRecyclerViewAdapter(view.getContext(), quizItems, quizInterface);
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<QuizItems>> call, Throwable t) {
                Toast.makeText(getContext(),"Something went wrong. Try reloading",Toast.LENGTH_LONG).show();

            }
        });

    }
}
