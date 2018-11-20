package com.example.bluecodic.stis;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Std_login extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    HashMap hashMap;

    String strEmail,strPass;

    mysharedpref prefobj;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("student");

    TextView std_sup;
    EditText t_login,t_pass;
    Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_login);

        //
        prefobj=new mysharedpref(Std_login.this);

        Map<String, String> userinfo=prefobj.getshared();
        if(userinfo!=null)
        {

            if(userinfo.get("type").toString().equals("student")) {
                finish();
                startActivity(new Intent(getApplicationContext(), navigation_bar.class));
            }
        }
        //


        std_sup =(TextView) findViewById(R.id.std_sup);
        t_login = (EditText) findViewById(R.id.std_login);
        t_pass = (EditText) findViewById(R.id.std_login_pass);
        mAuth = FirebaseAuth.getInstance();
        hashMap=new HashMap();
        std_sup.setPaintFlags(std_sup.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);

        std_sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Std_login.this, std_signup.class);
                startActivity(i);
            }
        });

        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Userlogin();

            }


        });
    }
    private void Userlogin()

    {

        final String email,password;
        strEmail = t_login.getText().toString().trim();
        strPass = t_pass.getText().toString().trim();


        if (strEmail.isEmpty())
        {
            t_login.setError("Enter email");
            t_login.requestFocus();
        }

        if (strPass.isEmpty())
        {
            t_pass.setError("Enter pass");
            t_pass.requestFocus();
        }


        String strBase64Email = new String();
        byte[] data = new byte[0];

        try
        {
            data = strEmail.getBytes("UTF-8");
            strBase64Email = android.util.Base64.encodeToString(data, android.util.Base64.NO_WRAP);
        }

        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            Log.e("",e.getMessage());


        }


        Constant.kPathKey = strBase64Email;
        Log.d("message",Constant.kPathKey);

        ref.child(Constant.kUsers).child(Constant.kPathKey).child(Constant.kUserInfo).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap hashMap = (HashMap) dataSnapshot.getValue();

                if(TextUtils.isEmpty(strEmail))
                {
                    Toast.makeText(Std_login.this, "please enter correct email", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    if ( hashMap.get("pass").equals(strPass))
                    {
                        Toast.makeText(getApplicationContext(), "LogIn", Toast.LENGTH_LONG).show();

                        //
                        prefobj.setshared(strEmail,hashMap.get("name").toString(),"student");
                        //
                        finish();
                        Intent intent = new Intent(Std_login.this , navigation_bar.class);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "PassWord is Incorrect", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    t_login.setError("Please Enter Valid Email");
                    t_login.requestFocus();
                }
//
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
