package com.example.payjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void Button_supplier(View view) {
        Intent i=new Intent(this,Supplierpage.class);
        startActivity(i);
    }

    public void Button_Customer(View view) {
        Intent i=new Intent(this,Customerpage.class);
        startActivity(i);
    }
}