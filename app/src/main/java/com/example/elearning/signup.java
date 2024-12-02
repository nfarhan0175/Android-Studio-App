package com.example.elearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signup extends AppCompatActivity {
    Button btn_signup,btn_login;
    EditText user,email,contact,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        user = findViewById(R.id.user);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contact);
        pass = findViewById(R.id.pass);
        btn_signup = findViewById(R.id.signup);
        btn_login = findViewById(R.id.login);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String User = user.getText().toString();
                String Email = email.getText().toString();
                String Contact = contact.getText().toString();
                String Pass = pass.getText().toString();

                if(!User.isEmpty()&&!Email.isEmpty()&&!Contact.isEmpty()&&!Pass.isEmpty()) {
                    Intent iNext;
                    iNext = new Intent(getApplicationContext(), Home.class);
                    iNext.putExtra("Name", User);
                    iNext.putExtra("Email", Email);
                    iNext.putExtra("Contact", Contact);
                    Toast.makeText(signup.this, "Succesfully signed up", Toast.LENGTH_SHORT).show();
                    startActivity(iNext);
                }
                else {
                    Toast.makeText(signup.this, "Enter All Information", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}