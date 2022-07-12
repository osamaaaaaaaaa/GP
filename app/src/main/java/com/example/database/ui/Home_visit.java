package com.example.database.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.database.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Home_visit extends AppCompatActivity {
    Button bt_submit;
    EditText name,mobile,notes,address;
    String special,area;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    Spinner spin_special;
    Spinner spin_area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_visit);
        mAuth = FirebaseAuth.getInstance();
        String userId=mAuth.getUid().toString();
        name=findViewById(R.id.ed_home_visit_name);
        mobile=findViewById(R.id.ed_home_visit_mobile);
        notes=findViewById(R.id.ed_home_visit_notes);
        address=findViewById(R.id.ed_home_visit_Address);
         spin_special = (Spinner) findViewById(R.id.spinner_special);
         spin_area = (Spinner) findViewById(R.id.spinner_area);
        bt_submit=findViewById(R.id.bt_homevisit_submit);
        mAuth = FirebaseAuth.getInstance();


      bt_submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              if (TextUtils.isEmpty(name.getText().toString())||

                TextUtils.isEmpty(mobile.getText().toString())||TextUtils.isEmpty(address.getText().toString())

              ||spin_special.getSelectedItem().equals("select speciality")||spin_area.getSelectedItem().equals("select area ")){


                  Toast.makeText(getApplicationContext(), "please complete all fields !", Toast.LENGTH_SHORT).show();


                      }else {
                          HomeVisitAppointment(userId);
                      }
                  }

      });

    }



   private void HomeVisitAppointment(String userId){
       Map<String, Object> userHomeVisitAppointment  = new HashMap<>();
       userHomeVisitAppointment.put("name",name.getText().toString());
       userHomeVisitAppointment.put("mobile",mobile.getText().toString());
       userHomeVisitAppointment.put("area",spin_area.getSelectedItem().toString());
       userHomeVisitAppointment.put("special",spin_special.getSelectedItem().toString());
       userHomeVisitAppointment.put("Notes",notes.getText().toString());
       userHomeVisitAppointment.put("address",address.getText().toString());
       userHomeVisitAppointment.put("status","Home Visit");




       db.collection("userHomeVisitAppointment").document(userId).collection("orders")
               .document().set(userHomeVisitAppointment)
               .addOnSuccessListener(new OnSuccessListener<Void>() {
                   @Override
                   public void onSuccess(Void aVoid) {

                       Toast.makeText(Home_visit.this, "Successfully appointment we will call you soon", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(getApplicationContext(),Home_page.class));


                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {

                       Toast.makeText(Home_visit.this, "please Try again later !", Toast.LENGTH_SHORT).show();
                   }
               });

   }

    }
