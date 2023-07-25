package com.trangchu_user.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trangchu_user.Chi_Tiet_Sach;
import com.trangchu_user.Class.TaiLieu;
import com.trangchu_user.R;

import java.util.ArrayList;

public class LoadAllAdapter extends RecyclerView.Adapter<LoadAllAdapter.MyViewHolder>{

    ArrayList<TaiLieu> newTaiLieu;
    String tendg;
    Integer madg;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mai_layout_book, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TaiLieu taiLieu = newTaiLieu.get(position);
        holder.name.setText(taiLieu.getTenSach());
        holder.sl.setText(""+taiLieu.getSoLuong());
        holder.img.setImageResource(Integer.parseInt(taiLieu.getHinh().trim()));

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Chi_Tiet_Sach.class);
                intent.putExtra("id", ""+taiLieu.getID());
                view.getContext().startActivity(intent);
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Chi_Tiet_Sach.class);
                intent.putExtra("id", ""+taiLieu.getID());
                view.getContext().startActivity(intent);
            }
        });
    }

    public LoadAllAdapter(ArrayList<TaiLieu> newArrayList1, String Ten, Integer Ma) {

        this.newTaiLieu = newArrayList1;
        this.tendg = Ten;
        this.madg = Ma;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name, sl;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvnamebook);
            sl = itemView.findViewById(R.id.tvsl);
            img = itemView.findViewById(R.id.imageView);

        }
    }

    @Override
    public int getItemCount() {
        return newTaiLieu.size();
    }

}
