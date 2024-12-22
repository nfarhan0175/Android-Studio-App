package com.example.elearning;

import static java.util.Locale.filter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    SearchView search;
    TextView textView;
    RecyclerView recyclerView;
    ImageView profile;
    CardView webcard;
    BottomNavigationView bottomNavigationView;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    int img[]={R.drawable.webpy,R.drawable.javascript,R.drawable.flutter,R.drawable.python,R.drawable.physics,R.drawable.english,R.drawable.math,R.drawable.business};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("register");

        //      search recycle     //
        search=findViewById(R.id.search);
        search.clearFocus();
        //      recycle     //
        recyclerView = findViewById(R.id.re);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),img);
        recyclerView.setAdapter(myAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        //      recycle     //
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                myAdapter.filterList(s);
                return true;
            }
        });
        //      search recycle     //

        //     info profile details    //
        profile = findViewById(R.id.acc_profile);
        loadProfileImage();
        textView = findViewById(R.id.info);
//        SharedPreferences preferences = getSharedPreferences("elogin",MODE_PRIVATE);
//        String username = preferences.getString("Username",null);
//        textView.setText(String.valueOf(username));
        if (user != null) {
            String userId = user.getUid();  // Get the current user's UID

            // First try to fetch the username from Firebase Realtime Database
            databaseReference.child(userId).child("username").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        String username = task.getResult().getValue(String.class);
                        if (username != null) {
                            textView.setText(username);  // Set the username in the TextView
                        } else {
                            // If no username is found, fallback to email
                            textView.setText(user.getEmail());
                        }
                    } else {
                        Toast.makeText(Home.this, "Failed to fetch username", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        //     info profile details    //

        //      bottomNavigation        //
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.book){
                    startActivity(new Intent(getApplicationContext(), mycourse.class));
                }
                else if(item.getItemId()==R.id.message){
                    startActivity(new Intent(getApplicationContext(), message.class));
                }
                else if (item.getItemId()==R.id.account) {
                    startActivity(new Intent(getApplicationContext(), account.class));
                }
                return true;
            }
        });
        //      bottomNavigation        //

        webcard = findViewById(R.id.WebCard);
        webcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),webdev.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadProfileImage() {
        SharedPreferences sharedPreferencesImg = getSharedPreferences("ProfilePic", MODE_PRIVATE);
        String imageUriString = sharedPreferencesImg.getString("profileImageUri", null);
        if(imageUriString != null){
            Uri imageUri = Uri.parse(imageUriString);
            Glide.with(this)
                    .load(imageUri)
                    .into(profile);
        }
    }

}
