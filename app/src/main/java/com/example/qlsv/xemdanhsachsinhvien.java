package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class xemdanhsachsinhvien extends AppCompatActivity {
    Button btntim,btnthoat,btnsuasv,btnthem,btnxoa;
    RecyclerView recyclerView;
    qlsv_database db = new qlsv_database(this);
    ArrayList<String > mssv,hoten,ngaysinh,gioitinh,malop;
    AdapterSV adapter  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemdanhsachsinhvien);
        btntim = findViewById(R.id.btntimsv);
        btnthoat = findViewById(R.id.btnthoatsvien);
        btnsuasv = findViewById(R.id.btnsuasv);
        btnthem =findViewById(R.id.btnthemsv);
        btnxoa =findViewById(R.id.btnxoasv);
        mssv = new ArrayList<>();
        hoten = new ArrayList<>();
        ngaysinh = new ArrayList<>();
        gioitinh = new ArrayList<>();
        malop = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_dslop);
        adapter = new AdapterSV(this, mssv, hoten, gioitinh, ngaysinh,malop);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaySTU();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               xemdanhsachsv_dialog();

            }
        });


        btntim.setOnClickListener(new View.OnClickListener() {
            @Override()
            public void onClick(View v) {
                timsv();
            }
        });

        // update lop
        btnsuasv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sua();
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {  deletesv();}
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    // tìm sinhvien
    private void timsv() {
        qlsv_database db = new qlsv_database(this);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.timsv);
        dialog.setCanceledOnTouchOutside(false);

        Button find = dialog.findViewById(R.id.btn_timsvien);
        Button cancel = dialog.findViewById(R.id.btn_huysvien);

        EditText mssv = dialog.findViewById(R.id.nhapmasv);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String masv = mssv.getText().toString();
                Intent i = new Intent(xemdanhsachsinhvien.this,hienthidanhsachtim.class);
                i.putExtra("mssv",masv);
                startActivity(i);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();

    }

    // hiển thị danh sách sinh viên
    private void xemdanhsachsv_dialog() {
        qlsv_database db = new qlsv_database(this);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.themsv);
        dialog.setCanceledOnTouchOutside(false);

        Button add = dialog.findViewById(R.id.btn_themsv);
        Button thoat = dialog.findViewById(R.id.btn_tch_cancelsv);


        EditText mssv;
        EditText hoten;
        EditText ngaysinh;
        EditText gioitinh;
        EditText malop;


        mssv = dialog.findViewById(R.id.themmsv);
        hoten = dialog.findViewById(R.id.tch_add_ten);
       ngaysinh= dialog.findViewById(R.id.edtngaysinh);
       gioitinh= dialog.findViewById(R.id.edtngioitinh);
        malop= dialog.findViewById(R.id.edtmalop);

// them sinh vien
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String masv = mssv.getText().toString();
                String ht = hoten.getText().toString();
                String ns = ngaysinh.getText().toString();
                String gt = gioitinh.getText().toString();
                String ml = malop.getText().toString();


                Boolean insertsinhvien = db.insertsinhvien(masv, ht, ns,gt,ml);
                if (insertsinhvien == true) {
                    Toast.makeText(xemdanhsachsinhvien.this, "Thêm sinh viên được rồi đó", Toast.LENGTH_LONG).show();
                    finish();
                    db.close();

                } else {
                    Toast.makeText(getApplicationContext(), "Thử lại lần sau", Toast.LENGTH_LONG).show();
                }
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }




    // sửa sinh vien


    private void sua(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.suasinhvien);
        dialog.setCanceledOnTouchOutside(false);
        qlsv_database db = new qlsv_database(this);

        Button huy = dialog.findViewById(R.id.btnhuysuasv);
        Button sua = dialog.findViewById(R.id.btnsuasvv);

        EditText mssv = dialog.findViewById(R.id.suamasv);
        EditText hoten = dialog.findViewById(R.id.suahoten);
        EditText ngaysinh = dialog.findViewById(R.id.suangaysinh);
        EditText gioitinh = dialog.findViewById(R.id.suagioitinh);
        EditText malop = dialog.findViewById(R.id.suamalop);
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String masv =mssv.getText().toString();
                String ht = hoten.getText().toString();

                String ns = ngaysinh.getText().toString();
                String gt = gioitinh.getText().toString();
                String ml = malop.getText().toString();
                boolean kq = db.chinhsuasv(masv,ht,ns,gt,ml);
                if (kq == true) {
                    Toast.makeText(getApplicationContext(),"Thành công !",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Sửa không thành công",Toast.LENGTH_LONG).show();
                }
            }
        });

        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();

    }
    // xóa  sinh viên
    private  void deletesv(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.xoasinhvien);
        dialog.setCanceledOnTouchOutside(false);
        qlsv_database db = new qlsv_database(this);

        Button btnhuy= dialog.findViewById(R.id.btnnhuysvv);
        Button btnxacnhan = dialog.findViewById(R.id.btnxacnhansv);
        EditText edtnhap = dialog.findViewById(R.id.edtnhapsv);

        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtnhap.getText().toString();
                boolean kq = db.deletesv(id);
                if (kq == true) {
                    Toast.makeText(getApplicationContext(), "Đã xóa sinh viên ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Xóa sinh viên không thành công", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();

    }



    private void displaySTU() {

        Cursor cursor = db.getdatasinhvien();
        while (cursor.moveToNext()) {
            mssv.add(cursor.getString(0));
            hoten.add(cursor.getString(1));
            ngaysinh.add(cursor.getString(2));
            gioitinh.add(cursor.getString(3));
            malop.add(cursor.getString(8));

        }
    }



}


