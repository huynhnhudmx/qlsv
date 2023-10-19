package com.example.qlsv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class qlsv_database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QLSV.db";
    private static final int DATABASE_VERSION = 1;

    public qlsv_database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng SINHVIEN
        db.execSQL("CREATE TABLE sinhvien ( " +
                "mssv TEXT PRIMARY KEY, " +
                "hoten TEXT, " +
                "ngaysinh TEXT, " +
                "gioitinh TEXT, " +
                "noisinh TEXT, " +
                "diachi TEXT, " +
                "sdt TEXT, " +
                "password TEXT, " +
                "malop TEXT REFERENCES lop (malop) " +
                ");");

        // Tạo bảng GIAOVIEN
        db.execSQL("CREATE TABLE giaovien ( " +
                "msgv TEXT PRIMARY KEY, " +
                "hoten TEXT, " +
                "trinhdo TEXT, " +
                "makhoa TEXT REFERENCES khoa (makhoa) " +
                ");");

        // Tạo bảng LOP
        db.execSQL("CREATE TABLE lop ( " +
                "malop TEXT PRIMARY KEY, " +
                "tenlop TEXT, " +
                "makhoa TEXT REFERENCES khoa (makhoa) " +
                ");");

        // Tạo bảng KHOA
        db.execSQL("CREATE TABLE khoa ( " +
                "makhoa TEXT PRIMARY KEY, " +
                "tenkhoa TEXT " +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa các bảng cũ
        db.execSQL("DROP TABLE IF EXISTS sinhvien");
        db.execSQL("DROP TABLE IF EXISTS giaovien");
        db.execSQL("DROP TABLE IF EXISTS lop");
        db.execSQL("DROP TABLE IF EXISTS khoa");

        // Tạo lại các bảng mới
        onCreate(db);
    }



    // sự kiện tạo tài khoảng sinh viên
    public Boolean taoTK_SV(String mssv, String hoten,String ngaysinh, String gioitinh, String noisinh,
                             String diachi, String sdt,String mk, String mlop) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mssv", mssv);
        values.put("hoten", hoten);
        values.put("ngaysinh",ngaysinh);
        values.put("gioitinh",gioitinh);
        values.put("noisinh",noisinh);
        values.put("diachi",diachi);
        values.put("sdt",sdt);
        values.put("password", mk);
        values.put("malop", mlop);


        Long kq = DB.insert("sinhvien", null, values);
        if (kq == -1) {
            return false;
        } else {
            return true;
        }
    }

    // sự kiện kiểm tra đăng nhập sinh viên
    public Boolean checkLG_SV(String user, String pass) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM sinhvien WHERE mssv = ? AND password = ?", new String[]{user, pass});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }
}
