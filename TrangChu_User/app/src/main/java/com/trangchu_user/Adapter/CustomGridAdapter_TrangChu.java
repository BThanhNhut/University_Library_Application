package com.trangchu_user.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trangchu_user.Chi_Tiet_Sach;
import com.trangchu_user.Class.Item_TrangChu;
import com.trangchu_user.Class.TaiLieu;
import com.trangchu_user.R;

import java.util.List;

public class CustomGridAdapter_TrangChu extends RecyclerView.Adapter<CustomGridAdapter_TrangChu.CustomGridViewHolder> {

    public CustomGridAdapter_TrangChu(List<TaiLieu> listItem) {
        this.mListItem = listItem;
    }


    public void setData(List<TaiLieu> list)
    {
        this.mListItem = list;
        notifyDataSetChanged();
    }
    private Context mContext;
    private List<TaiLieu> mListItem;
    @NonNull
    @Override
    public CustomGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_layout_trang_chu, parent, false);
        return new CustomGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomGridViewHolder holder, int position) {
        TaiLieu item_trangChu = mListItem.get(position);
        if (item_trangChu == null)
        {
            return;
        }
        holder.imgBook.setImageResource(Integer.parseInt(item_trangChu.getHinh()));
        holder.tvBook.setText(item_trangChu.getTenSach());
        holder.tvsl.setText(String.valueOf(item_trangChu.getSoLuong()));

        holder.imgBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id", ""+item_trangChu.getID());
                intent.setClass(v.getContext(), Chi_Tiet_Sach.class);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListItem != null)
        {
            return mListItem.size();
        }
        return 0;
    }

    public class CustomGridViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgBook;
        private TextView tvBook, tvsl;

        public CustomGridViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBook = itemView.findViewById(R.id.img_hinh);
            tvBook = itemView.findViewById(R.id.tv_sach_goi_y);
            tvsl = itemView.findViewById(R.id.tvsl);
        }




    }

}
