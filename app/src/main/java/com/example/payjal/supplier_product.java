package com.example.payjal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class supplier_product extends AppCompatActivity {

    EditText productname, quantity, price,pincode,phone;
    Button add, view, delete;
    DBHelpers DB;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_product);
        Intent i=getIntent();
        final String cnametext=i.getStringExtra("myExtra");

        productname = (EditText) findViewById(R.id.productname);
        quantity = (EditText) findViewById(R.id.quantity);
        price = (EditText) findViewById(R.id.price);
        pincode=(EditText)findViewById(R.id.editTextTextPersonName);
        phone=(EditText)findViewById(R.id.editTextPhone2);
        add = (Button) findViewById(R.id.btnadd);
        view = (Button) findViewById(R.id.btnview);
        delete = (Button) findViewById(R.id.btndel);


        DB = new DBHelpers(this);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.editTextPhone2,
                "(0/91)?[7-9][0-9]{9}", R.string.invalid_mobile);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = productname.getText().toString().trim();
                String quant = quantity.getText().toString().trim();
                String rupees = price.getText().toString().trim();
                String pe = pincode.getText().toString().trim();
                String phn=phone.getText().toString().trim();
                if(awesomeValidation.validate()) {

                    Boolean r = DB.addproductdata(cnametext, name, quant, rupees, pe, phn);
                    if (r == true) {
                        Toast.makeText(supplier_product.this, "New Product added", Toast.LENGTH_LONG).show();

                    } else
                        Toast.makeText(supplier_product.this, "New Product not added", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(supplier_product.this, "New Product not added", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = productname.getText().toString();

                Boolean checkdeletedata = DB.deleteproductdata(name);
                if(checkdeletedata==true)
                    Toast.makeText(supplier_product.this, "Product deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(supplier_product.this, "Product not delete", Toast.LENGTH_LONG).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0) {
                    Toast.makeText(supplier_product.this, "No Entry Exists", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Company: "+res.getString(1)+"\n");
                    buffer.append("Name: "+res.getString(2)+"\n");
                    buffer.append("Quantity: "+res.getString(3)+"\n");
                    buffer.append("Price: "+res.getString(4)+"\n");
                    buffer.append("Pincode: "+res.getString(5)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(supplier_product.this);
                builder.setCancelable(true);
                builder.setTitle("Product Enteries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

    public void onshowmain(View view) {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}