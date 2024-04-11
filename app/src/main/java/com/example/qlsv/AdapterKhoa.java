package com.example.qlsv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

    public class AdapterKhoa   extends RecyclerView.Adapter<com.example.qlsv.AdapterKhoa.xemdskhoa> {
        private Context context;
        private ArrayList makhoa,tenkhoa;
        private ViewGroup parent;
        private int viewType;

        public AdapterKhoa (Context context, ArrayList makhoa,ArrayList tenkhoa){
            this.context = context;
            this.makhoa = makhoa;
            this.tenkhoa = tenkhoa;

        }

        @NonNull
        @Override
        public com.example.qlsv.AdapterKhoa.xemdskhoa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.xemkhoa,parent,false);
            return new com.example.qlsv.AdapterKhoa.xemdskhoa(v);
        }


        @Override
        public void onBindViewHolder(@NonNull com.example.qlsv.AdapterKhoa.xemdskhoa holder, int position) {
            holder.makhoa.setText(String.valueOf(makhoa.get(position)));
            holder.tenkhoa.setText(String.valueOf(tenkhoa.get(position)));
        }

        @Override
        public int getItemCount() {
            return makhoa.size();
        }


        public class xemdskhoa extends RecyclerView.ViewHolder{
            TextView makhoa,tenkhoa;
            public xemdskhoa(@NonNull View itemView){
                super(itemView);
                makhoa = itemView.findViewById(R.id.mkh);
                tenkhoa = itemView.findViewById(R.id.dstenkhoa);

            }
        }
}
