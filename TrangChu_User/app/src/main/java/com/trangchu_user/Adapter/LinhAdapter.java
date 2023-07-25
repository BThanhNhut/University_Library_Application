package com.trangchu_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.trangchu_user.Class.Item_Sach;
import com.trangchu_user.R;

import java.util.ArrayList;

public class LinhAdapter extends RecyclerView.Adapter<LinhAdapter.MyViewHolder> {
    Context context;
    ArrayList<Item_Sach> newArrayList;

    public LinhAdapter(Context context, ArrayList<Item_Sach> item_saches) {
        this.context = context;
        this.newArrayList = item_saches;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_item_tra_sach, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item_Sach item_sach= newArrayList.get(position);
        holder.tvHeading.setText(item_sach.getTvHeading());
        holder.tvNgay.setText(item_sach.getTvNgay());
    }

    @Override
    public int getItemCount() {
        return newArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvHeading, tvNgay;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            tvNgay = itemView.findViewById(R.id.tvNgay);
        }
    }
}
