package com.example.qlsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterSV  extends RecyclerView.Adapter<AdapterSV.xemdanhsachsinhvien> {
    private Context context;
    private ArrayList mssv,hoten,ngaysinh,gioitinh,malop;
    private ViewGroup parent;
    private int viewType;

    public AdapterSV (Context context, ArrayList mssv,ArrayList hoten, ArrayList ngaysinh,ArrayList gioitinh,ArrayList malop){
        this.context = context;
        this.mssv = mssv;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.malop = malop;
    }

    @NonNull
    @Override
    public AdapterSV.xemdanhsachsinhvien onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.xemdanhsachsv,parent,false);
        return new xemdanhsachsinhvien(v);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterSV.xemdanhsachsinhvien holder, int position) {
        holder.mssv.setText(String.valueOf(mssv.get(position)));
        holder.hoten.setText(String.valueOf(hoten.get(position)));
        holder.ngaysinh.setText(String.valueOf(ngaysinh.get(position)));
        holder.gioitinh.setText(String.valueOf(gioitinh.get(position)));
        holder.malop.setText(String.valueOf(malop.get(position)));
    }

    @Override
    public int getItemCount() {
        return mssv.size();
    }


    public class xemdanhsachsinhvien extends RecyclerView.ViewHolder{
        TextView mssv,hoten,ngaysinh,gioitinh,malop;
        public xemdanhsachsinhvien(@NonNull View itemView){
            super(itemView);
           mssv = itemView.findViewById(R.id.dsmssv);
           hoten = itemView.findViewById(R.id.dsten);
            ngaysinh = itemView.findViewById(R.id.dsngaysinh);
          gioitinh= itemView.findViewById(R.id.dsgt);
           malop = itemView.findViewById(R.id.dsml);
        }
    }
}


