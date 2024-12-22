package com.example.elearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
//            public void run() {
//                SharedPreferences sharedPreferences=getSharedPreferences("elogin",MODE_PRIVATE);
//                Boolean check = sharedPreferences.getBoolean("flag",false);
//                Intent intent;
//                if(check){
//                    intent=new Intent(getApplicationContext(), Home.class);
//                    startActivity(intent);
//                }
//                else {
//                    intent=new Intent(getApplicationContext(), login.class);
//                    startActivity(intent);
//                }
//
//            }
            public void run() {
                // Get current user from Firebase Authentication
                FirebaseUser currentUser = mAuth.getCurrentUser();
                Intent intent;

                // Check if the user is logged in
                if (currentUser != null) {
                    // User is signed in
                    intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                } else {
                    // No user is signed in, navigate to login screen
                    intent = new Intent(getApplicationContext(), login.class);
                    startActivity(intent);
                }

                // Optionally, finish MainActivity to prevent the user from coming back using the back button
                finish();
            }
        },4000);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}