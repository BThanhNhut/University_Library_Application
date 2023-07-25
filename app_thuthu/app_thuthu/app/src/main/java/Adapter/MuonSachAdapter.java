package Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_thuthu.ChiTietMuonSach;
import com.example.app_thuthu.R;

import Class.MuonSachClass;
import java.util.ArrayList;

public class MuonSachAdapter extends RecyclerView.Adapter<MuonSachAdapter.ViewHolder>{
    String mamuonsach = "";
    private ArrayList<MuonSachClass> list;

    public MuonSachAdapter(ArrayList<MuonSachClass> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.thongtinmuonsach, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MuonSachClass d = list.get(position);

        if(d.getMamuon() > 9)
        {
            mamuonsach = "MT0" + d.getMamuon().toString();
        }
        else
        {
            mamuonsach = "MT00" + d.getMamuon().toString();
        }
        holder.txt_mamuon.setText(mamuonsach);
        holder.txt_docgia.setText("Độc giả: " + d.getTendocgia());
        holder.txt_sosach.setText(d.getSosachmuon().toString() + " cuốn sách");

        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(),ChiTietMuonSach.class);
                intent.putExtra("mamuon",d.getMamuon().toString());
                intent.putExtra("tendg",d.getTendocgia());
                view.getContext().startActivity(intent);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txt_mamuon, txt_docgia, txt_sosach;
        public RelativeLayout select;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_mamuon = itemView.findViewById(R.id.txt_mamuon);
            txt_docgia = itemView.findViewById(R.id.txt_docgia);
            txt_sosach = itemView.findViewById(R.id.txt_sosach);

            select = (RelativeLayout) itemView.findViewById(R.id.select);

        }
    }
    // su kien click



}
