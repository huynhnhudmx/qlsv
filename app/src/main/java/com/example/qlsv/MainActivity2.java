package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
   ImageButton  btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn2=(ImageButton) findViewById(R.id.btngv);
        btn3 =(ImageButton) findViewById(R.id.btnquantri);
        btn4 =(ImageButton) findViewById(R.id.btntrangsv);



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Bạn là giảng viên", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Bạn là người quản trị", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity2.this, quantri.class);
                startActivity(i);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Chào mừng bạn đến với trang sinh viên", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity2.this,trangsv.class);
                startActivity(i);
            }
        });

    }
}