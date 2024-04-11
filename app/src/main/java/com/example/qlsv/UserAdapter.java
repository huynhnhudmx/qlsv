package com.example.qlsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.dslop_holder> {
    private Context context;
    private ArrayList malop,tenlop,makhoa;
    private ViewGroup parent;
    private int viewType;

    public UserAdapter (Context context, ArrayList malop,ArrayList tenlop, ArrayList makhoa){
        this.context = context;
        this.malop = malop;
        this.tenlop = tenlop;
        this.makhoa = makhoa;
    }

    @NonNull
    @Override
    public dslop_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.xemdslop,parent,false);
        return new dslop_holder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull UserAdapter.dslop_holder holder, int position) {
        holder.malop.setText(String.valueOf(malop.get(position)));
        holder.tenlop.setText(String.valueOf(tenlop.get(position)));
        holder.makhoa.setText(String.valueOf(makhoa.get(position)));
    }

    @Override
    public int getItemCount() {
        return malop.size();
    }


    public class dslop_holder extends RecyclerView.ViewHolder{
        TextView malop, tenlop,makhoa;
        public dslop_holder(@NonNull View itemView){
            super(itemView);
            malop = itemView.findViewById(R.id.ds_malop);
            tenlop = itemView.findViewById(R.id.ds_tenlop);
            makhoa = itemView.findViewById(R.id.ds_makhoa);
        }
    }
}
