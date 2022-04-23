package com.example.contactsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button add, getcontacts;


    private RecyclerView rv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Contact> clist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillcontactlist();
        getcontacts = findViewById(R.id.button2);
        getcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addfromcontacts();
                mAdapter = new MyAdapter(clist, MainActivity.this);
                rv.setAdapter(mAdapter);
            }
        });
        rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(clist, MainActivity.this);
        rv.setAdapter(mAdapter);
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(), Add_Activity.class);
                startActivity(intent);
            }
        });

    }

    private void fillcontactlist() {
        DatabaseHelper dbhelper = new DatabaseHelper(MainActivity.this);
        clist = dbhelper.getall();
    }

    private void addfromcontacts(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CONTACTS}, 0);
        }
        ContentResolver cv = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = cv.query(uri,null,null,null,null);
        if(cursor.getCount()>0){
            DatabaseHelper db = new DatabaseHelper(MainActivity.this);
            while(cursor.moveToNext()){
                @SuppressLint("Range") String name1 = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Contact contact = new Contact(name1,phone);

                db.addone(contact);
            }
            clist = db.getall();
            }
        }


    }