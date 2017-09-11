package com.apex.bloodstory;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class offline extends AppCompatActivity {
EditText e1,e2,e3;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        e1=(EditText)findViewById(R.id.editText17);
        e2=(EditText)findViewById(R.id.editText18);
        e3=(EditText)findViewById(R.id.editText19);
        b=(Button)findViewById(R.id.button19);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e1.getText().toString();
                String address=e2.getText().toString();
                String bloodgp=e3.getText().toString();

                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:1800235892"));
                sendIntent.putExtra("sms_body","Sir It is an urgency.My personal Details are given below.Help us..!!\nNAME :"+name+"\nADDRESS :"+address+"\nBLOOD GROUP :"+bloodgp+"" );
                startActivity(sendIntent);
            }
        });
    }
}
