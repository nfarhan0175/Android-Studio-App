package com.example.elearning;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class webdev extends AppCompatActivity {

    RecyclerView recyclerView;
    int img[]={R.drawable.webpy,R.drawable.javascript,R.drawable.php,R.drawable.wordpress,R.drawable.vue,R.drawable.tailwind};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_webdev);

        recyclerView = findViewById(R.id.re1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        MyAdapterr myAdapterr = new MyAdapterr(getApplicationContext(),img);
        recyclerView.setAdapter(myAdapterr);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}