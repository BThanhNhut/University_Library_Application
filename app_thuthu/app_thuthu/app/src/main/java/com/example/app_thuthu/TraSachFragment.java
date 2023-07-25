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

import Adapter.MuonSachAdapter;;

import Adapter.TraSachAdapter;
import Class.MuonTra;
import Class.ChiTietMuonTra;
import Class.TaiLieu;
import Class.DocGiaClass;

public class TraSachFragment extends Fragment {
    DatabaseReference muontra;
    DatabaseReference docgia;
    RecyclerView recyclerView;
    ArrayList<MuonTra> lst_mt = new ArrayList<>();
    ArrayList<ChiTietMuonTra> lst_ctmt = new ArrayList<>();
    ArrayList<TaiLieu> lst_tl = new ArrayList<>();
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
        System.out.println("Chay nek");
        layoutRecyclerView(view);
        loadMuonTra();
        loadChiTietMuon();
        loadTaiLieu();
        loadDocGia();
        System.out.println("Ket thuc");

    }

    private void loadMuonTra ()
    {
        muontra = FirebaseDatabase.getInstance().getReference().child("MuonTra");

        muontra.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(lst_mt != null)
                {
                    lst_mt.clear();
                }
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    int id_dg = dataSnapshot.child("id_DG").getValue(Integer.class);
                    int id = dataSnapshot.child("id").getValue(Integer.class);
                    String ngayMuon = dataSnapshot.child("ngayMuon").getValue(String.class);
                    Boolean tinhTrang = dataSnapshot.child("tinhTrang").getValue(Boolean.class);
                    MuonTra mt = new MuonTra(id,ngayMuon,tinhTrang,id_dg);
                    lst_mt.add(mt);
                    System.out.println("Dai muon tra :" + lst_mt.size());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("loi "+ error);
            }
        });
    }
    private void loadChiTietMuon ()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference node = firebaseDatabase.getReference("ChiTietMuonTra");
        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(lst_ctmt != null)
                {
                    lst_ctmt.clear();
                }
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
                System.out.println("Dai chi tiet muon : "+ lst_ctmt.size());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void loadTaiLieu ()
    {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference node = firebaseDatabase.getReference("TaiLieu");
        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(lst_tl != null)
                {
                    lst_tl.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    int id = dataSnapshot.child("id").getValue(Integer.class);
                    String tenSach = dataSnapshot.child("tenSach").getValue(String.class);
                    String tacGia = dataSnapshot.child("tacGia").getValue(String.class);
                    String hinh = dataSnapshot.child("hinh").getValue(String.class);
                    int IdHinh = getResources().getIdentifier(hinh,"drawable",getActivity().getPackageName());

                    TaiLieu taiLieu = new TaiLieu(id,tenSach,tacGia,String.valueOf(IdHinh));
                    lst_tl.add(taiLieu);
                }
                System.out.println("Dai chi tiet muon : "+ lst_ctmt.size());
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
                    System.out.println("Chay dc");
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
        adapter = new TraSachAdapter(lst_mt);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}