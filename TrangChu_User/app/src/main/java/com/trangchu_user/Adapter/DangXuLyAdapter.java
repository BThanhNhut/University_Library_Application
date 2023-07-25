package com.trangchu_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trangchu_user.Class.MuonTra;
import com.trangchu_user.Fragment.DSMuonFragment;
import com.trangchu_user.R;

import java.util.List;

public class DangXuLyAdapter extends RecyclerView.Adapter<DangXuLyAdapter.MuonViewHolder> {
    private final List<MuonTra> dsMuon;
    String mamuonsach = "";
    private final Context context;
    public DangXuLyAdapter(List<MuonTra> dsMuon, Context context)
    {
        this.dsMuon = dsMuon;
        this.context = context;
    }

    @NonNull
    @Override
    public MuonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dang_xu_ly, null);
        return new MuonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MuonViewHolder holder , int position)
    {
        MuonTra muonTra = dsMuon.get(position);
        if(muonTra.getID() > 9)
        {
            mamuonsach = "MT0" + String.valueOf(muonTra.getID())+ "                                             ";
        }
        else
        {
            mamuonsach = "MT00" + String.valueOf(muonTra.getID()) + "                                           ";
        }

        holder.mamuon.setText(mamuonsach);
        holder.ngaymuon.setText("Ngày mượn: " + muonTra.getNgayMuon());
    }

    @Override
    public int getItemCount()
    {
        return dsMuon.size();
    }

    class MuonViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView mamuon, ngaymuon;

        public MuonViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mamuon = itemView.findViewById(R.id.mamuon);
            ngaymuon = itemView.findViewById(R.id.ngaymuon);
        }
    }
}
