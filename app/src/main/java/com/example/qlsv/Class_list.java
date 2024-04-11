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

public class Class_list extends AppCompatActivity {
    ArrayList<String > mlop,tlop,mkhoa;
    RecyclerView recyclerView;
    UserAdapter adapter;
    qlsv_database db = new qlsv_database(this);
    Button tim,btnthem,btnxoa,btnexit, btnsua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        mlop = new ArrayList<>();
        tlop = new ArrayList<>();
        mkhoa = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_dslopsv);
        btnthem = findViewById(R.id.dslop_them);
        btnxoa = findViewById(R.id.btnxoann);
        tim = findViewById(R.id.dslop_tim);
        btnsua = findViewById(R.id.dslop_sua);
        btnexit = findViewById(R.id.btnthoatnn);
        adapter = new UserAdapter(this,mlop,tlop,mkhoa);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaySTU();

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              xemdslop_dialog();

            }
        });

        tim.setOnClickListener(new View.OnClickListener() {
            @Override()
            public void onClick(View v) {
                timlop();
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
            public void onClick(View v) {  deletelop();}
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

        // tìm lớp
    private void timlop() {
        qlsv_database db = new qlsv_database(this);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.timlop);
        dialog.setCanceledOnTouchOutside(false);

        Button find = dialog.findViewById(R.id.btn_timmalop);
        Button cancel = dialog.findViewById(R.id.btn_huytimmalop);

        EditText mlop = dialog.findViewById(R.id.nhapmaloptim);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mlopp = mlop.getText().toString();
                Intent i = new Intent(Class_list.this, class_view.class);
                i.putExtra("MALOP",mlopp);
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

    // hiển thị danh sách lớp
    private void xemdslop_dialog() {
        qlsv_database db = new qlsv_database(this);
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.themdslop);
        dialog.setCanceledOnTouchOutside(false);

        Button add = dialog.findViewById(R.id.btn_themlop);
        Button thoat = dialog.findViewById(R.id.btn_tch_canceldslop);


        EditText malop;
        EditText tenlop;
        EditText makhoa;


        malop = dialog.findViewById(R.id.tch_add_mlop);
        tenlop = dialog.findViewById(R.id.tch_add_tenlop);
        makhoa = dialog.findViewById(R.id.tch_add_makhoa);

// them lop
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mlop = malop.getText().toString();
                String tlop = tenlop.getText().toString();
                String mkhoa = makhoa.getText().toString();


                Boolean insertlop = db.insertCLASS(mlop, tlop, mkhoa);
                if (insertlop == true) {
                    Toast.makeText(Class_list.this, "Thêm lớp được rồi đó", Toast.LENGTH_LONG).show();
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




    // sửa lớp


    private void sua(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.sualop);
        dialog.setCanceledOnTouchOutside(false);
        qlsv_database db = new qlsv_database(this);

        Button huy = dialog.findViewById(R.id.huylop);
        Button sua = dialog.findViewById(R.id.sualop);

        EditText malop = dialog.findViewById(R.id.sua_malop);
        EditText tenl = dialog.findViewById(R.id.sua_tenlop);
        EditText makh = dialog.findViewById(R.id.sua_makhoa);
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String malp =malop.getText().toString();
                String tenlop = tenl.getText().toString();

                String makhoa = makh.getText().toString();
                boolean kq = db.chinhsualop(malp,tenlop,makhoa);
                if (kq == true) {
                    Toast.makeText(getApplicationContext(),"Thành công !",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"sửa không thành công",Toast.LENGTH_LONG).show();
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
    private  void deletelop(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.deletelop_dialog);
        dialog.setCanceledOnTouchOutside(false);
        qlsv_database db = new qlsv_database(this);

        Button btnhuy= dialog.findViewById(R.id.btnhuylop);
        Button btnxacnhan = dialog.findViewById(R.id.btnxacnhanxoalop);
        EditText edtnhap = dialog.findViewById(R.id.edtnhapxoalop);

        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtnhap.getText().toString();
                boolean kq = db.deletelop(id);
                if (kq == true) {
                    Toast.makeText(getApplicationContext(),"Đã xóa lớp học",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"xóa không thành công",Toast.LENGTH_LONG).show();
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

        Cursor cursor = db.getdataClss();
        while (cursor.moveToNext()) {
            mlop.add(cursor.getString(0));
            tlop.add(cursor.getString(1));
            mkhoa.add(cursor.getString(2));

        }
    }



}