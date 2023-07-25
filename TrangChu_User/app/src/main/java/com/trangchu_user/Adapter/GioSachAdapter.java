package com.trangchu_user.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.trangchu_user.Chi_Tiet_Sach;
import com.trangchu_user.Class.GioSach;
import com.trangchu_user.Class.GioSachMuon;
import com.trangchu_user.Class.TaiLieu;
import com.trangchu_user.Class.YeuThich;
import com.trangchu_user.R;

import java.util.ArrayList;

public class GioSachAdapter extends RecyclerView.Adapter<GioSachAdapter.MyViewHolder> {

    ArrayList<GioSach> lst;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("GioSach");
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mai_layout_giosach, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GioSach d = lst.get(position);
        holder.tensach.setText("Tên sách: " + d.getTenSach());
        holder.tacgia.setText("" + d.getTacGia());
        holder.img.setImageResource(Integer.parseInt(d.getHinh().trim()));
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    GioSachMuon.mangmuon.add(d);
                }
                else
                {
                    for(int i=0; i<GioSachMuon.mangmuon.size();i++)
                    {
                        if(GioSachMuon.mangmuon.get(i).getId_Sach() == d.getId_Sach())
                        {
                            GioSachMuon.mangmuon.remove(i);
                        }
                    }
                }
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), Chi_Tiet_Sach.class);
                intent.putExtra("id", ""+d.getId_Sach());
                view.getContext().startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext()).setTitle("Thông báo").setMessage("Bạn có chắc chắn muôn xóa?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.child(""+d.getId()).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(view.getContext(), "Delete successfully", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).setNegativeButton("Cancel",null).show();



            }
        });

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tensach, tacgia;
        ImageView img, delete;
        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tensach = itemView.findViewById(R.id.tvtensach);
            tacgia = itemView.findViewById(R.id.tvtacgia);
            checkBox = itemView.findViewById(R.id.checkBox);
            img = itemView.findViewById(R.id.imgSach);
            delete = itemView.findViewById(R.id.imageView4);


        }
    }


    @Override
    public int getItemCount() {
        return lst.size();
    }
    public GioSachAdapter(ArrayList<GioSach> newArray) {
        lst = newArray;
    }
}
