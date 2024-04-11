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

public class xemdskhoa extends AppCompatActivity {

    Button btntim,btnthoat,btnsua,btnthem,btnxoa;
    RecyclerView recyclerView;
    qlsv_database db = new qlsv_database(this);
    ArrayList<String > makhoa, tenkhoa;
    AdapterKhoa adapter  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemdskhoa);
        btntim = findViewById(R.id.btntim);
        btnthoat = findViewById(R.id.btnthoat);
        btnsua = findViewById(R.id.btnsua);
        btnthem =findViewById(R.id.btnthem);
        btnxoa =findViewById(R.id.btnxoa);
        makhoa = new ArrayList<>();
        tenkhoa = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_dskhoa);
        adapter = new AdapterKhoa (this,makhoa,tenkhoa);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaySTU();

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xemdanhsachkhoa_dialog();

            }
        });

        btntim.setOnClickListener(new View.OnClickListener() {
            @Override()
            public void onClick(View v) {
                timkhoa();
            }
        });

        // update lop
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sua();
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                deletekhoa();}
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    // tìm khoa
    private void timkhoa() {
        qlsv_database db = new qlsv_database(this);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.timkhoa);
        dialog.setCanceledOnTouchOutside(false);

        Button find = dialog.findViewById(R.id.btn_timkhoa);
        Button cancel = dialog.findViewById(R.id.btn_huykhoa);

        EditText makhoa = dialog.findViewById(R.id.nhapmakhoa);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mkhoa = makhoa.getText().toString();
                Intent i = new Intent(xemdskhoa.this,hienthidanhsachtimkhoa.class);
                i.putExtra("makhoa",mkhoa);
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

    // hiển thị danh sách khoa
    private void xemdanhsachkhoa_dialog() {
        qlsv_database db = new qlsv_database(this);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.themkhoa);
        dialog.setCanceledOnTouchOutside(false);

        Button add = dialog.findViewById(R.id.btn_themkhoa);
        Button thoat = dialog.findViewById(R.id.btn_huytkhoa);


        EditText makhoa;
        EditText tenkhoa;


        makhoa = dialog.findViewById(R.id.themmkhoa);
        tenkhoa = dialog.findViewById(R.id.ten_khoa);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mkhoa = makhoa.getText().toString();
                String tkhoa = tenkhoa.getText().toString();

                Boolean insertkhoa = db.insertkhoa(mkhoa,tkhoa);
                if (insertkhoa == true) {
                    Toast.makeText(xemdskhoa.this, "Thêm khoa được rồi đó", Toast.LENGTH_LONG).show();
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




    // SỬA KHOA


    private void sua(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.suakhoa);
        dialog.setCanceledOnTouchOutside(false);
        qlsv_database db = new qlsv_database(this);

        Button huy = dialog.findViewById(R.id.huykhoa);
        Button sua = dialog.findViewById(R.id.suakhoa);

        EditText makhoa = dialog.findViewById(R.id.suakh);
        EditText tenkhoa = dialog.findViewById(R.id.suaten);

        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mkhoa = makhoa.getText().toString();
                String tkhoa = tenkhoa.getText().toString();


                boolean kq = db.chinhsuakhoa(mkhoa,tkhoa);
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
    // xóa  lớp
    private  void deletekhoa(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.xoakhoa);
        dialog.setCanceledOnTouchOutside(false);
        qlsv_database db = new qlsv_database(this);

        Button btnhuy= dialog.findViewById(R.id.btnhuy);
        Button btnxacnhan = dialog.findViewById(R.id.btnxacnhan);
        EditText edtnhap = dialog.findViewById(R.id.edtnhap);

        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtnhap.getText().toString();
                boolean kq = db.deletekhoa(id);
                if (kq == true) {
                    Toast.makeText(getApplicationContext(), "Đã xóa tên khoa ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Xóa tên khoa không thành công", Toast.LENGTH_LONG).show();
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

        Cursor cursor = db.getdatakhoa();
        while (cursor.moveToNext()) {
            makhoa.add(cursor.getString(0));
            tenkhoa.add(cursor.getString(1));


        }
    }
}