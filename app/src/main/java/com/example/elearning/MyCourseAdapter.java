package com.example.elearning;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyCourseAdapter extends RecyclerView.Adapter<MyCourseAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Course> items;
    int imageView[] = {R.drawable.webpy,R.drawable.physics};

    public MyCourseAdapter(Context context, ArrayList<Course> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.enrolled_course_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.enrolledcourse.setText(items.get(position).getTitle());
        if(items.get(position).getTitle().equals("Web Development with Python")){
            holder.enrolledcourseimg.setImageResource(imageView[0]);}
        if(items.get(position).getTitle().equals("Physics")){
            holder.enrolledcourseimg.setImageResource(imageView[1]);}

        holder.itemView.setOnClickListener(v -> {
            // Create intent and pass data (optional)
            Intent intent = new Intent(context, mycourse1.class);
            intent.putExtra("item_data", items); // Passing data to next Activity
            context.startActivity(intent); // Start the new Activity
        });
    }
    @Override
    public int getItemCount() {
        return items.size();  // Return the number of items in the list
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        LinearLayout card1;
        CardView card1;
        TextView enrolledcourse;
        ImageView enrolledcourseimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card1 = itemView.findViewById(R.id.card1);
            enrolledcourseimg = itemView.findViewById(R.id.enrolledcourseimg);
            enrolledcourse = itemView.findViewById(R.id.enrolledcourse);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            Toast.makeText(context, "position: " + position, Toast.LENGTH_SHORT).show();
            if (position == 0) {
                Intent intent = new Intent(context, mycourse1.class);
                context.startActivity(intent);
            }

        }
    }
}