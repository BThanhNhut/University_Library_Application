package com.trangchu_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trangchu_user.Class.TaiLieuTraSach;
import com.trangchu_user.R;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.MyViewHolder> {
    Context context;
    ArrayList<TaiLieuTraSach> newArrayList1;

    public SachAdapter(Context context, ArrayList<TaiLieuTraSach> newArrayList1) {
        this.context = context;
        this.newArrayList1 = newArrayList1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_item_tra_sach, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TaiLieuTraSach taiLieu = newArrayList1.get(position);
        holder.tenSach.setText(taiLieu.getTenSach());
//        holder.titleImage.setImageResource(item_sach.getTitleImage());
    }

    @Override
    public int getItemCount() {
        return newArrayList1.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //        ShapeableImageView titleImage;
        TextView tenSach;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            titleImage = itemView.findViewById(R.id.title_image);
            tenSach = itemView.findViewById(R.id.tvHeading);
        }
    }
}
