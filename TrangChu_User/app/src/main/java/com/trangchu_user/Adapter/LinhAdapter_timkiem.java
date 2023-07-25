package com.trangchu_user.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trangchu_user.Chi_Tiet_Sach;
import com.trangchu_user.Class.TaiLieu;
import com.trangchu_user.R;

import java.util.List;

public class LinhAdapter_timkiem extends  RecyclerView.Adapter<LinhAdapter_timkiem.CustomGridViewHolder> {

    private List<TaiLieu> mListItem;

    public LinhAdapter_timkiem(List<TaiLieu> listItem) {
        this.mListItem = listItem;
    }

    @NonNull
    @Override
    public CustomGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timkiem, parent, false);
        return new CustomGridViewHolder(view);
    }

    public void setSearchList(List<TaiLieu> mlist)
    {
        this.mListItem = mlist;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull CustomGridViewHolder holder, int position) {
        //TaiLieu item_trangChu = mListItem.get(position);
        TaiLieu item_trangChu = mListItem.get(position);
        if (item_trangChu == null)
        {
            return;
        }
        //holder.imgBook.setImageResource(Integer.parseInt(item_trangChu.getHinh()));
        holder.tvBook.setText(item_trangChu.getTenSach());
        holder.rec_item.setOnClickListener(new View.OnClickListener() {
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

    public class CustomGridViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBook;
        private LinearLayout rec_item;
        public CustomGridViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBook = itemView.findViewById(R.id.item_timkiem);
            rec_item = itemView.findViewById(R.id.rec_tensach);
        }

    }
}
