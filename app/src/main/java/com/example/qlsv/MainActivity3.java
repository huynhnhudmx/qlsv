package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity3 extends AppCompatActivity {

    Button btn, btndn;
    EditText edtpw, edtuser;
    Boolean passwordVisible;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        qlsv_database db = new qlsv_database(this);
        btn = (Button) findViewById(R.id.button_signup);
        edtpw = (EditText) findViewById(R.id.edtpw);
        edtuser = findViewById(R.id.edtdn);
        btndn = findViewById(R.id.buttondn);
        hienmatkhau();


        /*
        edtpw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
               final int Right = 2;ư
               if(event.getAction()==MotionEvent.ACTION_UP){
                   if(event.getRawX()>=edtpw.getRight()-edtpw.getCompoundDrawables()[Right].getBounds().width()){
                       int selection=edtpw.getSelectionEnd();
                       if(passwordVisible){
                           // mật khẩu hiển thị
                           edtpw.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.visoff,0);
                           // ẩn mật khẩu đi
                           edtpw.setTransformationMethod(PasswordTransformationMethod.getInstance());
                           passwordVisible=false;
                       }else {
                           // mật khẩu hiện
                           edtpw.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.vis, 0);
                           // ẩn mật khẩu đi
                           edtpw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                           passwordVisible = true;
                       }
                       edtpw.setSelection(selection);
                       return true;
                       }
                   }

                return false;
            }
        });
*/

        //chage_input password


//        btn.setVisibility(View.INVISIBLE);
        //xử lý sự kiện lựa chọn đối tượng đăng nhập
        //xử lý tạo tài khoản SINHVIEN
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangky_dialog();
            }
        });

        // xử lý sự kiện đăng nhập
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String us = edtuser.getText().toString();
                String pw = edtpw.getText().toString();

                Boolean check = db.checkLG_SV(us, pw);
                if (us.equals("null") && pw.equals("null")) {
                    Toast.makeText(MainActivity3.this, "Vui lòng nhập lại hệ thống", Toast.LENGTH_SHORT).show();
                } else {
                    if (check == true) {
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
                        startActivityinfo();
                    }
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

        private void startActivityinfo () {
            String id = edtuser.getText().toString();

            Intent i = new Intent(MainActivity3.this,thongtinsv.class);
            i.putExtra("id", id);
            startActivity(i);

        }

        private void dangky_dialog () {
            qlsv_database db = new qlsv_database(this);
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dangky);
            dialog.setCanceledOnTouchOutside(false);

            Button add = dialog.findViewById(R.id.btn_tch_add);
            Button cancel = dialog.findViewById(R.id.btn_tch_cancel);

            EditText msgv;
            EditText mk;
            EditText hoten;
            EditText trinhdo;
            EditText malop;


            msgv = dialog.findViewById(R.id.tch_add_msgv);
            mk = dialog.findViewById(R.id.tch_add_mkdn);
            hoten = dialog.findViewById(R.id.tch_add_hoten);
            trinhdo = dialog.findViewById(R.id.tch_add_trinhdo);
            malop = dialog.findViewById(R.id.tch_add_malopp);


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String mgv = msgv.getText().toString();
                    String ten = hoten.getText().toString();
                    String td = trinhdo.getText().toString();
                    String MK = mk.getText().toString();
                    String mlop = malop.getText().toString();

                    Boolean insertTCH = db.taoTK_GV(mgv, ten, td, MK, mlop);
                    if (insertTCH == true) {
                        Toast.makeText(MainActivity3.this, "Tạo tài khoản thành công", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity3.this, MainActivity3.class);
                        startActivity(intent);
                        dialog.cancel();

                    } else {
                        Toast.makeText(MainActivity3.this, "Tạo tài khoản không thành công", Toast.LENGTH_LONG).show();
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


        private void hienmatkhau () {
            @SuppressLint("WrongViewCast")
            ImageButton hien = findViewById(R.id.pw_show);
            ImageButton an = findViewById(R.id.pw_hide);
            EditText mk = findViewById(R.id.edtpw);


            an.setVisibility(View.INVISIBLE);
            hien.setVisibility(View.VISIBLE);
            hien.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mk.setInputType(InputType.TYPE_CLASS_TEXT);
                    hien.setVisibility(View.INVISIBLE);
                    an.setVisibility(View.VISIBLE);

                }
            });

            an.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mk.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    hien.setVisibility(View.VISIBLE);
                    an.setVisibility(View.INVISIBLE);

                }
            });
            hien.setVisibility(View.VISIBLE);


        }
    }


