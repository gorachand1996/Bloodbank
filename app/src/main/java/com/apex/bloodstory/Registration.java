package com.apex.bloodstory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Registration extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);
    EditText e;
    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;
    Button b;
    int z;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
         e=(EditText)findViewById(R.id.editText3);
         e1=(EditText)findViewById(R.id.editText4);
         e2=(EditText)findViewById(R.id.editText5);
         e3=(EditText)findViewById(R.id.editText6);
         e4=(EditText)findViewById(R.id.editText7);
        b=(Button)findViewById(R.id.button5);



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e.getText().toString();
                String uname=e1.getText().toString();
                String password1=e2.getText().toString();
                String password2=e3.getText().toString();
                String email=e4.getText().toString();
                if(password1.length()>=6&&password1.length()<=10)
                {
                    if(!password1.equals(password2))
                    {
                    Toast.makeText(Registration.this,"Password dont match",Toast.LENGTH_SHORT).show();
                }else if(name.isEmpty()||uname.isEmpty()||email.isEmpty()||password1.isEmpty()||password2.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please fill up all Credentials",Toast.LENGTH_LONG).show();
                }
                else{
                        Contact c=new Contact();
                        c.setname(name);
                        c.setuname(uname);
                        c.setpassword(password1);
                        c.setemail(email);
                        helper.input(c);
                    Intent k=new Intent(Registration.this,MainActivity.class);
                    startActivity(k);
                }}
                else{
                    Toast.makeText(getApplicationContext(),"Password must be 6 to 10 characters with atleast one letter or digit",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
