package com.example.app_thuthu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import Adapter.CTMuonSachAdapter;
import Adapter.CTTraSachAdapter;
import Adapter.TraSachAdapter;
import Class.CTTraSachClass;
import Class.TaiLieuClass;

public class ChiTietTraSach extends AppCompatActivity {
    TextView txt_maphieumuon, txt_tendocgia;
    ListView listview_trasach;
    ArrayList<CTTraSachClass> List = new ArrayList<>();
    ArrayList<TaiLieuClass> lst_tl = new ArrayList<>();
    CTTraSachAdapter adapter;
    DatabaseReference Chitietmuontra = FirebaseDatabase.getInstance().getReference().child("ChiTietMuonTra");
    DatabaseReference TaiLieu = FirebaseDatabase.getInstance().getReference().child("TaiLieu");

    Button btn_tra;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tra_sach);
        // ánh xạ
        img_back = findViewById(R.id.img_back);
        txt_maphieumuon = findViewById(R.id.txt_maphieumuon_2);
        listview_trasach = findViewById(R.id.listview_trasach);
        txt_tendocgia = findViewById(R.id.txt_tendocgia_2);
        btn_tra = findViewById(R.id.btn_tra);
        //doc gia tri truyen
        Integer matra = Integer.parseInt(getIntent().getStringExtra("matra"));
        String tendocgia = getIntent().getStringExtra("tendg");
        System.out.println("Ma tra la = "+ matra);
        System.out.println("Ten doc gia la ="+ tendocgia);
        // set du lieu
        setText(matra,tendocgia);
        // load du lieu la
        adapter = new CTTraSachAdapter(this,R.layout.row_ct_tra_sach,List);
        listview_trasach.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        loadTaiLieu(matra);


        btn_tra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(CTTraSachClass i : List)
                {
                    if(i.isTinhtrang())
                    {
                        System.out.println("zo dc" + i.getId_sach());
                        System.out.println("zo dc" + i.getSl());
                        Chitietmuontra.child(String.valueOf(i.getNode())).child("tinhTrangTra").setValue(true);
                        TaiLieu.child(String.valueOf(i.getId_sach())).child("soLuong").setValue(i.getSl()+1);
                    }
                }
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),MainActivity.class);
                intent.putExtra("number","1");
                startActivity(intent);
            }
        });
    }

    private void setText(Integer mamuon, String tendocgia)
    {
        String phieumuonsach = "";
        txt_tendocgia.setText(tendocgia);
        if(mamuon > 9)
        {
            phieumuonsach = "MT0" + mamuon;
        }
        else
        {
            phieumuonsach = "MT00" + mamuon;
        }
        txt_maphieumuon.setText(phieumuonsach);
    }

    private void loadTaiLieu (int matra)
    {
        DatabaseReference node = FirebaseDatabase.getInstance().getReference().child("TaiLieu");
        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    int id = dataSnapshot.child("id").getValue(Integer.class);
                    String hinh = dataSnapshot.child("hinh").getValue(String.class);
                    int nxb = dataSnapshot.child("namXB").getValue(Integer.class);
                    String tacGia = dataSnapshot.child("tacGia").getValue(String.class);
                    String tenSach = dataSnapshot.child("tenSach").getValue(String.class);
                    int sl = dataSnapshot.child("soLuong").getValue(Integer.class);
                    System.out.println("So luong" + String.valueOf(sl));
                    TaiLieuClass tl = new TaiLieuClass(id,hinh,nxb,tacGia,tenSach, sl);
                    lst_tl.add(tl);
                }
                loadChiTietTraSach(matra);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void loadChiTietTraSach (int mat)
    {
        DatabaseReference node = FirebaseDatabase.getInstance().getReference().child("ChiTietMuonTra");
        node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(List != null)
                {
                    List.clear();
                }
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    int matra = dataSnapshot.child("id_MT").getValue(Integer.class);
                    int masach = dataSnapshot.child("id_Sach").getValue(Integer.class);
                    boolean tinhTrang = dataSnapshot.child("tinhTrangTra").getValue(Boolean.class);
                    if(matra == mat && tinhTrang == false)
                    {
                        for(TaiLieuClass tl : lst_tl)
                        {
                            if(masach == tl.getId())
                            {
                                System.out.println("Ket qua la ");
                                int maHinh = getResources().getIdentifier(tl.getHinh().trim(),"drawable",getPackageName());
                                String tenSach = tl.getTensach();
                                int namXuatBan = tl.getNxb();
                                String tenTG = tl.getTacGia();
                                tinhTrang = dataSnapshot.child("tinhTrangTra").getValue(Boolean.class);
                                String node = dataSnapshot.getKey();
                                System.out.println("Key la" + tinhTrang);
                                CTTraSachClass ts = new CTTraSachClass(String.valueOf(maHinh),tenSach,namXuatBan,tenTG,tinhTrang,node,masach,tl.getSl());
                                List.add(ts);
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}