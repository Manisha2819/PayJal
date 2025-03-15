package com.example.payjal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.Menu;
import android.widget.Toast;


public class ChoosePymentMode extends AppCompatActivity {
    Button btn;
    RadioGroup rg;
    RadioButton selectedrb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_pyment_mode);
        btn = (Button) findViewById(R.id.button8);
        rg= (RadioGroup)findViewById(R.id.radiogp);
        Intent i=getIntent();
        final String myphone=i.getStringExtra("myphone");
        final String myname=i.getStringExtra("custname");
        final String myaddr=i.getStringExtra("myadd");
        final String amount=i.getStringExtra("payment");


        btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               selectedrb=(RadioButton)findViewById(rg.getCheckedRadioButtonId());
               String str=selectedrb.getText().toString();
               if(str.equals("Cash On Delivery"))
               {
                  // Intent intent=new Intent(getApplicationContext(),ChoosePymentMode.class);
                  // PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                   //Get the SmsManager instance and call the sendTextMessage method to send message
                   try {
                       SmsManager sms = SmsManager.getDefault();
                       sms.sendTextMessage(myphone, null, "You Have A New Order from  "+myname+"\nAddress is  "+myaddr+" \nTotal Amount is  "+amount, null, null);

                       Toast.makeText(getApplicationContext(), "ORDER PLACED SUCCESSFULLY!",
                               Toast.LENGTH_LONG).show();
                   }catch(Exception e)
                   {  Toast.makeText(getApplicationContext(), "SMS faild, please try again later.", Toast.LENGTH_SHORT).show(); }

               }
               else {
                       /*Intent i=new Intent(getApplicationContext(),payment.class);
                       startActivity(i);*/
                   Toast.makeText(getApplicationContext(), "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
               }
           }
       });

    }

}