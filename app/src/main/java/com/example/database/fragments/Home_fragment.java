package com.example.database.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.database.R;
import com.example.database.ui.Home_page;
import com.example.database.ui.Home_visit;
import com.example.database.ui.popular_hospitals;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Home_fragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    TextView nameBar;
    CardView bt_hospital_visit,bt_home_visit;
    String id;
    CardView cd_57357,cd_magdy_yaucub,cd_darelfouad,cd_abbassia;
    Button my_activity;
    String s;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
       id= mAuth.getUid().toString();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_home_fragment, container, false);
      bt_hospital_visit=view.findViewById(R.id.card_hospital_visit);
       bt_home_visit=view.findViewById(R.id.card_home_visit);
       cd_57357=view.findViewById(R.id.cd_57);
       cd_magdy_yaucub=view.findViewById(R.id.cd_magdy_yacoub);
       cd_abbassia=view.findViewById(R.id.cd_abassia);
       cd_darelfouad=view.findViewById(R.id.cd_dar_elfouad);
       my_activity=view.findViewById(R.id.bt_my_activity);
       nameBar=view.findViewById(R.id.tx_name_bar);
        Home_page home_page=(Home_page) getActivity();
        home_page.showbottombar();

       //Add bar name
       AddBarName(id);
       OnBackButtonPressed(view);





        cd_magdy_yaucub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startActivity(new Intent(getActivity(), popular_hospitals.class).putExtra("name","Magdy yaucub"));
                    }
                });
                cd_abbassia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startActivity(new Intent(getActivity(), popular_hospitals.class).putExtra("name","abbassia"));
                    }
                });
                cd_darelfouad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startActivity(new Intent(getActivity(), popular_hospitals.class).putExtra("name","dar elfouad"));
                    }
                });
                cd_57357.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {


                       startActivity(new Intent(getActivity(), popular_hospitals.class).putExtra("name","57357"));
                   }
               });
                my_activity.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                     home_page.hidebottombar();
                    home_page.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.bottom_to_up,R.anim.up_to_bottom).replace(R.id.fragment, new Patient_Activity_history()).commit();


                   }
               });
                bt_hospital_visit.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      home_page.hidebottombar();

                      home_page.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.bottom_to_up,R.anim.up_to_bottom).replace(R.id.fragment, new Hospital_visit_specialties_fragment()).commit();

                  }
              });
                bt_home_visit.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             startActivity(new Intent(getActivity(), Home_visit.class));
                             home_page.overridePendingTransition(R.anim.bottom_to_up,R.anim.up_to_bottom);
                         }
                     });

                return view;
            }




    public void AddBarName(String id){
        mAuth = FirebaseAuth.getInstance();
        DocumentReference docRef = db.collection("users").document(id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String name=document.get("name").toString();
                        nameBar.setText("Hi "+name+" \uD83D\uDC4B");
                    } else {

                    }
                } else {

                }
            }

        });
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

                        home_page.finishAffinity();
                        return true;
                    }
                }
                return false;
            }
        });
    }}