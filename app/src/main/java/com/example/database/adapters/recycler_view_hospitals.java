package com.example.database.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.R;
import com.example.database.classes.Hospital_class;
import com.example.database.ui.Hospital_visit_info_submit;
import com.example.database.ui.Hospital_visit_list_view;
import com.example.database.ui.Log_In;

import java.util.ArrayList;

public class recycler_view_hospitals extends RecyclerView.Adapter<recycler_view_hospitals.HospitalViewHolder>{


      Context context;
        ArrayList<Hospital_class> hospitalsList =new ArrayList<>();


        Hospital_visit_list_view hospital_visitListview =new Hospital_visit_list_view();

    public recycler_view_hospitals(ArrayList<Hospital_class> hospitalsList , Context context) {
        this.hospitalsList = hospitalsList;
        this.context=context;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_list_ryc,null,false);
      HospitalViewHolder hospitalViewHolder=new HospitalViewHolder(v);
        return hospitalViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
                    holder.tx_hos_name.setText(hospitalsList.get(position).getName());
                    holder.tx_hos_address.setText(hospitalsList.get(position).getAddress());
                    Context c= holder.bt_submit.getContext();


                    holder.bt_submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        c.startActivity(new Intent(c, Hospital_visit_info_submit.class).putExtra("name",hospitalsList.get(position).getName()));


                        }
                    });
    }


        public void setHospitalsList(ArrayList<Hospital_class> hospitalsList){
        this.hospitalsList=hospitalsList;
        notifyDataSetChanged();
        }


    @Override
    public int getItemCount() {
        return hospitalsList.size();
    }




    class HospitalViewHolder extends RecyclerView.ViewHolder{

        TextView tx_hos_name;
        TextView tx_hos_address;
        Button bt_submit;
    public HospitalViewHolder(@NonNull View itemView) {
        super(itemView);

        tx_hos_name=itemView.findViewById(R.id.tx_hosp_adap_name);
        tx_hos_address=itemView.findViewById(R.id.tx_hosp_adap_address);
        bt_submit=itemView.findViewById(R.id.btboking);

    }
}

}
