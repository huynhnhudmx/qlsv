package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Selectgiangvien extends AppCompatActivity {
 Button xemdiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectgiangvien);

        xemdiem = findViewById(R.id.btnchamdiem);

        xemdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Selectgiangvien.this,chamdiemsv.class);
                startActivity(i);
            }
        });
    }


}