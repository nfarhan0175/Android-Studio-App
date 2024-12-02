package com.example.elearning;

import static android.Manifest.permission.CALL_PHONE;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class message extends AppCompatActivity {

    Button btn_dial,btn_email,btn_sms,back;
    EditText sub,sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_message);

        btn_dial = findViewById(R.id.dial);
        btn_email = findViewById(R.id.email);
        btn_sms = findViewById(R.id.sms);
        back = findViewById(R.id.back);

        sub = findViewById(R.id.sub);
        sms = findViewById(R.id.sms1);

        btn_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(message.this,new String[]{CALL_PHONE},1);
                }
                else{
                    Intent idial = new Intent(Intent.ACTION_CALL);
                    idial .setData(Uri.parse("tel: 01749555935"));
                    startActivity(idial);
                }
            }
        });

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iEmail = new Intent(Intent.ACTION_SEND);
                String x = sub.getText().toString();
                String z = sms.getText().toString();
                iEmail.setType("message/rfc822");
                iEmail.putExtra(Intent.EXTRA_EMAIL , new String[]{"minaakter2105@gmail.com"});
                iEmail.putExtra(Intent.EXTRA_SUBJECT,x);
                iEmail.putExtra(Intent.EXTRA_TEXT,z);
                startActivity(Intent.createChooser(iEmail," send by "));
            }
        });
        btn_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent isms = new Intent(Intent.ACTION_SENDTO);
                isms.setType("text/plain");
                isms.setData(Uri.parse("sms: 01749555935"));
                startActivity(Intent.createChooser(isms, " send by "));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Home.class));
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}