package com.example.database.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.database.R;
import com.example.database.classes.Hospital_class;
import com.example.database.adapters.recycler_view_hospitals;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class Hospital_visit_list_view extends AppCompatActivity {
    RecyclerView rec_hospitals;
    FirebaseAuth auth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String name ,address;
    SearchView  searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_visit);

        rec_hospitals=findViewById(R.id.rec_hospitals);
        ArrayList<Hospital_class> hospitals=new ArrayList<>();
        auth=FirebaseAuth.getInstance();
        String userId=auth.getUid();
        searchView=findViewById(R.id.searchview);
        recycler_view_hospitals adapter=new recycler_view_hospitals(hospitals,getApplicationContext());



        GetHospitals(hospitals);







    }




    private void GetHospitals(ArrayList hospitals){

        db.collection("Hospitals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                hospitals.add(new Hospital_class(document.get("name").toString(),document.get("address").toString()));
                                recycler_view_hospitals adapter=new recycler_view_hospitals(hospitals,getApplicationContext());
                                rec_hospitals.setAdapter(adapter);
                                rec_hospitals.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                //   Toast.makeText(Hospital_visit.this, document.getData().toString(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


    }



}