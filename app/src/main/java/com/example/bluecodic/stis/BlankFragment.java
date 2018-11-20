package com.example.bluecodic.stis;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    View v;
    private RecyclerView myrecyclerview;
    private List<Contact> lstContect;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_blank, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.contect_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),lstContect);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);


        return v;





    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lstContect =new ArrayList<>();
        lstContect.add(new Contact("Aliza zulifqar","(+92)300 1234567",R.drawable.aliza));
        lstContect.add(new Contact("Nabeel Hussain","(+92)300 1234567",R.drawable.nabeel));
        lstContect.add(new Contact("Yousaf Khan","(+92)300 1234567",R.drawable.yousaf));
        lstContect.add(new Contact("Danish Hussain","(+92)300 1234567",R.drawable.danish));
        lstContect.add(new Contact("Amber Lateef","(+92)300 1234567",R.drawable.amber));
        lstContect.add(new Contact("Mariam Hamza","(+92)300 1234567",R.drawable.mariam));
        lstContect.add(new Contact("Mujtaba Hassan","(+92)300 1234567",R.drawable.yousaf));
        lstContect.add(new Contact("Akifa Javeed","(+92)300 1234567",R.drawable.akifa));
        lstContect.add(new Contact("Saqib Riaz","(+92)300 1234567",R.drawable.danish));
        lstContect.add(new Contact("Iqra Ikhlaq","(+92)300 1234567",R.drawable.iqra));
        lstContect.add(new Contact("Bilal Qudoos","(+92)300 1234567",R.drawable.nabeel));
        lstContect.add(new Contact("Aliza zulifqar","(+92)300 1234567",R.drawable.aliza));
        lstContect.add(new Contact("Nabeel Hussain","(+92)300 1234567",R.drawable.nabeel));
        lstContect.add(new Contact("Yousaf Khan","(+92)300 1234567",R.drawable.yousaf));
        lstContect.add(new Contact("Danish Hussain","(+92)300 1234567",R.drawable.danish));
        lstContect.add(new Contact("Amber Lateef","(+92)300 1234567",R.drawable.amber));
        lstContect.add(new Contact("Mariam Hamza","(+92)300 1234567",R.drawable.mariam));
        lstContect.add(new Contact("Mujtaba Hassan","(+92)300 1234567",R.drawable.yousaf));
        lstContect.add(new Contact("Akifa Javeed","(+92)300 1234567",R.drawable.akifa));
        lstContect.add(new Contact("Saqib Riaz","(+92)300 1234567",R.drawable.danish));
        lstContect.add(new Contact("Iqra Ikhlaq","(+92)300 1234567",R.drawable.iqra));
        lstContect.add(new Contact("Bilal Qudoos","(+92)300 1234567",R.drawable.nabeel));
    }
}