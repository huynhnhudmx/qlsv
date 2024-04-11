package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class class_view extends AppCompatActivity {
    ArrayList<String > mlop,tlop,mkhoa;
    RecyclerView recyclerView;
    UserAdapter adapter;
    qlsv_database db = new qlsv_database(this);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_view);
        mlop = new ArrayList<>();
        tlop = new ArrayList<>();
        mkhoa = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_lop);
        adapter = new UserAdapter(this, mlop, tlop, mkhoa);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button thoat = findViewById(R.id.lop_thoat);
        Intent i = getIntent();
        String mlopp = i.getStringExtra("MALOP");
        displayCLASS(mlopp);

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void displayCLASS(String mlopp) {

        Cursor cursor = db.getdataClss_withID(mlopp);
        while (cursor.moveToNext()) {
            mlop.add(cursor.getString(0));
            tlop.add(cursor.getString(1));
            mkhoa.add(cursor.getString(2));

        }
    }
}