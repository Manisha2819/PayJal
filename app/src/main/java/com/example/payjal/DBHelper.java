package com.example.payjal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME= "Login.db";

    public DBHelper(Context context) {
        super(context, "Login.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(CompanyName TEXT primary key, Password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String CompanyName, String Password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("CompanyName", CompanyName);
        contentValues.put("Password", Password);
        long result = MyDB.insert("users",null, contentValues);
        if((result== -1)) return false;
        else
            return true;
    }

    public Boolean checkCompanyName(String CompanyName){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where CompanyName = ?", new String[]{CompanyName});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkCompanyNamePassword(String CompanyName, String Password){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery(" Select * from users where CompanyName = ? and Password = ? ", new String[] {CompanyName,Password} );
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

}
