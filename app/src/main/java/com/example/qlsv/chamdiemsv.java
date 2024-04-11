package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class chamdiemsv extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7;
    Button btn1, btn2,btn_luu;

    qlsv_database db = new qlsv_database(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamdiemsv);
        ed1 = findViewById(R.id.stname);
        ed2 = findViewById(R.id.mark1);
        ed3 = findViewById(R.id.mark2);
        ed4 = findViewById(R.id.mark3);
        ed5 = findViewById(R.id.total);
        ed6 = findViewById(R.id.avg);
        ed7 = findViewById(R.id.grade);


        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn_luu = findViewById(R.id.btnluudiem);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                diem();
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        btn_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mssv = ed1.getText().toString();
                String d1 = ed2.getText().toString();
                String d2 = ed3.getText().toString();
                String d3 = ed4.getText().toString();
                boolean kq = db.insertDIEM(mssv,d1,d2,d3);
                if (kq==true ) {
                    Toast.makeText(chamdiemsv.this, "Thành Công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(chamdiemsv.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    public void diem()
    {

        int d1,d2,d3,tot;
        double trungbinh;
        String loai;
        d1 = Integer.parseInt(ed2.getText().toString());
        d2 = Integer.parseInt(ed3.getText().toString());
        d3 = Integer.parseInt(ed4.getText().toString());

        tot = d1 + d2 + d3;
        ed5.setText(String.valueOf(tot));
        trungbinh = tot/3;
        ed6.setText(String.valueOf(trungbinh));

        if(trungbinh > 75)
        {
            ed7.setText("A");
        }
        else if(trungbinh > 65)
        {
            ed7.setText("B");
        }

        else if(trungbinh > 55)
        {
            ed7.setText("C");
        }


        else if(trungbinh > 40)
        {
            ed7.setText("D");
        }

        else
        {
            ed7.setText("Fail");
        }
    }

    public void clear()
    {
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed5.setText("");
        ed6.setText("");
        ed7.setText("");

        ed1.requestFocus();

    }
}