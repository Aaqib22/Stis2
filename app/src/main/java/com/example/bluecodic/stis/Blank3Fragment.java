package com.example.bluecodic.stis;


import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.bluecodic.stis.aqibchat.ui.activities.UserListingActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class Blank3Fragment extends Fragment {


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        if (isVisibleToUser) {
            startActivity(new Intent(getContext(), UserListingActivity.class));

        }
    }





   /* FloatingActionButton f1;
    String tost ="This will replace for Message";



    public Blank3Fragment() {



        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank3, container, false);
           FloatingActionButton f1= (FloatingActionButton)  v.findViewById(R.id.fab);
            f1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),tost,Toast.LENGTH_LONG).show();

                }
            });

            return v;

    }*/



}
