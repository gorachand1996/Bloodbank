package com.apex.bloodstory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bloodbank extends AppCompatActivity {
    Button b,b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodbank);

        b=(Button)findViewById(R.id.button8);
        b1=(Button)findViewById(R.id.button9);
        b2=(Button)findViewById(R.id.button10);
        b3=(Button)findViewById(R.id.button6);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(Bloodbank.this,Donor.class);
                startActivity(o);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent p=new Intent(Bloodbank.this,Receiver.class);
                startActivity(p);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(Bloodbank.this,donate.class);
                startActivity(o);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent o=new Intent(Bloodbank.this,facts.class);
                startActivity(o);
            }
        });



    }
}
