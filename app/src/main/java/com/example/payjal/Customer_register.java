package com.example.payjal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;


public class Customer_register extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    EditText mPhone;
    Button mButtonRegister;
    DatabaseHelper mydb;

    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_register);
        mydb=new DatabaseHelper(this);

        mTextUsername=(EditText)findViewById((R.id.editunamelogin));
        mTextPassword=(EditText)findViewById((R.id.editTextNumber3));
        mTextCnfPassword=(EditText)findViewById((R.id.editTextNumberSigned));
        mPhone=(EditText)findViewById((R.id.editTextNumber4));
        mButtonRegister = (Button)findViewById((R.id.blogin));
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.editunamelogin,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        awesomeValidation.addValidation(this, R.id.editTextNumber4,
                "(0/91)?[7-9][0-9]{9}", R.string.invalid_mobile);

        awesomeValidation.addValidation(this,R.id.editTextNumber3,
                ".{6,}",R.string.invalid_password);

        awesomeValidation.addValidation(this, R.id.editTextNumberSigned,
                R.id.editTextNumber3, R.string.inavalid_confirm_password);

        mButtonRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String user=mTextUsername.getText().toString().trim();
                        String pwd=mTextPassword.getText().toString().trim();
                        String cnf_pwd=mTextCnfPassword.getText().toString().trim();
                        String ph=mPhone.getText().toString().trim();
                        if(awesomeValidation.validate()){
                            long val=mydb.addUser(user,pwd,ph);
                            Toast.makeText(getApplicationContext(), "YOU HAVE REGISTERED", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(Customer_register.this,Customer_Login.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();
                        }

                        /*if(pwd.equals(cnf_pwd))
                        {  long val=mydb.addUser(user,pwd,ph);
                           if (val>0)
                           {
                               Toast.makeText(Customer_register.this,"YOU HAVE REGISTERED",Toast.LENGTH_SHORT).show();
                               Intent i=new Intent(Customer_register.this,Customer_Login.class);
                               startActivity(i);
                           }
                           else
                           {
                               Toast.makeText(Customer_register.this,"REGISTRATION ERROR",Toast.LENGTH_SHORT).show();

                           }
                        }
                        else
                        {
                            Toast.makeText(Customer_register.this,"Password is not matching",Toast.LENGTH_SHORT).show();

                        }*/

                    }
                }
        );
    }




}
