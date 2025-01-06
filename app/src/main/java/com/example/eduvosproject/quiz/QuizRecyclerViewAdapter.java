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

public class QuizRecyclerViewAdapter extends RecyclerView.Adapter<QuizRecyclerViewAdapter.QuizViewHolder> {

    private final QuizRecyclerViewInterface quizRecyclerViewInterface;

    Context context;
    ArrayList<QuizItems> quizItemModels;


    public QuizRecyclerViewAdapter(Context context, ArrayList<QuizItems> quizItemModels, QuizRecyclerViewInterface quizRecyclerViewInterface) {
        this.context = context;
        this.quizItemModels = quizItemModels;
        this.quizRecyclerViewInterface = quizRecyclerViewInterface;
    }

    @NonNull
    @Override
    public QuizRecyclerViewAdapter.QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout, giving a look to our rows.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quiz_item_view, parent, false);
        return new QuizRecyclerViewAdapter.QuizViewHolder(view, quizRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizRecyclerViewAdapter.QuizViewHolder holder, int position) {

        holder.quizTitle.setText(quizItemModels.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return quizItemModels.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {

        TextView quizTitle;

        public QuizViewHolder(@NonNull View itemView, QuizRecyclerViewInterface quizRecyclerViewInterface) {
            super(itemView);

            quizTitle = itemView.findViewById(R.id.quiz_title);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Decides which position of the recycleView you clicked on so that it can open
                    // corresponding view.
                    if (quizRecyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            quizRecyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });


        }
    }
}
