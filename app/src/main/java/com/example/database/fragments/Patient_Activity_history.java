package com.example.database.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.R;
import com.example.database.adapters.recycler_view_activites;
import com.example.database.adapters.recycler_view_hospitals;
import com.example.database.classes.Activity_class;
import com.example.database.classes.Hospital_class;
import com.example.database.ui.Home_page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Patient_Activity_history extends Fragment {

    FirebaseFirestore db ;
    FirebaseAuth auth;
    RecyclerView rec_activity,rec_hospitals;
    TextView tx_nodata;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_patient__activity_history, container, false);
        db= FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        String userId=auth.getUid();
        rec_activity=view.findViewById(R.id.rec_activity);
        tx_nodata=view.findViewById(R.id.tx_nodata);




        ReturnActivityData(userId);
        OnBackButtonPressed(view);

        return view;
    }
    private void OnBackButtonPressed(View view){
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        Home_page home_page=(Home_page)getActivity();

                        home_page. getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.bottom_to_up,R.anim.up_to_bottom).replace(R.id.fragment, new Home_fragment()).commit();

                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void ReturnActivityData(String userId){
        db.collection("userHomeVisitAppointment")
                  .document(userId).collection("orders").get()
          .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.getData()!=null) {
                            tx_nodata.setVisibility(View.INVISIBLE);
                            ArrayList<Activity_class>activites =new ArrayList<>();
                            activites.add(new Activity_class(document.get("name").toString(),document.get("address").toString(),"Category: " +document.get("status").toString()));


                            recycler_view_activites adapter= new recycler_view_activites(activites);
                            rec_activity.setAdapter(adapter);
                            rec_activity.setLayoutManager(new LinearLayoutManager(getContext()));
                        }else{
                            tx_nodata.setVisibility(View.VISIBLE);


                        }

                    }
                } else {
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}