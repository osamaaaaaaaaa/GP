package com.example.database.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Locale;

public class popular_hospitals extends AppCompatActivity {
    TextView tx_name,tx_address, tx_description,tx_location;
    Button bt_donate;
    String link ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView imageView;
    double longatatuide,shortattiute;
    Home_page home_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_hospitals);
        tx_name =findViewById(R.id.pop_hosp_name);
        tx_address=findViewById(R.id.pop_hosp_address);
        tx_description=findViewById(R.id.tx_description);
        bt_donate=findViewById(R.id.bt_donate);
        imageView=findViewById(R.id.im_pop);
       tx_location=findViewById(R.id.tx_location);
        home_page=new Home_page();
         tx_location.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String uri = String.format(Locale.ENGLISH, "geo:%f,%f", longatatuide, shortattiute);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
        }
    });


            // get name from home_fragment
         String name=getIntent().getStringExtra("name");
     bt_donate.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             Intent i = new Intent(Intent.ACTION_VIEW);
             i.setData(Uri.parse(link));
             startActivity(i);
         }
     });


            switch (name){
                case "57357":
                    imageView.setImageResource(R.drawable.l57357);
                    longatatuide=30.023304727999705;
                    shortattiute=31.237949599141007;
                    break;
                case "Magdy yaucub":
                    imageView.setImageResource(R.drawable.magdy);
                    longatatuide=30.0606951110172;
                    shortattiute=31.224521041470997;
                    break;
                case "abbassia":
                    bt_donate.setVisibility(View.GONE);
                    imageView.setImageResource(R.drawable.abbassia);
                    longatatuide=30.068203848751406;
                    shortattiute=31.293483841471122;
                    break;
                case "dar elfouad":
                    imageView.setImageResource(R.drawable.dar);
                    bt_donate.setVisibility(View.GONE);
                    longatatuide=29.997434560306;
                    shortattiute=30.967334623368238;
                    break;

                default:
                    break;

            }

        HospitalView(name);


        }
        private void HospitalView(String name){
            DocumentReference docRef = db.collection("popular_hospitals").document(name);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            tx_name.setText(document.get("name").toString());
                            tx_address.setText(document.get("address").toString());
                            tx_description.setText(document.get("description ").toString());
                         link=document.get("donation").toString();
                        } else {
                            //    Log.d(TAG, "No such document");
                        }
                    } else {
                        // Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }

}