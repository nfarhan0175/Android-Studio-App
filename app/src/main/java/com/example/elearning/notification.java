package com.example.elearning;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.messaging.FirebaseMessaging;

public class notification extends AppCompatActivity {

    TextView notificationTitle, notificationMessage, notificationTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);

        notificationTitle = findViewById(R.id.notification_title);
        notificationMessage = findViewById(R.id.message);
        notificationTime = findViewById(R.id.notification_time);

//        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        String token = task.getResult();
//                        System.out.println(token);
//                        EditText tokenTextView = findViewById(R.id.token);
//                        tokenTextView.setText(token);
//                        // You can send the token to your server here to send notifications
//                    }
//                });

        // Get the data from the Intent
        if (getIntent() != null && getIntent().getExtras() != null) {
            String title = getIntent().getStringExtra("title");
            String message = getIntent().getStringExtra("message");
            String time = getIntent().getStringExtra("time");

            notificationTitle.setText(title);
            notificationMessage.setText(message);
//            notificationTime.setText(time);

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }
}