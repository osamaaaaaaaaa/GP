package com.example.database.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.R;
import com.example.database.classes.register_class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    Button bt_reg;
    EditText name,email,password,confirmpassword,mobile;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    register_class user_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bt_reg = findViewById(R.id.bt_register);
        name = findViewById(R.id.ed_reg_name);
        email = findViewById(R.id.ed_reg_email);
        password = findViewById(R.id.ed_reg_pass);
        confirmpassword = findViewById(R.id.ed_reg_conf_pass);
        mobile = findViewById(R.id.ed_reg_phone);
        mAuth = FirebaseAuth.getInstance();


        bt_reg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

     if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ||TextUtils.isEmpty(confirmpassword.getText().toString())||TextUtils.isEmpty(mobile.getText().toString()))
     {
         Toast.makeText(Register.this, "please enter all fields", Toast.LENGTH_SHORT).show();

     }
        if (password.getText().toString().equals(confirmpassword.getText().toString()) && password.getText().toString().length()>=8){
            adduser();

        }
        else{
            Toast.makeText(Register.this, "error in password", Toast.LENGTH_SHORT).show();
        }


    }
});





    }


    private void adduser(){
        user_info = new register_class(name.getText().toString().trim(), email.getText().toString().trim(), password.getText().toString(), Integer.parseInt(mobile.getText().toString()));

        mAuth.createUserWithEmailAndPassword(user_info.getEmail(), user_info.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            String userId = mAuth.getUid();
                            addinfo(userId);

                            Toast.makeText(Register.this, "Welcom To Med Factory", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, Home_page.class).putExtra("userId",userId));
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    private void addinfo(String userId){

        Map<String, Object> user = new HashMap<>();
        user.put("name",name.getText().toString());
        user.put("email",email.getText().toString());
        user.put("password",password.getText().toString());
        user.put("mobile",mobile.getText().toString());




        db.collection("users").document(userId)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

    }

}