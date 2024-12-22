package com.example.elearning;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterr extends RecyclerView.Adapter<MyAdapterr.MyViewHolder> {

    Context context;
    String[] WebCourse,batchWeb;
    int imageView[];
    public MyAdapterr(Context applicationContext,int img[]) {
        context = applicationContext;
        WebCourse = context.getResources().getStringArray(R.array.WebCourse);
        batchWeb = context.getResources().getStringArray(R.array.batchWeb);
        imageView = img;
    }
    @NonNull
    @Override
    public MyAdapterr.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.web_list,parent,false);
        return new MyAdapterr.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterr.MyViewHolder holder, int position) {
        holder.textseat.setText(WebCourse[position]);
        holder.textCrsname.setText(batchWeb[position]);
        holder.image.setImageResource(imageView[position]);
    }

    @Override
    public int getItemCount() {
        return WebCourse.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cardView;
        TextView textseat,textCrsname;;
        ImageView image;
        public MyViewHolder(@NonNull View view) {
            super(view);
            cardView = view.findViewById(R.id.card);
            textseat = view.findViewById(R.id.seat);
            textCrsname = view.findViewById(R.id.crsname);
            image = view.findViewById(R.id.img);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            Intent intent;
            if (position >= 0) {
                String courseName = WebCourse[position];  // Use the course name if needed
                if (courseName.equals("Web development with Python")) {
                    intent = new Intent(view.getContext(), python.class);  // Adjust accordingly
                    view.getContext().startActivity(intent);
                }
            }
        }
    }
}
