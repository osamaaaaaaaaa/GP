package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import com.example.database.ui.Home_page;
import com.example.database.ui.Log_In;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    TextView textView;
    Button bt;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
     //   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        textView.animate().alpha(0).setDuration(4000);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (currentUser!=null) {
                    startActivity(new Intent(getApplicationContext(), Home_page.class) );
                }else {
                    startActivity(new Intent(getApplicationContext(), Log_In.class));
                }

            }
        }, 4000);



    }

}
