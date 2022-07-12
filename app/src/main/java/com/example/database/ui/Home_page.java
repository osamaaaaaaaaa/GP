package com.example.database.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.database.R;
import com.example.database.fragments.Home_fragment;
import com.example.database.fragments.setting_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Home_page extends AppCompatActivity {
    Button signout;
    TextView textView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);



        bottomNavigationView=findViewById(R.id.bottomnavview2);
        fab=findViewById(R.id.fab);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new Home_fragment(),"MY_FRAGMENT").commit();








    }
    int i=1;
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                if (i == 2) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.left_to_right,R.anim.right_to_left).replace(R.id.fragment, new Home_fragment()).commit();
                    i = 1;

                    return true;
                }
                break;

            case R.id.setting:
                if (i == 1) {
                    getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.push_left_in,R.anim.push_left_out).replace(R.id.fragment, new setting_fragment()).commit();
                   i = 2;


                    return true;
                }
                break;
        }return false;
    }
   public void hidebottombar(){
        bottomNavigationView.setVisibility(View.INVISIBLE);
        fab.setVisibility(View.INVISIBLE);
   }
    public void showbottombar(){
        bottomNavigationView.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
    }

    }
