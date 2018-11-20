package com.example.bluecodic.stis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.txusballesteros.bubbles.BubblesManager;

public class Term_conditions extends AppCompatActivity {

    private BubblesManager bubblesManager;
    private boolean isCheckedValue;

    CheckBox checkbox;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_conditions);
        checkbox = (CheckBox)findViewById(R.id.checkBox);
        button =(Button)findViewById(R.id.agree);







        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (!checkbox.isChecked())
                {
                    Toast.makeText(Term_conditions.this, "please checkboc click", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(Term_conditions.this,First.class);
                    startActivity(intent);
                }

            }
        });



    }



}
