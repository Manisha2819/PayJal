package com.example.payjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Customerpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerpage);
    }

    public void OnCustomerRegister(View view) {
        Intent i=new Intent(this,Customer_register.class);
        startActivity(i);
    }

    public void OnCustomerLogin(View view) {
        Intent i=new Intent(this,Customer_Login.class);
        startActivity(i);
    }
}