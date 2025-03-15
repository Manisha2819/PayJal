package com.example.payjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Customer_Login extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    DatabaseHelper mydb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__login);
        mydb1=new DatabaseHelper(this);
        mTextUsername=(EditText)findViewById((R.id.editunamelogin));
        mTextPassword=(EditText)findViewById((R.id.editTextloginpass));
        mButtonLogin=(Button)findViewById((R.id.blogin));

        mButtonLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user=mTextUsername.getText().toString().trim();
                        String pwd=mTextPassword.getText().toString().trim();
                        Boolean res=mydb1.checkUser(user,pwd);
                        if(res == true)
                        {


                            Toast.makeText(Customer_Login.this,"Successfully Logged in",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(Customer_Login.this,ViewProduct.class);
                            i.putExtra("custname",user);
                            startActivity(i);
                        }
                        else
                        {        Toast.makeText(Customer_Login.this,"LOGIN ERROR",Toast.LENGTH_SHORT).show();}
                    }
                }
        );
    }

    public void viewProd(View view) {

    }
}