package com.example.payjal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperBookProduct extends SQLiteOpenHelper {

    public DBHelperBookProduct(@Nullable Context context) {
        super(context,"BookProducts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Booking(ID INTEGER PRIMARY KEY AUTOINCREMENT ,companyname TEXT,productname TEXT,totalquant TEXT,price TEXT,pinco TEXT,quantity TEXT,address TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Booking");
        onCreate(db);

    }
    public Boolean addbooking(String companyname,String productname,String totalquant,String price,String pinco,String quantity,String address)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("companyname", companyname);
        contentValues.put("productname", productname);
        contentValues.put("totalquant", totalquant);
        contentValues.put("price", price);
        contentValues.put("pinco", pinco);
        contentValues.put("quantity", quantity);
        contentValues.put("address", address);
        long result = DB.insert("Booking", null, contentValues);
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Booking", null);
        return cursor;
    }


}
