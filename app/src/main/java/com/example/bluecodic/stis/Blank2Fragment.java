package com.example.bluecodic.stis;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Blank2Fragment extends Fragment {

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);


        if (isVisibleToUser) {
            //getdatafromdb();
            //Toast.makeText(getContext(), "ok", Toast.LENGTH_SHORT).show();
        }
    }

    View v;
    private RecyclerView myhomerecycler;
    private List<Home> ListHomePage;

    private ProgressDialog dialog;

    public Blank2Fragment() {
        // Required empty public constructor
    }
    RecyclerHomeAdapter recycleradaptere;

    Button postbtn;
    EditText mypost;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    HashMap hashMap;
    DatabaseReference myref;

    mysharedpref prefobj;
    Map<String, String> userinfo;

    @Override

    public View onCreateView(LayoutInflater inflater,  ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_blank2,container,false);
        myhomerecycler = (RecyclerView)v.findViewById(R.id.home_recyclerview);


        //
        prefobj=new mysharedpref(getContext());
        //

        ListHomePage = new ArrayList<>();
        try {
            getdatafromdb();
        } catch (Exception e) {
            e.printStackTrace();
        }


        postbtn=(Button) v.findViewById(R.id.mypostbtn);
        mypost=(EditText) v.findViewById(R.id.mypost);

        hashMap=new HashMap();

        dialog = new ProgressDialog(getContext());

        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myposttext=mypost.getText().toString().trim();

                if(TextUtils.isEmpty(myposttext))
                {
                    mypost.setError("please write Status");
                    mypost.requestFocus();
                    return;
                }

                String name="nomi";
                String email="aqib@gmail.com";

             userinfo=prefobj.getshared();
                if(userinfo!=null) {
                    name=userinfo.get("name").toString();
                    email=userinfo.get("email").toString();
                }








                regtodb(name,email,myposttext);


               // addItem(myposttext);
                Toast.makeText(getContext(), "successfully post", Toast.LENGTH_SHORT).show();
                hidemykeyboard();
                mypost.setText("");
            }
        });


        return v;


    }

    private void regtodb(String name,String email,String mytext) {

        //
        String strBase64Email = new String();
        byte[] data = new byte[0];

        try
        {
            data = email.getBytes("UTF-8");
            strBase64Email = android.util.Base64.encodeToString(data, android.util.Base64.NO_WRAP);
        }

        catch (UnsupportedEncodingException e)
        {

        }

        //


        hashMap.put("name", name);
        hashMap.put("email", email);
        hashMap.put("mypost", mytext);
        hashMap.put("like", "0");



        ref.child("posts").push().setValue(hashMap);
    }

    private void addItem(String posttext) {
        ListHomePage.add(0,new Home("Nabeel",posttext,R.drawable.nabeel,"","","",""));
        recycleradaptere.notifyDataSetChanged();
    }
    // hide keybord when text done
    private void hidemykeyboard()
    {
        InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        /*for (int i = 1; i <= 5; i++) {
            ListHomePage.add(new Home("Nabeel", "this is status field", R.drawable.nabeel, "", "", ""));
        }*/

       // getdatafromdb();
        /*ListHomePage.add(new Home("Nabeel","this is status field",R.drawable.danish,"","",""));
        ListHomePage.add(new Home("Nabeel","this is status field",R.drawable.iqra,"","",""));
        ListHomePage.add(new Home("Nabeel","this is status field",R.drawable.yousaf,"","",""));*/
    }

    private void getdatafromdb() {

        Map<String, String> myuserinfo=prefobj.getshared();



String email=myuserinfo.get("email").toString();

        //
        String strBase64Email = new String();
        byte[] data = new byte[0];

        try
        {
            data = email.getBytes("UTF-8");
            strBase64Email = android.util.Base64.encodeToString(data, android.util.Base64.NO_WRAP);
        }

        catch (UnsupportedEncodingException e)
        {

        }



        myref=FirebaseDatabase.getInstance().getReference().child("posts");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dialog.setMessage("Doing something, please wait.");
                dialog.show();

                ListHomePage.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {



                    String postid=(String) snapshot.getKey();
                    String name = (String) snapshot.child("name").getValue();
                    String mytext = (String) snapshot.child("mypost").getValue();
                    String like = (String) snapshot.child("like").getValue();
                    ListHomePage.add(0,new Home(name, mytext, R.drawable.nabeel, "", "", like,postid));


                }

                recycleradaptere = new RecyclerHomeAdapter(getContext(),ListHomePage);
                myhomerecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                myhomerecycler.setAdapter(recycleradaptere);

                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }
}
