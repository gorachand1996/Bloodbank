package com.apex.bloodstory;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class receiverdet extends AppCompatActivity {
    DatabaseHelper helper=new DatabaseHelper(this);
    TextView t1,t2,t3,t4,t5;
    Button b,b1;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiverdet);
        t1=(TextView)findViewById(R.id.textView9);
        t2=(TextView)findViewById(R.id.textView8);
         t3=(TextView)findViewById(R.id.textView10);
         t4=(TextView)findViewById(R.id.textView11);
        t5=(TextView)findViewById(R.id.textView7);
        b=(Button)findViewById(R.id.button16);
        b1=(Button)findViewById(R.id.button17);
        Intent i=getIntent();
        Bundle bd = i.getExtras();
        if(bd != null)
        {
            String getName = (String) bd.get("value");
            t1.setText(getName);
            String Details[]=helper.searchdonor(getName);
            String address=Details[1];
            phone=Details[2];
            String bloodgr=Details[3];
            t3.setText(address);
            t2.setText(phone);
            t4.setText(bloodgr);

        }b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone+""));
                startActivity(intent);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"+phone+""));
                sendIntent.putExtra("sms_body","Its an Emergency ,Sir your blood is needed" );
                startActivity(sendIntent);

            }
        });


    }
}
