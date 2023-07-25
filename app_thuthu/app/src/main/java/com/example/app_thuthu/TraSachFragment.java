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
import Adapter.TraSachAdapter;
import Class.TraSachClass;
import Class.ChiTietMuonTra;
import Class.DocGiaClass;

public class TraSachFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<TraSachClass> list = new ArrayList<TraSachClass>();
    ArrayList<ChiTietMuonTra> lst_ctmt = new ArrayList<>();
    ArrayList<TraSachClass> lst_trasach = new ArrayList<>();
    ArrayList<DocGiaClass> lst_dg = new ArrayList<>();
    TraSachAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tra_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview_trasach);
        layoutRecyclerView(view);
        loadChiTietMuon();
        loadDocGia();
        loadTraSach();

    }

    private void loadChiTietMuon ()
    {
        DatabaseReference node = FirebaseDatabase.getInstance().getReference().child("ChiTietMuonTra");
        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    int id_MT = dataSnapshot.child("id_MT").getValue(Integer.class);
                    int id_Sach = dataSnapshot.child("id_Sach").getValue(Integer.class);
                    String tinhTrangSach = dataSnapshot.child("tinhTrangSach").getValue(String.class);
                    Boolean tinhTrangTra = dataSnapshot.child("tinhTrangTra").getValue(Boolean.class);
                    String ngayTra = dataSnapshot.child("ngayTra").getValue(String.class);
                    if(ngayTra == null)
                    {
                        ngayTra ="0/0/0";
                    }
                    ChiTietMuonTra ctmt = new ChiTietMuonTra(ngayTra,tinhTrangSach,tinhTrangTra,id_MT,id_Sach);
                    lst_ctmt.add(ctmt);
                }
                System.out.println("So luong chi tiet muon sach la = "+ lst_ctmt.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadDocGia()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference node = firebaseDatabase.getReference("DocGia");

        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(lst_dg != null)
                {
                    lst_dg.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String gioitinh = dataSnapshot.child("gioiTinh").getValue(String.class);
                    Integer madocgia = dataSnapshot.child("id").getValue(Integer.class);
                    String tendocgia = dataSnapshot.child("tenDG").getValue(String.class);
                    String username = dataSnapshot.child("username").getValue(String.class);
                    DocGiaClass dg = new DocGiaClass(email, gioitinh, tendocgia, username,madocgia);
                    lst_dg.add(dg);
                }
                System.out.println("So luong doc gia la "+ lst_dg.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void loadTraSach ()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference node = firebaseDatabase.getReference("MuonTra");

        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    boolean tinhTrang = dataSnapshot.child("tinhTrang").getValue(boolean.class);
                    System.out.println("Tinh trạng là " + tinhTrang);
                    if(tinhTrang)
                    {
                        String tendocgia = "";
                        int slm = 0, slt = 0;
                        int id = dataSnapshot.child("id").getValue(Integer.class);
                        int id_dg = dataSnapshot.child("id_DG").getValue(Integer.class);
                        for(DocGiaClass dg : lst_dg)
                        {
                            if(dg.getId() == id_dg)
                            {
                                tendocgia = dg.getTendocgia();
                            }
                        }
                        for(ChiTietMuonTra ctmt : lst_ctmt)
                        {
                            if(ctmt.getID_MT() == id && ctmt.isTinhTrangTra() == false)
                            {
                                slm++;
                            }
                            if(ctmt.getID_MT() == id && ctmt.isTinhTrangTra() == true)
                            {
                                slt++;
                            }
                        }
                        TraSachClass ts = new TraSachClass(id,tendocgia,slm,slt);
                        lst_trasach.add(ts);
                    }
                }
                System.out.println("So luong load sach la = "+ lst_ctmt.size());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    private void layoutRecyclerView(View view)
    {
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        adapter = new TraSachAdapter(lst_trasach);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}