package com.example.elearning;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class python extends AppCompatActivity {

    ExtendedFloatingActionButton fab1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_python);

        fab1 = findViewById(R.id.extended_fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseTitle = "Web Development with Python";
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference("enrollments");
                String enrollmentId = databaseReference.push().getKey();
                Course course = new Course(courseTitle);

                if (enrollmentId != null) {
                    databaseReference.child(enrollmentId).setValue(course);  // Save the course data
                }

                TextView staticMessage = new TextView(python.this);
                staticMessage.setText("Pay in the following number: 01617735858");
                staticMessage.setPadding(16, 16, 16, 16);  // Optional: add padding for better spacing

                final EditText numberInput = new EditText(python.this);
                numberInput.setHint("enter transection id");
                numberInput.setInputType(InputType.TYPE_CLASS_NUMBER);

                LinearLayout layout = new LinearLayout(python.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(staticMessage);
                layout.addView(numberInput);

                // Create the AlertDialog with the TextView and EditText
                new AlertDialog.Builder(python.this)
                        .setTitle("Enrollment Success")
                        .setView(layout)  // Add both the TextView and EditText to the dialog
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String inputNumber = numberInput.getText().toString();

                                // Handle the input number (you can validate it if needed)
                                if (!inputNumber.isEmpty()) {
                                    try {
                                        int number = Integer.parseInt(inputNumber); // Parse the number
                                        // Do something with the number, like saving it or using it
                                        Toast.makeText(python.this, "You have successfully enrolled in " + courseTitle, Toast.LENGTH_SHORT).show();
                                    } catch (NumberFormatException e) {
                                        Toast.makeText(python.this, "Invalid number input!", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(python.this, "Please enter a valid number.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", null)  // Optional: Cancel button
                        .setCancelable(false)
                        .show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}