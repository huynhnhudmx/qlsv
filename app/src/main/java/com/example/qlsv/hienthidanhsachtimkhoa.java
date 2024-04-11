package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class hienthidanhsachtimkhoa extends AppCompatActivity {

    ArrayList<String > makhoa,tenkhoa;
    RecyclerView recyclerView;
    AdapterKhoa adapter;
    qlsv_database db = new qlsv_database(this);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hienthidanhsachtimkhoa);
        makhoa = new ArrayList<>();
        tenkhoa = new ArrayList<>();
        recyclerView = findViewById(R.id.khoa);
        adapter = new AdapterKhoa(this, makhoa, tenkhoa);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button thoat = findViewById(R.id.btnthoatkhoa);
        Intent i = getIntent();
        String mkhoa = i.getStringExtra("makhoa");
        displaySTU(mkhoa);

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void  displaySTU(String mkh) {

        Cursor cursor = db.getdatakhoamakhoa(mkh);
        while (cursor.moveToNext()) {
            makhoa.add(cursor.getString(0));
            tenkhoa.add(cursor.getString(1));


        }
    }
}