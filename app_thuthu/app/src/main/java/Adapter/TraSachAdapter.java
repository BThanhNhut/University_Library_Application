package Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_thuthu.ChiTietMuonSach;
import com.example.app_thuthu.ChiTietTraSach;
import com.example.app_thuthu.R;

import java.util.ArrayList;

import Class.TraSachClass;

public class TraSachAdapter extends RecyclerView.Adapter<TraSachAdapter.ViewHolder>{
    private ArrayList<TraSachClass> list;
    public TraSachAdapter(ArrayList<TraSachClass> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.thongtintrasach, parent, false);
        return new TraSachAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TraSachClass d = list.get(position);
        String mamuonsach;
        if(d.getId() > 9)
        {
            mamuonsach = "MT0" + d.getId();
        }
        else
        {
            mamuonsach = "MT00" + d.getId();
        }
        holder.txt_mamuon.setText(mamuonsach);
        holder.txt_docgia.setText(d.getTenDG() + "");
        holder.txt_slm.setText(d.getSlMuon() + "");
        holder.txt_slt.setText(d.getSlTra() + "");

        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), ChiTietTraSach.class);
                intent.putExtra("matra",String.valueOf(d.getId()));
                intent.putExtra("tendg",d.getTenDG());
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
        public TextView txt_mamuon, txt_docgia, txt_slm, txt_slt;
        public RelativeLayout select;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_mamuon = itemView.findViewById(R.id.txt_mamuon);
            txt_docgia = itemView.findViewById(R.id.txt_docgia);
            txt_slm = itemView.findViewById(R.id.txt_slm);
            txt_slt = itemView.findViewById(R.id.txt_slt);

            select = (RelativeLayout) itemView.findViewById(R.id.select1);
        }
    }
}
