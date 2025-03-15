package com.example.payjal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookProduct extends AppCompatActivity {
     EditText mquant;
     EditText maddress;
     Button mbutton;
     DBHelperBookProduct DB;
    Cursor res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_product);
        mquant=(EditText)findViewById(R.id.editTextTextPersonName2);
        maddress=(EditText)findViewById(R.id.editTextTextPersonName3);
        mbutton=(Button)findViewById(R.id.button);
        DB=new DBHelperBookProduct(this);
        Bundle bd=getIntent().getExtras();
        final String companyname=bd.getString("mCompanyname");
        final String productname=bd.getString("mproductname");
        final String quantity=bd.getString("mquantity");             //this is the total quantity
        final String price=bd.getString("mprice");
        final String pcode=bd.getString("mp");
        final  String phoneno=bd.getString("mphone");
        final  String name=bd.getString("custname");

        res = DB.getdata();

        mbutton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                       String quant=mquant.getText().toString().trim();           //Quant is the quantity enter by customer to order
                       final String ad=maddress.getText().toString().trim();
                      /* if(mquant.equals("") || maddress.equals(""))
                       {
                           Toast.makeText(BookProduct.this, "Please Enter All Details!", Toast.LENGTH_LONG).show();
                       }*/
                        if((Integer.parseInt(quant)) > (Integer.parseInt(quantity)))
                        {
                            Toast.makeText(BookProduct.this, "Cannot order more than total quantity:"+quantity, Toast.LENGTH_LONG).show();
                        }
                       else {



                               int amt = (Integer.parseInt(quant)) * (Integer.parseInt(price));
                               final String amount = Integer.toString(amt);
                               String str = "Total Amount to be paid to " + companyname + " is  " + amount;
                               Boolean r = DB.addbooking(companyname, productname, quantity, price, pcode, quant, ad);
                               if (r == true) {
                                   //Toast.makeText(BookProduct.this, "New Product added", Toast.LENGTH_LONG).show();
                                   AlertDialog.Builder builder = new AlertDialog.Builder(BookProduct.this);
                                   builder.setCancelable(true);
                                   builder.setTitle("Wants to Order?");
                                   builder.setMessage(str);
                                   builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialog, int which) {
                                           Toast.makeText(BookProduct.this, "Choose Mode of Pyament", Toast.LENGTH_LONG).show();
                                           //bookproduct();
                                       Intent intent = new Intent(getApplicationContext(),ChoosePymentMode.class);
                                       intent.putExtra("myphone",phoneno);
                                       intent.putExtra("custname",name);
                                       intent.putExtra("myadd",ad);
                                       intent.putExtra("payment",amount);
                                       startActivity(intent);
                                       }
                                   });
                                   builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                       @Override
                                       public void onClick(DialogInterface dialog, int which) {
                                           Toast.makeText(BookProduct.this, "NO CLICKED", Toast.LENGTH_LONG).show();
                                       }
                                   });
                                   builder.show();
                                   //bookproduct();
                               } else
                                   Toast.makeText(BookProduct.this, "New Product not added", Toast.LENGTH_LONG).show();

                       }
                    }
                }
        );


    }
    /*private void bookproduct(){
        Intent intent = new Intent(this, ChoosePymentMode.class);
        //intent.putExtra("myphone",phoneno);
        startActivity(intent);
    }*/
}