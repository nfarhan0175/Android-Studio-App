package com.example.elearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class account extends AppCompatActivity {

    ImageView imageView;
    Button btn_logout;
    TextView textname,textmail,textphone;

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private final int REQUEST_GALLERY_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);

        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("register");

        //      set profile image      //
        imageView = findViewById(R.id.imageView);
        loadProfileImage();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_GALLERY_IMAGE_CAPTURE);

            }
        });
        //      set profile image      //

        //      info        //
        textname = findViewById(R.id.name);
        textmail = findViewById(R.id.email);
        textphone = findViewById(R.id.contact);
//        SharedPreferences preferences = getSharedPreferences("elogin",MODE_PRIVATE);
//        String username = preferences.getString("Username",null);
//        String email = preferences.getString("Email",null);
//        String Contact = preferences.getString("Contact",null);
//        textname.setText("Name: "+String.valueOf(username));
//        textmail.setText("Email: "+String.valueOf(email));
//        textphone.setText("Contact: "+String.valueOf(Contact));

        if (user != null) {
            String userId = user.getUid();  // Get the current user's UID
            // Fetch username, email, and phone from Firebase
            databaseReference.child(userId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        String username = task.getResult().child("username").getValue(String.class);
                        String mail = task.getResult().child("email").getValue(String.class);
                        String phone = task.getResult().child("contact").getValue(String.class);

                        if (username != null) {
                            textname.setText("Name: " + username);  // Set the username
                            textmail.setText("Email: " + mail);    // Set the email
                            textphone.setText("Phone: " + phone);  // Set the phone number
                        }
                    }
                }
            });
        }
        //      info        //

        //   logout button    //
        btn_logout = findViewById(R.id.logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SharedPreferences preferences = getSharedPreferences("elogin",MODE_PRIVATE);
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putBoolean("flag",false);
//                editor.apply();
                auth.signOut();
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });
        //      logout button    //

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_GALLERY_IMAGE_CAPTURE){
                Uri imageUri = data.getData();
                displayProfileImage(imageUri);
                saveProfileImage(imageUri);
            }
        }
    }

    private void saveProfileImage(Uri imageUri) {
        SharedPreferences sharedPreferencesImg = getSharedPreferences("ProfilePic", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesImg.edit();
        editor.putString("profileImageUri", imageUri.toString());
        editor.apply();
    }
    private void loadProfileImage() {
        SharedPreferences sharedPreferencesImg = getSharedPreferences("ProfilePic", MODE_PRIVATE);
        String imageUriString = sharedPreferencesImg.getString("profileImageUri", null);
        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            displayProfileImage(imageUri);
        }
    }

    private void displayProfileImage(Uri imageUri) {
        Glide.with(this)  // Using Glide to load the image
                .load(imageUri)
                .into(imageView);
    }
}