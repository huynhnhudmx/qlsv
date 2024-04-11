package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;

public class trangsv extends AppCompatActivity {

    ImageButton imgtkb, imgsotay, imglichthi, imgqldt, imgqllop, imgvieclam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangsv);
        imgtkb = findViewById(R.id.imge1);
        imgsotay = findViewById(R.id.imge2);
        imglichthi = findViewById(R.id.imge3);
        imgqldt = findViewById(R.id.imge4);
        imgqllop = findViewById(R.id.imge5);
        imgvieclam = findViewById(R.id.imge6);

        imgtkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.ctec.edu.vn/ctec/index.php?page=thoikhoabieu";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        imgsotay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://drive.google.com/drive/folders/1BtilHX86re6zb0CQ0FdVbyRe_6WfLwkl?usp=sharing";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        imglichthi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.ctec.edu.vn/ctec/index.php?page=lichthihocky";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });


        imgqldt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://115.79.42.45:9090/htqlctec/dangnhap/sv";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });


        imgqllop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://115.79.42.45:7071/sodaubaionline/sinhvien.php";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        imgvieclam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.ctec.edu.vn/ctec/index.php?page=vieclamchung";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

    }
}