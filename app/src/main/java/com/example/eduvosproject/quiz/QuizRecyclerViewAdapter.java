package com.example.eduvosproject.quiz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eduvosproject.R;
import java.util.ArrayList;

public class QuizRecyclerViewAdapter extends RecyclerView.Adapter<QuizRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<QuizItemModel> quizItemModels;

    public QuizRecyclerViewAdapter(Context context, ArrayList<QuizItemModel> quizItemModels) {
        this.context = context;
        this.quizItemModels = quizItemModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quiz_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizItemModel quizItemModel = quizItemModels.get(position);
        holder.quizImage.setImageResource(quizItemModel.getImage());
        holder.quizTitle.setText(quizItemModel.getName());
        holder.quizDescription.setText(quizItemModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return quizItemModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView quizImage;
        TextView quizTitle, quizDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quizImage = itemView.findViewById(R.id.quiz_icon);
            quizTitle = itemView.findViewById(R.id.quiz_title);
            quizDescription = itemView.findViewById(R.id.quiz_description);
        }
    }
}
