package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class quantri extends AppCompatActivity {

    Button qlkhoa, qlsinhvien , qllop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantri);

        qllop = findViewById(R.id.btn_qtrilop);
        qlsinhvien = findViewById(R.id.btn_qtrisv);
        qlkhoa = findViewById(R.id.btn_qtrikhoa);



        qllop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Class_list.class);
                startActivity(i);
            }
        });

        qlsinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(quantri.this,xemdanhsachsinhvien.class);
                startActivity(i);
            }
        });

        qlkhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(quantri.this,xemdskhoa.class);
                startActivity(i);
            }
        });

    }
}