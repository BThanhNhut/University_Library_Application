package com.trangchu_user.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Activity_TimKiemSach;
import com.trangchu_user.Adapter.CustomGridAdapter_TrangChu;
import com.trangchu_user.Class.Item_TrangChu;
import com.trangchu_user.Class.TaiLieu;
import com.trangchu_user.R;
import com.trangchu_user.TheLoai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TrangChuFragment extends Fragment {

    List<TaiLieu> mlistTaiLieu;
    CardView cv_TinHoc, cv_NgoaiNgu, cv_KeToan, cv_SinhHoc, cv_DetMay, cv_DuLich;
    private RecyclerView recyclerView;
    private Context context;
    private CustomGridAdapter_TrangChu customGridAdapter_trangChu;
    private SearchView searchView;
    private TextView  tvtl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_trang_chu, container, false);
        recyclerView = rootView.findViewById(R.id.rec_trangchu);
        searchView = rootView.findViewById(R.id.searchView);
        tvtl = rootView.findViewById(R.id.tv_all_tl);
        searchView.clearFocus();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);


        recyclerView.setAdapter(customGridAdapter_trangChu);
        mlistTaiLieu = new ArrayList<>();
        customGridAdapter_trangChu = new CustomGridAdapter_TrangChu(mlistTaiLieu);
        recyclerView.setAdapter(customGridAdapter_trangChu);
        layDL();

        tvtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TheLoai.class);
                getActivity().startActivity(intent);
            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent searchIntent=new Intent(getActivity(), Activity_TimKiemSach.class);
                getActivity().startActivity(searchIntent);
            }
        });
        cv_TinHoc = rootView.findViewById(R.id.cardView_TinHoc);
        cv_NgoaiNgu = rootView.findViewById(R.id.cardView_NgoaiNgu);
        cv_KeToan = rootView.findViewById(R.id.cardView_KeToan);
        cv_SinhHoc = rootView.findViewById(R.id.cardView_SinhHoc);
        cv_DetMay = rootView.findViewById(R.id.cardView_DetMay);
        cv_DuLich = rootView.findViewById(R.id.cardView_DuLich);

        cv_TinHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);
//                intent.putExtra("number","1");


                getActivity().startActivity(intent);

            }
        });
        cv_NgoaiNgu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);

                getActivity().startActivity(intent);
            }
        });
        cv_KeToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);

                getActivity().startActivity(intent);
            }
        });
        cv_SinhHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);
                getActivity().startActivity(intent);
            }
        });
        cv_DetMay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);
                getActivity().startActivity(intent);
            }
        });
        cv_DuLich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TheLoai.class);
                getActivity().startActivity(intent);
            }
        });

        return rootView;


    }

    private void layDL() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRec = database.getReference("TaiLieu");

        myRec.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (mlistTaiLieu != null)
                {
                    mlistTaiLieu.clear();
                }
                int i = 0;
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {

                    String tenHinh = dataSnapshot.child("hinh").getValue(String.class);
                    String tenSach = dataSnapshot.child("tenSach").getValue(String.class);
                    int id = dataSnapshot.child("id").getValue(Integer.class);
                    int namXB = dataSnapshot.child("namXB").getValue(Integer.class);
                    String mota = dataSnapshot.child("moTa").getValue(String.class);
                    int soLuong = dataSnapshot.child("soLuong").getValue(Integer.class);
                    String tacGia = dataSnapshot.child("tacGia").getValue(String.class);
                    int id_Loai = dataSnapshot.child("id_Loai").getValue(Integer.class);
                    int id_NXB = dataSnapshot.child("id_NXB").getValue(Integer.class);
                    String tenNXB = dataSnapshot.child("tenNXB").getValue(String.class);
                    Integer hinh = getResources().getIdentifier(tenHinh,"drawable", getActivity().getPackageName());

                    String ha = ""+hinh;
                    TaiLieu tl = new TaiLieu(id, tenSach, namXB, soLuong, tacGia, ha, mota, id_Loai, id_NXB, tenNXB);
                    mlistTaiLieu.add(tl);
                    i++;
                    if (i == 12)
                    {
                        break;
                    }
                }
                customGridAdapter_trangChu.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Lỗi lấy dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });

    }

}