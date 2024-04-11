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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class hienthidanhsachtim extends AppCompatActivity {
    ArrayList<String > mssv,hoten,ngaysinh,gioitinh,malop;
    RecyclerView recyclerView;
   AdapterSV adapter;
    qlsv_database db = new qlsv_database(this);

    TextView d1,d2,d3,dt,dtb,xl;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hienthidanhsachtim);
        mssv = new ArrayList<>();
        hoten = new ArrayList<>();
        ngaysinh = new ArrayList<>();
        gioitinh = new ArrayList<>();
        malop = new ArrayList<>();
        recyclerView = findViewById(R.id.sinhvien);
        adapter = new AdapterSV(this, mssv, hoten, ngaysinh,gioitinh,malop);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button thoat = findViewById(R.id.btnthoatsv);
        Intent i = getIntent();
        String massv = i.getStringExtra("mssv");
        displaySTU(massv);

        d1 = findViewById(R.id.diem1);
        d2 = findViewById(R.id.diem2);
        d3 = findViewById(R.id.diem3);
        dt = findViewById(R.id.diemtong);
        dtb = findViewById(R.id.diemtrungbinh);
        xl = findViewById(R.id.xeploai);

        Cursor cursor = db.xuatdiem(massv);
        while (cursor.moveToNext()) {
            d1.setText(cursor.getString(1));
            d2.setText(cursor.getString(2));
            d3.setText(cursor.getString(3));
            dt.setText(cursor.getString(4));
            dtb.setText(cursor.getString(5));
            xl.setText(cursor.getString(6));
        }
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void  displaySTU(String massv) {

        Cursor cursor = db.getdatasinhvienmasv(massv);
        while (cursor.moveToNext()) {
            mssv.add(cursor.getString(0));
            hoten.add(cursor.getString(1));
            ngaysinh.add(cursor.getString(2));
            gioitinh.add(cursor.getString(3));
            malop.add(cursor.getString(8));

        }
    }
}