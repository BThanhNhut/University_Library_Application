package com.example.app_thuthu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import Class.MuonSachClass;
import Class.DocGiaClass;

public class MuonSachFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference muontra;
    DatabaseReference docgia;
    DatabaseReference chitietmuontra;
    ArrayList<MuonSachClass> list_mt = new ArrayList<MuonSachClass>();
    ArrayList<DocGiaClass> list_dg = new ArrayList<>();
    String tendocgia = "";
    MuonSachAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_muon_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_muonsach);
        layoutRecyclerView(view);
        loadDocGia();
        loadMuonTra();
        loadSoSachMuon();
    }

    private void loadMuonTra()
    {
        muontra = FirebaseDatabase.getInstance().getReference().child("MuonTra");

        muontra.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(list_mt != null)
                {
                    list_mt.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Boolean tinhtrangtra =dataSnapshot.child("tinhTrang").getValue(Boolean.class);
                    if(!tinhtrangtra) {
                        Integer mamuon = dataSnapshot.child("id").getValue(Integer.class);
                        Integer madocgia = dataSnapshot.child("id_DG").getValue(Integer.class);
                        String ngaymuon = dataSnapshot.child("ngayMuon").getValue(String.class);
                        for(DocGiaClass i: list_dg)
                        {
                            if(i.getId().equals(madocgia))
                            {
                                tendocgia = i.getTendocgia();
                            }
                        }
                        MuonSachClass a = new MuonSachClass(mamuon, madocgia, tendocgia, 0);
                        list_mt.add(a);
                    }
                }
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
                if(list_dg != null)
                {
                    list_dg.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String gioitinh = dataSnapshot.child("gioiTinh").getValue(String.class);
                    Integer madocgia = dataSnapshot.child("id").getValue(Integer.class);
                    String tendocgia = dataSnapshot.child("tenDG").getValue(String.class);
                    String username = dataSnapshot.child("username").getValue(String.class);
                    DocGiaClass dg = new DocGiaClass(email, gioitinh, tendocgia, username,madocgia);
                    list_dg.add(dg);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadSoSachMuon()
    {
        chitietmuontra = FirebaseDatabase.getInstance().getReference().child("ChiTietMuonTra");
        chitietmuontra.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Integer mamuon = dataSnapshot.child("id_MT").getValue(Integer.class);
                    for(MuonSachClass i: list_mt)
                    {
                        if(i.getMamuon().equals(mamuon))
                        {
                            i.setSosachmuon(i.getSosachmuon() + 1);
                        }
                    }
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void layoutRecyclerView(View view)
    {
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        adapter = new MuonSachAdapter(list_mt);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}