package com.example.payjal;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


public class ViewProduct extends AppCompatActivity {
    DBHelpers DB;
    EditText mPincode1;
    TextView mCompanyname;
    TextView mproductname;
    TextView mprice;
    TextView mquantity;
    TextView mp;
    Button mb;
    Button mb2,mb3;
    Cursor res,cr;
    String pc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);
                //mproductname=(TextView)findViewById((R.id.textView10));
                DB=new DBHelpers(this);
        Intent i=getIntent();
        final String custname=i.getStringExtra("custname");

        mPincode1=(EditText)findViewById(R.id.editTextTextPersonName4);
                mCompanyname=(TextView)findViewById((R.id.textView9)) ;
                mproductname=(TextView)findViewById((R.id.textView10)) ;
                mquantity=(TextView)findViewById((R.id.textView11)) ;
                mprice=(TextView)findViewById((R.id.textView12));
                mp=(TextView)findViewById(R.id.textView13) ;
                mb=(Button)findViewById((R.id.button10));
                mb2=(Button)findViewById((R.id.button12));
                mb3=(Button)findViewById((R.id.button11));

                    res = DB.getdata();

                mb.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pc=mPincode1.getText().toString().trim();
                                boolean b=DB.checkpin(pc);
                                              if(b==true) {
                                                  Toast.makeText(com.example.payjal.ViewProduct.this, "Pincode Matched:Click Next Button", Toast.LENGTH_LONG).show();
                                                  /*if(cr.getCount()==0) {
                                                      Toast.makeText(com.example.payjal.ViewProduct.this, "No Entry Exists", Toast.LENGTH_LONG).show();
                                                      return;
                                                  }
                                                  else if(cr.moveToNext()){
                                                      mCompanyname.setText(cr.getString(1));
                                                      mproductname.setText(cr.getString(2));
                                                      mquantity.setText(cr.getString(3));
                                                      mprice.setText(cr.getString(4));
                                                      mp.setText(cr.getString(5));
                                                  }*/

                                              }


                                             else
                                              {

                                                  Toast.makeText(com.example.payjal.ViewProduct.this, "Pincode Not Matched:Enter Again", Toast.LENGTH_LONG).show();
                                                  mPincode1.setText("");
                                              }

                            }
                        }
                );
                mb2.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pc=mPincode1.getText().toString().trim();
                                if(pc.equals(""))
                                {
                                    Toast.makeText(com.example.payjal.ViewProduct.this, "Please Enter the Pincode", Toast.LENGTH_LONG).show();
                                }
                                    if (res.getCount() == 0) {
                                        Toast.makeText(com.example.payjal.ViewProduct.this, "No Entry Exists", Toast.LENGTH_LONG).show();
                                        return;

                                    }

                                       else if (res.moveToNext()) {

                                        String x = res.getString(5);
                                        if (pc.equals(x)) {
                                            mCompanyname.setText(res.getString(1));
                                            mproductname.setText(res.getString(2));
                                            mquantity.setText(res.getString(3));
                                            mprice.setText(res.getString(4));
                                            mp.setText(res.getString(5));
                                        }
                                       }
                            }});


        mb3.setOnClickListener( new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String f = res.getString(6);
                                        String a=mCompanyname.getText().toString().trim();
                                        String b=mproductname.getText().toString().trim();
                                        String c=mquantity.getText().toString().trim();
                                        String d=mprice.getText().toString().trim();
                                        String e=mp.getText().toString().trim();
                                        Intent i=new Intent(getApplicationContext(), BookProduct.class);
                                        Bundle bun=new Bundle();
                                        bun.putString("mCompanyname",a);
                                        bun.putString("mproductname",b);
                                        bun.putString("mquantity",c);
                                        bun.putString("mprice",d);
                                        bun.putString("mp",e);
                                        bun.putString("mphone",f);
                                        bun.putString("custname",custname);
                                        i.putExtras(bun);
                                        startActivity(i);


                                    }
                                }
            );


            }

}

