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
import androidx.recyclerview.widget.RecyclerView;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    String[] crs;
    int imageView[];
    public MyAdapter(Context applicationContext,int img[]) {
        context = applicationContext;
        crs = context.getResources().getStringArray(R.array.course);
        imageView = img;
    }
    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(crs[position]);
        holder.image.setImageResource(imageView[position]);
    }

    @Override
    public int getItemCount() {
        return crs.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout linearLayout;
        TextView textView;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.list);
            textView=itemView.findViewById(R.id.text);
            image = itemView.findViewById(R.id.img);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            if(position==0){
               Intent intent = new Intent(v.getContext(), physics.class);
                v.getContext().startActivity(intent);}

            Toast.makeText(v.getContext(), position+": " +crs[position], Toast.LENGTH_SHORT).show();
        }
    }
}

