package com.example.elearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class mycourse extends AppCompatActivity {

    RecyclerView recyclerView;
    MyCourseAdapter myCourseAdapter;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mycourse);

        recyclerView = findViewById(R.id.re2);
        next = findViewById(R.id.next);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        ArrayList<Course> items = new ArrayList<>();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("enrollments");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                items.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    Course data = item.getValue(Course.class);
                    if (data != null) {
                        items.add(data);  // Add course data to the list
                    }
                }
                if (items.isEmpty()) {
                    Toast.makeText(mycourse.this, "No courses enrolled yet.", Toast.LENGTH_SHORT).show();
                }
                if (myCourseAdapter == null) {
                    myCourseAdapter = new MyCourseAdapter(getApplicationContext(), items);
                    recyclerView.setAdapter(myCourseAdapter);
                } else {
                    myCourseAdapter.notifyDataSetChanged();  // Notify adapter about data changes
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error appropriately
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),mycourse1.class));
            }
        });

    }
}
