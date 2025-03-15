package com.example.payjal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Supplier_Login extends AppCompatActivity {
    EditText CompanyName,Password;
    Button btnlogin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier__login);
        CompanyName = (EditText) findViewById(R.id.CompanyName1);
        Password = (EditText) findViewById(R.id.Password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);



        btnlogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String user = CompanyName.getText().toString();
                String pass = Password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Supplier_Login.this, "Please enter all detials", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkCompanyNamePassword = DB.checkCompanyNamePassword( user, pass);
                    if(checkCompanyNamePassword==true){
                        Toast.makeText(Supplier_Login.this, "Sign in Successfull!", Toast.LENGTH_SHORT).show();
                        openproduct();
                    }else{
                        Toast.makeText(Supplier_Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
    }

    private void openproduct() {
        String cname=CompanyName.getText().toString();
       // Intent i=getIntent();
       // final String pintext=i.getStringExtra("myExtra2");
        Intent intent = new Intent(this, supplier_product.class);
       /* Bundle bundle=new Bundle();
        bundle.putString("myExtra",cname);
        bundle.putString("mypincode",pin);*/
        intent.putExtra("myExtra",cname);
        startActivity(intent);
    }

}