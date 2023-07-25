package com.trangchu_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trangchu_user.Class.CTMS;
import com.trangchu_user.R;

import java.util.ArrayList;

public class TraAdapter extends RecyclerView.Adapter<TraAdapter.MyViewHolder> {
    Context context;
    ArrayList<CTMS> newArrayList;

    public TraAdapter(Context context, ArrayList<CTMS> item_saches) {
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
        CTMS item_sach= newArrayList.get(position);
//        holder.TinhTrangSach.setText(item_sach.getTinhTrangSach());
        holder.tvHeading.setText(item_sach.getTensach());
        holder.NgayTra.setText(item_sach.getNgayTra());
//        holder.titleImage.setImageResource(item_sach.getTitleImage());
    }

    @Override
    public int getItemCount() {
        return newArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //        ShapeableImageView titleImage;
        TextView TinhTrangSach, NgayTra, tvHeading;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            titleImage = itemView.findViewById(R.id.title_image);
//            TinhTrangSach = itemView.findViewById(R.id.tvHeading);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            NgayTra = itemView.findViewById(R.id.tvNgay);
        }
    }
}
