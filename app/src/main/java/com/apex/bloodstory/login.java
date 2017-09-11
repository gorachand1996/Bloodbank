package com.apex.bloodstory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {

    DatabaseHelper helper=new DatabaseHelper(this);
    EditText e;
    TextView t;
     EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       e =(EditText)findViewById(R.id.editText);
       e1 =(EditText)findViewById(R.id.editText2);
        Button b=(Button)findViewById(R.id.button4);
        t=(TextView)findViewById(R.id.textView2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname=e.getText().toString();
                String pass=e1.getText().toString();
                  int x= helper.search(uname,pass);
                if(x==1){

                    Intent o=new Intent(login.this,bloodmenu.class);
                    startActivity(o);

                }else{
                    Toast.makeText(getApplicationContext(),"Wrong Credentials",Toast.LENGTH_LONG).show();
                }

            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(login.this,Registration.class);
                startActivity(i);
            }
        });
    }
}
