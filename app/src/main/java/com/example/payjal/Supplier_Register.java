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

public class Supplier_Register extends AppCompatActivity {
    EditText CompanyName,Password, CnfrmPswd;
    Button signup, signin;
    DBHelper DB;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier__register);

        CompanyName = (EditText) findViewById(R.id.CompanyName);
        Password = (EditText) findViewById(R.id.Password);
        CnfrmPswd = (EditText) findViewById(R.id.CnfrmPswd);
        signin = (Button) findViewById(R.id.btnsignin);
        signup = (Button) findViewById(R.id.btnsignup);
        DB = new DBHelper(this);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.CompanyName,
                RegexTemplate.NOT_EMPTY, R.string.invalid_name);

              awesomeValidation.addValidation(this,R.id.Password,
                ".{6,}",R.string.invalid_password);

        awesomeValidation.addValidation(this, R.id.CnfrmPswd,
                R.id.Password, R.string.inavalid_confirm_password);


        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                String user = CompanyName.getText().toString();
                String pass = Password.getText().toString();
                String cfmpas = CnfrmPswd.getText().toString();


                if(awesomeValidation.validate()){
                    Boolean insert =DB.insertData(user, pass);
                    if(insert==true) {
                        Toast.makeText(getApplicationContext(), "YOU HAVE REGISTERED", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Supplier_Register.this, Supplier_Login.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();
                }



                /*if(user.equals("")||pass.equals("")||cfmpas.equals(""))
                    Toast.makeText(Supplier_Register.this, "Please enter all detials", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(cfmpas)){
                        Boolean checkCompanyName = DB.checkCompanyName(user);
                        if (checkCompanyName==false){
                            Boolean insert =DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(Supplier_Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Supplierpage.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Supplier_Register.this, "Registration failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(Supplier_Register.this, "User already exist! Please Sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Supplier_Register.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                    }
                }*/

            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                //String pin1= Pincode.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Supplier_Login.class);
                //intent.putExtra("myExtra2",pin1);
                startActivity(intent);

            }
        });
    }
}