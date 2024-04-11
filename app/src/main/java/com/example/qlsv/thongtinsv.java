package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class thongtinsv extends AppCompatActivity {

    TextView txt1, txt2, txt3, txt4;
    Button bntud, btngvql ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinsv);
        qlsv_database db = new qlsv_database(this);
        btngvql =findViewById(R.id.btngvql);
        bntud = findViewById(R.id.bntudgv);
        txt1 = findViewById(R.id.msgvud);
        txt2 = findViewById(R.id.hotenud);
        txt3 = findViewById(R.id.trinhdoud);
        txt4 = findViewById(R.id.malopudate);







        Intent intent = this.getIntent();
        String id = intent.getStringExtra("id");
        Cursor cursor = db.getdataSTU_withID(id);
        while (cursor.moveToNext()){
            txt1.setText(cursor.getString(0)); // msgv
            txt2.setText(cursor.getString(1)); //hoten
            txt3.setText(cursor.getString(2)); //   trinhdo
            txt4.setText(cursor.getString(3)); // malop



        }

        btngvql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(thongtinsv.this, Selectgiangvien.class);
                startActivity(i);
                //thoat

            }
        });
        bntud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogupdate();
            }
        });


    }

    // hàm hiển thị cửa sổ  dialogexit
    private void dialogupdate() {
        Dialog i= new Dialog(this);
        i.setContentView(R.layout.capnhatsvdialog);
        i.setCanceledOnTouchOutside(false);
        // tắt click ngoài
        Button btnyes=i.findViewById(R.id.buttonyes);
        Button btnno=i.findViewById(R.id.buttonno);
        btnyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(thongtinsv.this, updatesv.class);
                startActivity(i);
                //thoat

            }
        });

        //chọn NO sẽ thoát ra ngoài
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.cancel();
            }
        });
        i.show();
    }
}