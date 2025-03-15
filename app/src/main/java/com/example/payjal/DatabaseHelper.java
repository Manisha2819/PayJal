package com.example.payjal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Customer.db";
    public static final String TABLE_NAME="registercust";
    public static final String COL1="ID";
    public static final String COL2="username";
    public static final String COL3="password";
    public static final String COL4="phone";


    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE registercust (ID INTEGER PRIMARY KEY AUTOINCREMENT ,username TEXT,password TEXT ,phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    onCreate(db);

    }
    public long addUser(String user,String pass,String pho)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2,user);
        contentValues.put(COL3,pass);
        contentValues.put(COL4,pho);
        long res=db.insert(TABLE_NAME,null,contentValues);
           db.close();
            return res;
    }
    public boolean checkUser(String user,String pass)
    {  SQLiteDatabase db=getReadableDatabase();
          Cursor cursor=db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE " + COL2 + " =? AND " + COL3 + " =? ", new String[] {user,pass} );
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
