package com.example.elearning;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.telecom.InCallService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    String[] course,batch,price;
    int imageView[];
    List<String> courseList;
    public MyAdapter(Context applicationContext,int img[]) {
        context = applicationContext;
        course = context.getResources().getStringArray(R.array.course);
        batch = context.getResources().getStringArray(R.array.batch);
        price = context.getResources().getStringArray(R.array.price);
        imageView = img;
        courseList = new ArrayList<>();
        for (String c : course) {
            courseList.add(c);
        }
    }
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
//        holder.textView.setText(course[position]);
//        holder.textBatch.setText(batch[position]);
//        holder.textPrice.setText(price[position]);
//        holder.image.setImageResource(imageView[position]);

        String currentCourse = courseList.get(position); // Use the filtered course list
        int index = getIndexForCourse(currentCourse); // Get the index of the course in the original list
        holder.textView.setText(currentCourse);
        holder.textBatch.setText(batch[index]);
        holder.textPrice.setText(price[index]);
        holder.image.setImageResource(imageView[index]);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
//        return course.length;
    }

    public void filterList(String query) {
        courseList.clear();
        if (query.isEmpty()) {
            // If the search query is empty, show all items
            for (String c : course) {
                courseList.add(c);
            }
        } else {
            // Filter the list based on the search query
            for (String c : course) {
                if (c.toLowerCase().contains(query.toLowerCase())) {
                    courseList.add(c);
                }
            }
        }
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }
    private int getIndexForCourse(String courseName) {
        for (int i = 0; i < course.length; i++) {
            if (course[i].equals(courseName)) {
                return i;
            }
        }
        return -1; // Default value if not found
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout linearLayout;
        TextView textView,textBatch,textPrice;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.list);
            textView=itemView.findViewById(R.id.text);
            textBatch=itemView.findViewById(R.id.batch);
            textPrice=itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Intent intent;
            if(position==0){
                intent = new Intent(v.getContext(), python.class);// Adjust accordingly
                v.getContext().startActivity(intent);
            }
            if(position==4){
                intent = new Intent(v.getContext(), physics.class);// Adjust accordingly
                v.getContext().startActivity(intent);
            }
        }
    }
}

