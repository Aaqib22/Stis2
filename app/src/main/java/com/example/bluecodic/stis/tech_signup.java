package com.example.bluecodic.stis;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class tech_signup extends AppCompatActivity {

    TextView siggin_bck_tech;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("teacher");
    HashMap hashMap;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText t_fname,t_lname,t_email,t_pass;
    TextView sigin_bck_std;
    Button b_signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_signup);


        mAuth = FirebaseAuth.getInstance();

        t_fname = (EditText) findViewById(R.id.f_name_tech_up);
        t_lname = (EditText) findViewById(R.id.l_name_tech_up);
        t_email = (EditText) findViewById(R.id.email_tech_up);
        t_pass = (EditText) findViewById(R.id.pass_tech_up);
        b_signup = (Button) findViewById(R.id.tech_signup);
        hashMap=new HashMap();


        siggin_bck_tech = (TextView)findViewById(R.id.sigin_bck_tech);

        siggin_bck_tech.setPaintFlags(siggin_bck_tech.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        siggin_bck_tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(tech_signup.this , teacher_login.class);
                startActivity(i);
            }
        });


        b_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)

            {

                TechRegister();

            }


        });

    }
    private void TechRegister()
    {


        String fname,lname,email,pass;
        fname = t_fname.getText().toString().trim();
        lname = t_lname.getText().toString().trim();
        email = t_email.getText().toString().trim();
        pass = t_pass.getText().toString().trim();

        if (fname.isEmpty())
        {
            t_fname.setError("Enter first Name");
            t_fname.requestFocus();
        }

        if (lname.isEmpty())
        {
            t_lname.setError("Enter last Name");
            t_lname.requestFocus();
        }

        if (email.isEmpty())
        {
            t_email.setError("Enter Email Address");
            t_email.requestFocus();
        }

        if (pass.isEmpty())
        {
            t_pass.setError("Enter password");
            t_pass.requestFocus();
        }



        if (pass.length()<6||pass.length()>8)
        {
            t_pass.setError("Password length must be 6 to 8");
            t_pass.requestFocus();
            return;

        }

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(tech_signup.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(fname))
        {
            Toast.makeText(tech_signup.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(lname))
        {
            Toast.makeText(tech_signup.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(tech_signup.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }



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


        Constant.kPathKey = strBase64Email;
        hashMap.put("name", fname);
        hashMap.put("lastname", lname);
        hashMap.put("email", email);
        hashMap.put("pass",pass);


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            t_email.setError("Please Enter correct Email");
            t_email.requestFocus();
        }

        else{


            ref.child(Constant.kUsers).child(Constant.kPathKey).child(Constant.kUserInfo).setValue(hashMap);
            Toast.makeText(tech_signup.this, "Register Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(tech_signup.this,teacher_login.class));

        }


    }
}
