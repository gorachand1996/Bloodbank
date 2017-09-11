package com.apex.bloodstory;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Receiver extends Activity {
    EditText e;
    EditText e1;
    Button b;
    ListView li;
    DatabaseHelper helper=new DatabaseHelper(this);

    String names[]=new String[100];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        e=(EditText)findViewById(R.id.editText13);
        e1=(EditText)findViewById(R.id.editText20);

        ListView li=(ListView)findViewById(R.id.listView);
        b=(Button)findViewById(R.id.button12);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bloodgrp=e.getText().toString().toLowerCase();
                String city=e1.getText().toString().toLowerCase();
                ListView li=(ListView)findViewById(R.id.listView);


               names= helper.retrieve(bloodgrp,city);

            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Receiver.this,android.R.layout.simple_list_item_1,names);
                li.setAdapter(arrayAdapter);
                li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String value=(String)adapterView.getItemAtPosition(i);
                        Intent k=new Intent(Receiver.this,receiverdet.class);
                        k.putExtra("value",value);
                        startActivity(k);
                    }
                });

            }
        });
    }

}
