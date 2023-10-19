package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private Button btn;
    private qlsv_database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn = (Button) findViewById(R.id.button_signup);


//        btn.setVisibility(View.INVISIBLE);
        //xử lý sự kiện lựa chọn đối tượng đăng nhập
        //xử lý tạo tài khoản SINHVIEN
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangky_dialog();
            }
        });


    }

    private void dangky_dialog() {
        db = new qlsv_database(this);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dangky);
        dialog.setCanceledOnTouchOutside(false);

        Button add = dialog.findViewById(R.id.btn_tch_add);
        Button cancel = dialog.findViewById(R.id.btn_tch_cancel);

        EditText mssv;
        EditText mk;
        EditText hoten;
        EditText malop;
        EditText noisinh;
        EditText ngaysinh;
        EditText gioitinh;
        EditText diachi;
        EditText sdt;
        mssv = dialog.findViewById(R.id.tch_add_msgv);
        mk = dialog.findViewById(R.id.tch_add_mkdn);
        hoten = dialog.findViewById(R.id.tch_add_hoten);
        malop = dialog.findViewById(R.id.tch_add_malop);
        noisinh = dialog.findViewById(R.id.tch_add_noisinh);
        ngaysinh = dialog.findViewById(R.id.tch_add_ngaysinh);
        gioitinh = dialog.findViewById(R.id.tch_add_gioitinh);
        diachi = dialog.findViewById(R.id.tch_add_diachi);
        sdt = dialog.findViewById(R.id.tch_add_sdt);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String msv = mssv.getText().toString();
                String ten = hoten.getText().toString();
                String ns = ngaysinh.getText().toString();
                String gt = gioitinh.getText().toString();
                String nois = noisinh.getText().toString();
                String dc = diachi.getText().toString();
                String SDT = sdt.getText().toString();
                String MK = mk.getText().toString();
                String mlop = malop.getText().toString();
                Boolean insertTCH = db.taoTK_SV(msv,ten, ns, gt,nois,dc, SDT, MK, mlop);
                if (insertTCH == true) {
                    Toast.makeText(MainActivity3.this, "Tạo tài khoảng thành công", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                    startActivity(intent);
                    db.close();

                } else {
                    Toast.makeText(MainActivity3.this, "Tạo tài khoảng không thành công", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });

        dialog.show();

    }
}

