package com.example.app_thuthu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapter.MuonSachAdapter;
import Adapter.TaiKhoanAdapter;
import Class.TaiKhoanClass;

public class TaiKhoanFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<TaiKhoanClass> list = new ArrayList<TaiKhoanClass>();
    DatabaseReference database;
    DatabaseReference docgia;
    TaiKhoanAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tai_khoan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_taikhoan);
        layoutRecyclerView(view);

        loadTaiKhoan();
    }

    private void loadTaiKhoan()
    {
        database = FirebaseDatabase.getInstance().getReference().child("TaiKhoan");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(list != null)
                {
                    list.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Boolean tinhtrang =dataSnapshot.child("tinhtrang").getValue(Boolean.class);
                    if(!tinhtrang) {
                        Integer id_dg = dataSnapshot.child("id_DG").getValue(Integer.class);
                        String password = dataSnapshot.child("password").getValue(String.class);
                        String username = dataSnapshot.child("username").getValue(String.class);
                        TaiKhoanClass a = new TaiKhoanClass("", username, password, tinhtrang, id_dg);
                        list.add(a);
                    }
                }
                System.out.println(list.size());
                loadDocGia();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadDocGia()
    {
        docgia = FirebaseDatabase.getInstance().getReference().child("DocGia");
        docgia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Integer id_dg = dataSnapshot.child("id").getValue(Integer.class);
                    for(TaiKhoanClass i: list)
                    {
                        if(id_dg == i.getMadocgia())
                        {
                            i.setTendocgia(dataSnapshot.child("tenDG").getValue(String.class));
                        }
                    }
                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void layoutRecyclerView(View view)
    {
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        adapter = new TaiKhoanAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}