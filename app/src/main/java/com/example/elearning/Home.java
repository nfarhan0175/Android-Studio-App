package com.example.elearning;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    AutoCompleteTextView autolist;
    ListView listView;
    ArrayList<String> autolists = new ArrayList<>();
    ArrayList<String>listvals = new ArrayList<>();
    TextView textname,textemail,textcontact,textacc,textmsg,textnoti;
    EditText textView;
    RecyclerView recyclerView;
    int img[]={R.drawable.physics,R.drawable.chemistry,R.drawable.math,R.drawable.google,R.drawable.google,R.drawable.ict};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        //      data pass      //
        autolist = findViewById(R.id.autoComplete);
        listvals.add("Physics");
        listvals.add("Chemistry");
        listvals.add("Biology");
        listvals.add("English");
        listvals.add("Math");

        ArrayAdapter<String>autoAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_dropdown_item_1line,listvals);
        autolist.setAdapter(autoAdapter);
        autolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                autolist.getText();
            }
        });
        Intent getDAta = getIntent();
        String  name = getDAta.getStringExtra("Name");
        String  email = getDAta.getStringExtra("Email");
        String  Contact = getDAta.getStringExtra("Contact");

        textView = findViewById(R.id.info);
        textname = findViewById(R.id.name);
        textemail = findViewById(R.id.email);
        textcontact = findViewById(R.id.contact);

        textmsg = findViewById(R.id.msg);
        textacc = findViewById(R.id.acc);
        textnoti = findViewById(R.id.noti);

        if (name != null) {
            textView.setText(name);
        }

        textmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),message.class));
            }
        });
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        textnoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView notification = findViewById(R.id.notification);
                TextView notification1 = findViewById(R.id.message);
                notification.setText("new message");
                //notification1.setText("your new module unlocked");
                //startService(new Intent(getApplicationContext(),music.class));
                startActivity(new Intent(getApplicationContext(),notification.class));
                vibrator.vibrate(3000);

            }
        });
        textacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),Account.class));
            }
        });

        //      recycle     //
        recyclerView = findViewById(R.id.re);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),img);
        recyclerView.setAdapter(myAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        //      recycle     //

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
}
