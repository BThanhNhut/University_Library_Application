package com.trangchu_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trangchu_user.Class.MuonTra;
import com.trangchu_user.R;

import java.util.ArrayList;

public class Muon_TraAdapter extends RecyclerView.Adapter<Muon_TraAdapter.MyViewHolder> {
    Context context;
    ArrayList<MuonTra> newArrayList;

    public Muon_TraAdapter(Context context, ArrayList<MuonTra> item_saches) {
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
        MuonTra item_sach= newArrayList.get(position);
//        holder.TinhTrangSach.setText(item_sach.getTinhTrangSach());
        holder.NgayMuon.setText(item_sach.getNgayMuon());
//        holder.titleImage.setImageResource(item_sach.getTitleImage());
    }

    @Override
    public int getItemCount() {
        return newArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //        ShapeableImageView titleImage;
        TextView NgayMuon;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            titleImage = itemView.findViewById(R.id.title_image);
//            TinhTrangSach = itemView.findViewById(R.id.tvHeading);
            NgayMuon = itemView.findViewById(R.id.tvNgay);
        }
    }
}
