package com.example.contactsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Activity extends AppCompatActivity {

    Button add2;
    EditText pname, pnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add2 = findViewById(R.id.add2);
        pnumber = findViewById(R.id.pnumber);
        pname = findViewById(R.id.pname);
        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact(pname.getText().toString(), pnumber.getText().toString());
                DatabaseHelper dbhelper = new DatabaseHelper(Add_Activity.this);
                dbhelper.addone(contact);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}