package com.example.elearning;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class mycourse1 extends AppCompatActivity {

    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mycourse1);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        callFragment(new FirstFragment(),0);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FirstFragment(),1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new SecondFragment(),1);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void callFragment(Fragment fragment, int status)//add,replace
    {
        FragmentManager fragmentManager = getSupportFragmentManager();//switch fragment
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(status == 0){
            fragmentTransaction.add(R.id.container,fragment);
        }
        else{
            fragmentTransaction.replace(R.id.container,fragment);
        }
        fragmentTransaction.commit();
    }
}