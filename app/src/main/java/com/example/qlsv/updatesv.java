package com.example.qlsv;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class updatesv extends AppCompatActivity {

    EditText edt1, edt2, edt3, edt4;
    Button bntud;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatesv);
        qlsv_database db = new qlsv_database(this);
        bntud = findViewById(R.id.bntudate);
        edt1 = findViewById(R.id.msgvien);
        edt2 = findViewById(R.id.hoten);
        edt3 = findViewById(R.id.trinhdodate);
        edt4 = findViewById(R.id.capmalop);



        bntud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msgv = edt1.getText().toString();
                String hoten = edt2.getText().toString();
                String trinhdo = edt3.getText().toString();
                String malop = edt4.getText().toString();


                // truyen du lieu nhap vao hàm Update//////////
                Boolean kq = db.updateSTU(msgv,hoten,trinhdo,malop);
                if (kq == true) {
                    Toast.makeText(getApplicationContext(), "CAP NHAT THANH CONG!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "CAP NHAT KHONG THANH CONG!", Toast.LENGTH_SHORT).show();
                }

                // Hiển thị danh sách sinh viên sau khi UPDATE
                Cursor res = db.getgiangvien();
                if(res.getCount()==0){
                    Toast.makeText(updatesv.this,"Hiển thị không thành công",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Mã số giảng viên:"+res.getString(0)+"\n");
                    buffer.append("Họ tên giảng viên:"+res.getString(1)+"\n");
                    buffer.append("Trình độ :"+res.getString(2)+"\n");
                    buffer.append("Mã lớp:"+res.getString(3)+"\n\n");


                }

                AlertDialog.Builder builder = new AlertDialog.Builder(updatesv.this);
                builder.setCancelable(true);
                builder.setTitle("THÔNG TIN GIẢNG VIÊN");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

}
}
