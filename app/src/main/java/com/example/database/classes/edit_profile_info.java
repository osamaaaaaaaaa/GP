package com.example.database.classes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.R;
import com.example.database.ui.Home_page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class edit_profile_info extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    EditText ed_name,ed_email,ed_mobile,ed_new_pass;
    Button bt_save;
    ArrayList <String> userinfo =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_info);

        mAuth = FirebaseAuth.getInstance();
        String id =mAuth.getUid().toString();

        UserData( id);
        ed_email=findViewById(R.id.ed_edit_profile_email);
        ed_email.setKeyListener(null);
        ed_name=findViewById(R.id.ed_edit_profile_name);
        ed_mobile=findViewById(R.id.ed_edit_profile_mobile);
        bt_save=findViewById(R.id.bt_edit_profile_save);
        ed_new_pass=findViewById(R.id.ed_new_pass);

                bt_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UpdateUserData(id);
                    }
                });




    }
    //return user data
    private void  UserData(String id){
     DocumentReference docRef = db.collection("users").document(id);
     docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
         @Override
         public void onComplete(@NonNull Task<DocumentSnapshot> task) {
             if (task.isSuccessful()) {
                 DocumentSnapshot document = task.getResult();
                 if (document.exists()) {
                     ed_name.setText(document.get("name").toString());
                     ed_email.setText(document.get("email").toString());
                     ed_mobile.setText(document.get("mobile").toString());


                 } else {

                 }
             } else {

             }
         }

     });
    }
    //update user data
    private void UpdateUserData(String id){
        String name,email,mobile,newPassword;
        name =ed_name.getText().toString();
        email=ed_email.getText().toString();
        mobile=ed_mobile.getText().toString();
        newPassword=ed_new_pass.getText().toString();

        db.collection("users").document(id)
                .update("name",name,"email",email,"mobile",mobile)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(edit_profile_info.this, "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Home_page.class));

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                                e.getMessage();
                    }
                });


        //update user password
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                        }
                    }
                });

    }

    }
