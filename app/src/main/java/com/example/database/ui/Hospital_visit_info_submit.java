package com.example.database.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.database.R;

public class Hospital_visit_info_submit extends AppCompatActivity {
    String name;
    TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_visit_info_submit);
        name= getIntent().getStringExtra("name");
        tx=findViewById(R.id.tx);
        tx.setText(name);
    }
}