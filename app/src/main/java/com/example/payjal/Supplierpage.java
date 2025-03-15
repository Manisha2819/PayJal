package com.example.payjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Supplierpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplierpage);
    }

    public void onSupplierRegister(View view) {
        Intent i=new Intent(this,Supplier_Register.class);
        startActivity(i);
    }

    public void onSupplierLogin(View view) {
        Intent i=new Intent(this,Supplier_Login.class);
        startActivity(i);
    }
}