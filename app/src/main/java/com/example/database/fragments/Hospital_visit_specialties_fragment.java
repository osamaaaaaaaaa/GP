package com.example.database.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.database.R;
import com.example.database.ui.Home_page;
import com.example.database.ui.Hospital_visit_list_view;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Hospital_visit_specialties_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Hospital_visit_specialties_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button bt_brain;

    public Hospital_visit_specialties_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Hospital_visit_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Hospital_visit_specialties_fragment newInstance(String param1, String param2) {
        Hospital_visit_specialties_fragment fragment = new Hospital_visit_specialties_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hospital_visit_fragment, container, false);

        OnBackButtonPressed(view);
        bt_brain=view.findViewById(R.id.bt_brain);
        Home_page home_page=new Home_page();

        bt_brain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), Hospital_visit_list_view.class));
            }
        });


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

}