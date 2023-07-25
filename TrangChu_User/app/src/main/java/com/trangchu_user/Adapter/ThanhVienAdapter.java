package com.trangchu_user.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trangchu_user.Class.ThanhVien;
import com.trangchu_user.R;

import java.util.ArrayList;


public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{

    private ArrayList<ThanhVien> list;

    public ThanhVienAdapter(ArrayList<ThanhVien> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThanhVien d = list.get(position);
        holder.ten.setText(d.getTen());
        holder.hinh.setImageResource(d.getHinh());
        holder.congviec.setText(d.getCongviec());
        holder.danhgia.setText(d.getDanhgia());
        holder.select.setBackgroundResource(d.getMau());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView hinh;
        public TextView ten, congviec, danhgia;
        public RelativeLayout select;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.ten);
            congviec = itemView.findViewById(R.id.congviec);
            danhgia = itemView.findViewById(R.id.danhgia);
            hinh = itemView.findViewById(R.id.hinh);
            select = itemView.findViewById(R.id.select);
        }
    }
}
