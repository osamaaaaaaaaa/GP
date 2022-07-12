package com.example.database.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.MainActivity;
import com.example.database.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Log_In extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    TextView tx_reg;
    Button bt_login;
    EditText ed_email ,ed_password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);
        tx_reg = findViewById(R.id.tx_create_new_acc);
        bt_login=findViewById(R.id.bt_login);
        ed_email=findViewById(R.id.ed_login_username);
        ed_password=findViewById(R.id.ed_login_password);
        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile",MODE_PRIVATE).edit();
        SharedPreferences prefs = getSharedPreferences("MyPrefsFile", MODE_PRIVATE);
        String name = prefs.getString("name","");//"No name defined" is the default value.
        String pass = prefs.getString("pass", "");

        ed_email.setText(name);
        ed_password.setText(pass);








        tx_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));

            }

        });
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(ed_email.getText().toString()) || TextUtils.isEmpty(ed_password.getText().toString())){
                    Toast.makeText(Log_In.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    signin(ed_email.getText().toString().trim(),ed_password.getText().toString());

                    editor.putString("name" ,ed_email.getText().toString());
                    editor.putString("pass",ed_password.getText().toString());
                    editor.commit();
                }
            }
        });
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }
    public void signin(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            String user = mAuth.getUid();


                            startActivity(new Intent(getApplicationContext(), Home_page.class).putExtra("userId",user));

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Log_In.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}
