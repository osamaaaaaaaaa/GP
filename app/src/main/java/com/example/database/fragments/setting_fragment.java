package com.example.database.fragments;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.database.MainActivity;
import com.example.database.R;
import com.example.database.classes.edit_profile_info;
import com.example.database.ui.Home_page;
import com.example.database.ui.Log_In;
import com.google.firebase.auth.FirebaseAuth;


public class setting_fragment extends Fragment {

    Button bt_edit_prfile,bt_changepass,bt_signout,bt_dark,bt_light;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_setting_fragment, container, false);

    bt_edit_prfile=view.findViewById(R.id.bt_editprofile);
    bt_changepass=view.findViewById(R.id.bt_changepassword);
    bt_signout=view.findViewById(R.id.bt_signout);
    bt_dark=view.findViewById(R.id.bt_dark);
    bt_light=view.findViewById(R.id.bt_light);


    bt_light.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    });
    bt_dark.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    });


    bt_signout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), Log_In.class));
        }
    });







    bt_edit_prfile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getActivity(), edit_profile_info.class));

        }
    });



        return view ;
    }
    private void OnBackButtonPressed(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        Home_page home_page = (Home_page) getActivity();

                        home_page.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.left_to_right, R.anim.right_to_left).replace(R.id.fragment, new Home_fragment()).commit();


                        return true;
                    }
                }
                return false;
            }
        });
    }
}