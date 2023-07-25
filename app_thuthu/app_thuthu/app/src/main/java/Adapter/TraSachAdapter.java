package Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_thuthu.ChiTietTraSach;
import com.example.app_thuthu.R;

import java.util.ArrayList;

import Class.MuonTra;

public class TraSachAdapter extends RecyclerView.Adapter<TraSachAdapter.ViewHolder>{
    private ArrayList<MuonTra> list;
    public TraSachAdapter(ArrayList<MuonTra> list) {
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
        MuonTra d = list.get(position);
        holder.txt_mamuon.setText(d.getID());
        holder.txt_docgia.setText(d.getID_DG());
        holder.txt_sach.setText(d.getID());

        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), ChiTietTraSach.class);
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
        public TextView txt_mamuon, txt_docgia, txt_sach;
        public RelativeLayout select;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_mamuon = itemView.findViewById(R.id.txt_mamuon);
            txt_docgia = itemView.findViewById(R.id.txt_docgia);
            txt_sach = itemView.findViewById(R.id.txt_sach);

            select = (RelativeLayout) itemView.findViewById(R.id.select1);
        }
    }
}
