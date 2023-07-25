package com.example.app_thuthu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.CTMuonSachAdapter;
import Class.CTMuonSachClass;

public class ChiTietMuonSach extends AppCompatActivity {
    TextView txt_maphieumuon, txt_tendocgia;
    ListView listview_muonsach;
    ArrayList<CTMuonSachClass> list_ctmt = new ArrayList<>();
    CTMuonSachAdapter adapter;
    Button btn_muon, btn_huy;
    ImageView img_back;
    DatabaseReference chitietmuontra;
    DatabaseReference sach;
    DatabaseReference muontra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_muon_sach);
        addControls();
        chitietmuontra = FirebaseDatabase.getInstance().getReference().child("ChiTietMuonTra");
        adapter = new CTMuonSachAdapter(ChiTietMuonSach.this,R.layout.row_ct_muon_sach,list_ctmt);
        //doc gia tri truyen
        Integer mamuon = Integer.parseInt(getIntent().getStringExtra("mamuon"));
        String tendocgia = getIntent().getStringExtra("tendg");
        //set du lieu
        setText(mamuon, tendocgia);
        //load ct muon sach
        loadCTMuonSach(mamuon);
        muontra = FirebaseDatabase.getInstance().getReference().child("MuonTra");

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),MainActivity.class);
                intent.putExtra("number","0");
                startActivity(intent);
            }
        });

        btn_muon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                muontra.child(String.valueOf(mamuon)).child("tinhTrang").setValue(true);
                for(CTMuonSachClass i: list_ctmt)
                {
                    sach.child(String.valueOf(i.getID_Sach())).child("soLuong").setValue(i.getSoluong() - 1);
                }
                Toast.makeText(ChiTietMuonSach.this, "Thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(CTMuonSachClass i: list_ctmt)
                {
                    chitietmuontra.child(i.getKey()).removeValue();
                }
                muontra.child(String.valueOf(mamuon)).removeValue();
                Toast.makeText(ChiTietMuonSach.this, "Thanh cong", Toast.LENGTH_SHORT).show();
            }
        });

            muontra.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //System.out.println(snapshot.getValue());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addControls()
    {
        txt_maphieumuon = findViewById(R.id.txt_maphieumuon);
        txt_tendocgia = findViewById(R.id.txt_tendocgia);
        listview_muonsach = findViewById(R.id.listview_muonsach);
        btn_muon = findViewById(R.id.btn_muon);
        btn_huy = findViewById(R.id.btn_huy);
        img_back = findViewById(R.id.img_back);
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

    private void loadCTMuonSach(Integer id_mt)
    {
        chitietmuontra.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(list_ctmt != null)
                {
                    list_ctmt.clear();
                }
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Integer mamuontra = dataSnapshot.child("id_MT").getValue(Integer.class);
                    if(mamuontra == id_mt)
                    {
                        Integer masach = dataSnapshot.child("id_Sach").getValue(Integer.class);
                        String tinhtrangSach = dataSnapshot.child("tinhTrangSach").getValue(String.class);
                        Boolean tinhtrangTra = dataSnapshot.child("tinhTrangTra").getValue(Boolean.class);
                        String key = dataSnapshot.getKey();
                        System.out.println("Key ne: " + key);
                        CTMuonSachClass a = new CTMuonSachClass(tinhtrangSach, tinhtrangTra, mamuontra, masach, "", "", 0, "", key, 0);
                        list_ctmt.add(a);
                    }
                }
                loadSach();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadSach()
    {
        sach = FirebaseDatabase.getInstance().getReference().child("TaiLieu");
        for(CTMuonSachClass i: list_ctmt)
        {
            sach.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                        Integer id_sach = dataSnapshot1.child("id").getValue(Integer.class);
                        if (i.getID_Sach() == id_sach)
                        {
                            i.setTensach(dataSnapshot1.child("tenSach").getValue(String.class));
                            String hinh = dataSnapshot1.child("hinh").getValue(String.class);
                            Integer id_hinh = getResources().getIdentifier(hinh, "drawable", getPackageName());
                            i.setHinh(id_hinh.toString());
                            i.setTacgia(dataSnapshot1.child("tacGia").getValue(String.class));
                            i.setNamxuatban(dataSnapshot1.child("namXB").getValue(Integer.class));
                            i.setSoluong(dataSnapshot1.child("soLuong").getValue(Integer.class));
                        }
                        listview_muonsach.setAdapter(adapter);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}