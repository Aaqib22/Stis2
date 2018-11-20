package com.example.bluecodic.stis;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

public class First extends AppCompatActivity {

    Button btn_tech_next;
    Button btn_std_next;
    TextView term_cond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn_tech_next = (Button) findViewById(R.id.btn_tech_next);

        btn_tech_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(First.this , teacher_login.class);
                startActivity(i);
            }
        });

        btn_std_next = (Button) findViewById(R.id.btn_std_next);

        btn_std_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(First.this , Std_login.class);
                startActivity(i);
            }
        });

        term_cond = (TextView) findViewById(R.id.term_cond);

        term_cond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(First.this , Term_conditions.class);
                startActivity(i);
            }
        });

        term_cond.setPaintFlags(term_cond.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);





    }


}
