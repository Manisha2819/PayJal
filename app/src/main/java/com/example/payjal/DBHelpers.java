package com.example.payjal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelpers extends SQLiteOpenHelper {
    public DBHelpers(Context context) {
        super(context, "Productdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Productdetails(ID INTEGER PRIMARY KEY AUTOINCREMENT ,companyname TEXT,productname TEXT , quantity TEXT, price TEXT , pincode TEXT, phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Productdetails");
        onCreate(DB);

    }

    public Boolean addproductdata (String companyname,String productname, String quantity, String price,String pincode,String phn)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("companyname",companyname);
        contentValues.put("productname",productname);
        contentValues.put("quantity", quantity);
        contentValues.put("price", price);
        contentValues.put("pincode", pincode);
        contentValues.put("phone", phn);


        long result = DB.insert("Productdetails", null, contentValues);
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }
    }


    public Boolean deleteproductdata(String productname) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Productdetails where productname = ?", new String[]{productname});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Productdetails", "productname=?", new String[]{productname});
            if (result == -1)
                return false;
            else
                return true;
        } else {
            return false;
        }
    }

    public Boolean checkproductquantrupees(String productname, String quantity, String price){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(" Select * from users where productname = ? and quantity = ? and price = ?", new String[] {productname,quantity,price} );
        if (cursor!=null)
        {
            if(cursor.getCount()>0)
            {
                return  true;
            }
            else {
                return false;
            }

        }
        else
        {
            return false;
        }

    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Productdetails", null);
        return cursor;
    }
    public Cursor checkpin2(String pincode)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(" Select * from Productdetails where pincode=? ",new String[] {pincode} );
        return cursor;
    }
    public boolean checkpin(String pincode)
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(" Select * from Productdetails where pincode=? ",new String[] {pincode} );
        if (cursor!=null)
        {
            if(cursor.getCount()>0)
            {

                return  true;
            }
            else
                {
                return false;
            }

        }

        else{
            return false;}



    }
}
