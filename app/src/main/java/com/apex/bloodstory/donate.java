package com.apex.bloodstory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class donate extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);
    Button b;
    EditText e,e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        b=(Button)findViewById(R.id.button7);
        e=(EditText)findViewById(R.id.editText16);
        e1=(EditText)findViewById(R.id.editText15);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e.getText().toString();
                String date=e1.getText().toString();
                helper.changeflag(name,date);
                Intent i=new Intent(donate.this,Bloodbank.class);
                startActivity(i);

            }
        });
    }
}
