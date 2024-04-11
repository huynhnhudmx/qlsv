package com.example.qlsv;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;

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
                "malop TEXT REFERENCES lop (malop) " +
                ");");

        db.execSQL("CREATE TABLE diem ( " +
                "mssv TEXT REFERENCES sinhvien(mssv) ," +
                "D1 INT, " +
                "D2 INT, " +
                "D3 INT, " +
                "DTONG INT, " +
                "DTRUNGBINH DOUBLE, " +
                "XEPLOAI TEXT);");

        // Tạo bảng GIAOVIEN
        db.execSQL("CREATE TABLE giaovien ( " +
                "msgv TEXT PRIMARY KEY, " +
                "hoten TEXT, " +
                "trinhdo TEXT, " +
                "password TEXT, " +
                "malop TEXT REFERENCES lop (malop) " +

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
        /*
        db.execSQL("DROP TABLE IF EXISTS sinhvien");
        db.execSQL("DROP TABLE IF EXISTS giaovien");
        db.execSQL("DROP TABLE IF EXISTS lop");
        db.execSQL("DROP TABLE IF EXISTS khoa");
        db.execSQL("DROP TABLE IF EXISTS diem");


         */
        // Tạo lại các bảng mới
        onCreate(db);
    }


    // sự kiện tạo tài khoản giảng viên
    public Boolean taoTK_GV(String msgv, String hoten, String trinhdo,String mk, String mlop) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("msgv", msgv);
        values.put("hoten", hoten);
        values.put("trinhdo", trinhdo);
        values.put("password", mk);
        values.put("malop", mlop);

        Long kq = DB.insert("giaovien", null, values);
        if (kq == -1) {
            return false;
        } else {
            return true;
        }
    }

    // sự kiện kiểm tra đăng nhập giảng  viên
    public Boolean checkLG_SV(String user, String pass) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM giaovien WHERE msgv = ? AND password = ?", new String[]{user, pass});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Cursor getdataSTU() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN", null);
        return cursor;
    }

    public Cursor getdataSTU_withClassID(String malop) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN WHERE MALOP = ?", new String[]{malop});
        return cursor;
    }

    // sự kiện lấy dữ liệu theo MSSV
    public Cursor getdataSTU_withID(String id) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM GIAOVIEN WHERE MSGV = ?", new String[]{id});
        return cursor;


    }

    // THÊM DANH SÁCH lop ////
public Boolean  insertCLASS(String malop, String tenlop, String makhoa) {
    SQLiteDatabase DB = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("malop", malop);
    values.put("tenlop", tenlop);
    values.put("makhoa", makhoa);
    Long kq = DB.insert("LOP", null, values);
    if (kq == -1) {
        return false;
    } else {
        return true;
    }
}

    public Cursor getdataClss() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM LOP",null);
        return cursor;
    }

    // HÀM TÌM LỚP THEO MALOP
    public Cursor getdataClss_withID(String mlop) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM LOP WHERE malop = ?",new String[]{mlop});
        return cursor;
    }

    // DANH SÁCH LỚP SAU KHI UPDATE THÔNG TIN SINH VIÊN

    public Cursor getgiangvien(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from giaovien",null);
        return cursor;

    }

    public boolean chinhsualop(String malop, String tenlop, String makhoa){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MALOP",malop);
        values.put("TENLOP",tenlop);
        values.put("MAKHOA",makhoa);
        Cursor cursor = DB.rawQuery("SELECT * FROM LOP WHERE MALOP = ?", new String[]{malop});
        if(cursor.getCount()>0) {
            long kq = DB.update("LOP", values, "MALOP = ?", new String[]{malop});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
        }
    public Boolean deletelop(String malop) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM LOP WHERE MALOP = ?",new String[]{malop});
        if (cursor.getCount()>0) {
            long kq = DB.delete("LOP","MALOP = ?",new String[]{malop});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    //UPDATE thopng tin sinh viên
    public Boolean updateSTU(String msgv, String hoten, String trinhdo, String mlop) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();;
        values.put("msgv",msgv);  // 0
        values.put("hoten",hoten);  // 1
        values.put("trinhdo",trinhdo); // 2
        values.put("malop",mlop);// 3

        /// chuyển từ qlsv_database Sang thongtin.java

        Cursor cursor = DB.rawQuery("SELECT * FROM GIAOVIEN WHERE MSGV = ?",new String[]{msgv});
        if (cursor.getCount()>0) {
            long kq = DB.update("GIAOVIEN",values,"MSGV = ?",new String[]{msgv});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }

    }


    //---------------- SINH VIEN-------------------//

    // THÊM DANH SÁCH SINH VIÊN////
    public Boolean  insertsinhvien(String mssv, String hoten, String ngaysinh, String gioitinh, String malop) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mssv", mssv);
        values.put("hoten", hoten);
        values.put("ngaysinh", ngaysinh);
        values.put("gioitinh", gioitinh);
        values.put("malop", malop);
        Long kq = DB.insert("SINHVIEN", null, values);
        if (kq == -1) {
            return false;
        } else {
            return true;
        }
    }
    // hàm tìm muốn sài hàm nào gắn vô
    public Cursor getdatasinhvien() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN",null);
        return cursor;
    }

    // HÀM TÌM SINH VIEN THEO MA
    public Cursor getdatasinhvienmasv(String mssv) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN WHERE mssv = ?",new String[]{mssv});
        return cursor;
    }

    public boolean chinhsuasv(String mssv, String hoten, String ngaysinh, String gioitinh, String malop){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("mssv",mssv);
        values.put("hoten",hoten);
        values.put("ngaysinh",ngaysinh);
        values.put("gioitinh",gioitinh);
        values.put("malop",malop);
        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN WHERE mssv = ?", new String[]{mssv});
        if(cursor.getCount()>0) {
            long kq = DB.update("SINHVIEN", values, "mssv = ?", new String[]{mssv});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }
    public Boolean deletesv(String mssv) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM SINHVIEN WHERE mssv = ?",new String[]{mssv});
        if (cursor.getCount()>0) {
            long kq = DB.delete("SINHVIEN","mssv = ?",new String[]{mssv});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }



    // ------------------- KHOA -------------------

    // THÊM DANH SÁCH KHOA ////
    public Boolean  insertkhoa(String makhoa, String tenkhoa) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("makhoa", makhoa);
        values.put("tenkhoa",tenkhoa);

        Long kq = DB.insert("KHOA", null, values);
        if (kq == -1) {
            return false;
        } else {
            return true;
        }
    }
    // hàm tìm muốn sài hàm nào gắn vô
    public Cursor getdatakhoa() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM KHOA",null);
        return cursor;
    }

    // HÀM TÌM KHOA THEO MA
    public Cursor getdatakhoamakhoa(String makhoa) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM KHOA WHERE makhoa = ?",new String[]{makhoa});
        return cursor;
    }

    public boolean chinhsuakhoa(String makhoa, String tenkhoa){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("makhoa",makhoa);
        values.put("tenkhoa",tenkhoa);

        Cursor cursor = DB.rawQuery("SELECT * FROM KHOA WHERE makhoa = ?", new String[]{makhoa});
        if(cursor.getCount()>0) {
            long kq = DB.update("KHOA", values, "makhoa = ?", new String[]{makhoa});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }
    }
    public Boolean deletekhoa(String makhoa) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM KHOA WHERE makhoa = ?",new String[]{makhoa});
        if (cursor.getCount()>0) {
            long kq = DB.delete("KHOA","makhoa = ?",new String[]{makhoa});
            if (kq == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }
    }

//===++=++=======================================================================================
public Boolean  insertDIEM(String mssv,String d1, String d2, String d3) {
    SQLiteDatabase DB = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("mssv",mssv);
    values.put("D1", d1);
    values.put("D2", d2);
    values.put("D3", d3);
    long diem1 = Long.parseLong(String.valueOf(d1));
    long diem2 = Long.parseLong(String.valueOf(d2));
    long diem3 = Long.parseLong(String.valueOf(d3));
    long tot = diem1 + diem2 + diem3;
    long trungbinh = tot/3;
    String ed7 = null;
    if(trungbinh > 75)
    {
        ed7 = "A";
    }
    else if(trungbinh > 65)
    {
        ed7="B";
    }

    else if(trungbinh > 55)
    {
        ed7 ="C";
    }


    else if(trungbinh > 40)
    {
        ed7 = "D";
    }

    else
    {
        ed7 = "Fail";
    }
    values.put("DTONG", tot);
    values.put("DTRUNGBINH", trungbinh);
    values.put("XEPLOAI",ed7);
    Long kq = DB.insert("DIEM", null, values);
    if (kq == -1) {
        return false;
    } else {
        return true;
    }
}

public Cursor xuatdiem(String mssv) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM diem WHERE mssv = ?",new String[]{mssv});
        return cursor;
}




}


