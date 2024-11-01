package com.example.eduvosproject.quiz;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.eduvosproject.R;
import java.util.ArrayList;

public class QuizFragment extends Fragment {

    private RecyclerView recyclerViewQuiz;
    private ArrayList<QuizItemModel> quizModels;
    private QuizRecyclerViewAdapter quizRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        recyclerViewQuiz = view.findViewById(R.id.recyclerviewQuiz);
        recyclerViewQuiz.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize quiz data
        quizModels = new ArrayList<>();
        quizModels.add(new QuizItemModel(R.drawable.baseline_person_24, "Quiz 1: Core values", "Learn about our core values."));
        quizModels.add(new QuizItemModel(R.drawable.baseline_settings_24, "Quiz 2: Wines and liquor", "Test your knowledge on wines and liquors."));
        quizModels.add(new QuizItemModel(R.drawable.ic_home_black_24dp, "Quiz 3: Etiquette", "Quiz on etiquette and protocols."));

        quizRecyclerViewAdapter = new QuizRecyclerViewAdapter(getContext(), quizModels);
        recyclerViewQuiz.setAdapter(quizRecyclerViewAdapter);

        return view;
    }
}
