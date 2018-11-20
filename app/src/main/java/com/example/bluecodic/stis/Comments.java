package com.example.bluecodic.stis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.bluecodic.stis.commentbox.my_comment;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.UnsupportedEncodingException;

public class Comments extends AppCompatActivity {

    public Button send_commnt;
   public EditText txt_coomt;
    public HashMap hashMap;

    List<my_comment> searchlist;
    ListView listViewsearch;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        Intent intent=getIntent();

        String postid=intent.getStringExtra("postid");

        listViewsearch = (ListView) findViewById(R.id.commentlistview);
        searchlist = new ArrayList<>();

        send_commnt = (Button) findViewById(R.id.btn_commnt);
        txt_coomt = (EditText) findViewById(R.id.edit_comment);
        send_commnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                 String   mycomnttxt = txt_coomt.getText().toString().trim();

            
                if (TextUtils.isEmpty(mycomnttxt))
                {

                    txt_coomt.setError("please write comment");
                    txt_coomt.requestFocus();
                    return;
                }

            }
        });


        getallcomments(postid);


    }


    private void regtodb(String name,String email,String mytext) {



        //


        hashMap.put("name", name);
        hashMap.put("email", email);
        hashMap.put("mypost", mytext);
        hashMap.put("like", "0");



//        ref.child("posts").push().setValue(hashMap);
    }

    private void getallcomments(String postid) {
    }
}
