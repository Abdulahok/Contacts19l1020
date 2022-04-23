package com.example.contactsapplication;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "contacts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE CONTACTS_TABLE(NAME TEXT PRIMARY KEY , NUMBER TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addone(Contact c){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", c.getName());
        cv.put("NUMBER", c.getContactno());
        long insert = db.insert("CONTACTS_TABLE", null, cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }


    public List<Contact> getall() {
        List<Contact> mylist = new ArrayList<>();
        String queryString = "Select * FROM CONTACTS_TABLE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(0);
                String number = cursor.getString(1);
            Contact contact = new Contact(name,number);
            mylist.add(contact);}
            while(cursor.moveToNext());
        }else{}

        cursor.close();
        db.close();
        return mylist;
    }
}
