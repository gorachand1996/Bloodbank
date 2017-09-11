package com.apex.bloodstory;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.Manifest;

import java.util.Random;

import static android.R.attr.dial;
import static android.R.attr.y;
import static com.apex.bloodstory.R.id.button;
import static com.apex.bloodstory.R.id.textView;

public class Donor extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);
        EditText e,e1,e2,e3,e4,e5,e6,ex;
    Button b,button;
    int y;
    private static final int PERMISSION_SEND_SMS = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        e=(EditText)findViewById(R.id.editText8);
        e1=(EditText)findViewById(R.id.editText9);
        e2=(EditText)findViewById(R.id.editText10);
        e3=(EditText)findViewById(R.id.editText11);
        e4=(EditText)findViewById(R.id.editText12);
        e5=(EditText)findViewById(R.id.editText15);
        e6=(EditText)findViewById(R.id.editText14);
        b=(Button)findViewById(R.id.button11);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(Donor.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(Donor.this, new String[]{Manifest.permission.SEND_SMS}, PERMISSION_SEND_SMS);

                } else {
                    Log.d("PLAYGROUND", "Permission is granted");
                }


              final String name=e1.getText().toString();
                final String age=e2.getText().toString();
                final String address=e3.getText().toString();
                final String phone=e4.getText().toString();
                final String bloodgr=e.getText().toString().toLowerCase();
                final String date =e5.getText().toString();
                final String city=e6.getText().toString().toLowerCase();
                if(Integer.parseInt(age)>=18)
                {
                    Random r = new Random();
                    int i1 = r.nextInt(80 - 65) + 65;
                    final String otp=Integer.toString(i1);







                    sendSMS(phone, otp);

                    final AlertDialog.Builder builder=new AlertDialog.Builder(Donor.this);

                    builder.setTitle("OTP");
                    builder.setMessage("Enter the OTP");
                    builder.setCancelable(false);
                    ex=new EditText(Donor.this);
                    ex.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(ex);


                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           String me=ex.getText().toString();
                            if (me.equals(otp)) {
                                Contact c = new Contact();
                                c.setname(name);
                                c.setage(age);
                                c.setaddress(address);
                                c.setphone(phone);
                                c.setbloodgr(bloodgr);
                                c.setdate(date);
                                c.setcity(city);
                                y = helper.inputblood(c);


                                Intent k = new Intent(Donor.this, Bloodbank.class);
                                startActivity(k);
                            }
                                else{
                                    dialog.cancel();

                                    Toast.makeText(getApplicationContext(),"Give The Correct OTP",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();



                }
                else{
                    Toast.makeText(getApplicationContext(),"You should be 18 years old",Toast.LENGTH_LONG).show();
                }

            }
        });


    }
    private void sendSMS(String phoneNumber, String message)
    {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 123) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("PLAYGROUND", "Permission has been granted");

            } else {
                Log.d("PLAYGROUND", "Permission has been denied or request cancelled");


            }
        }
    }
}


