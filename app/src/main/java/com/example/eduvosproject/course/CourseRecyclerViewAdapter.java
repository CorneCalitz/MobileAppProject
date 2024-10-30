package com.example.eduvosproject.course;

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

public class CourseRecyclerViewAdapter extends RecyclerView.Adapter<CourseRecyclerViewAdapter.CourseViewHolder> {

    private final CourseRecyclerViewInterface courseRecyclerViewInterface;

    Context context;
    ArrayList<CourseItemModel> courseItemModels;

    public CourseRecyclerViewAdapter(Context context, ArrayList<CourseItemModel> courseItemModels, CourseRecyclerViewInterface courseRecyclerViewInterface){
        // Constructor for our adapter
        this.context = context;
        this.courseItemModels = courseItemModels;
        this.courseRecyclerViewInterface = courseRecyclerViewInterface;
    }

    @NonNull
    @Override
    public CourseRecyclerViewAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This is where we inflate the layout ie. giving a look to our rows.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.course_item_view, parent, false);
        return new CourseRecyclerViewAdapter.CourseViewHolder(view, courseRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseRecyclerViewAdapter.CourseViewHolder holder, int position) {
        // Assigning values to each of our rows we created in course_item_view based on their
        // position in the recycler view.

        holder.tvName.setText(courseItemModels.get(position).getName());
        holder.tvDescription.setText(courseItemModels.get(position).getDescription());
        holder.ivCourse.setImageResource(courseItemModels.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        // the recycler view wants to know the number of items that you plan on displaying.
        return courseItemModels.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        // Here we get the views from our course_item_view layout file.
        // Acts similar to a onCreate method.

        ImageView ivCourse;
        TextView tvName, tvDescription;

        public CourseViewHolder(@NonNull View itemView, CourseRecyclerViewInterface courseRecyclerViewInterface) {
            super(itemView);

            ivCourse = itemView.findViewById(R.id.course_iv);
            tvName = itemView.findViewById(R.id.course_name_tv);
            tvDescription = itemView.findViewById(R.id.course_description_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (courseRecyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            courseRecyclerViewInterface.onItemClick(pos);
                        }

                    }
                }
            });


        }
    }
}
