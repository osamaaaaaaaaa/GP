package com.example.database.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database.R;
import com.example.database.classes.Activity_class;

import java.util.ArrayList;

public class recycler_view_activites extends RecyclerView.Adapter<recycler_view_activites.ActivityViewHolder> {
        ArrayList<Activity_class> activity_list=new ArrayList<>();
    public recycler_view_activites( ArrayList<Activity_class> activity_list) {
        this.activity_list=activity_list;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_ryc,null,false);
       ActivityViewHolder activityViewHolder=new ActivityViewHolder(v);

        return activityViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
    holder.tx_active_name.setText(activity_list.get(position).getName());
    holder.tx_active_address.setText(activity_list.get(position).getAddress());
    holder.tx_active_stat.setText(activity_list.get(position).getStatuse());
    }


    @Override
    public int getItemCount() {
        return activity_list.size();
    }

    class ActivityViewHolder extends RecyclerView.ViewHolder{

        TextView tx_active_name;
        TextView tx_active_address;
        TextView tx_active_stat;
    public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
    tx_active_name=itemView.findViewById(R.id.tx_activ_name);
    tx_active_address=itemView.findViewById(R.id.tx_acitve_address);
    tx_active_stat=itemView.findViewById(R.id.tx_active_status);

        }
    }
}
