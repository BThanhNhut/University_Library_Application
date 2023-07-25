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


public class ChiTietTraSach extends AppCompatActivity {
    TextView txt_maphieumuon, txt_tendocgia;
    ListView listview_trasach;
    List<CTTraSachClass> List;
    CTTraSachAdapter adapter;

    Button btn_tra;
    ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tra_sach);
        img_back = findViewById(R.id.img_back);
        txt_maphieumuon = findViewById(R.id.txt_maphieumuon_2);
        listview_trasach = findViewById(R.id.listview_trasach);
        txt_tendocgia = findViewById(R.id.txt_tendocgia_2);
        btn_tra = findViewById(R.id.btn_tra);

        loadDuLieu();
        System.out.println("So ban la " + List.size());
        adapter = new CTTraSachAdapter(this,R.layout.row_ct_tra_sach,List);
        System.out.println(adapter);
        listview_trasach.setAdapter(adapter);

        btn_tra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

    private void loadDuLieu()
    {
        List = new ArrayList<>();
        List.add(new CTTraSachClass(String.valueOf(R.drawable.hinh_ss),"Phải lòng với cô đơn ",2012,"Biện Thanh Nhựt",false));
        List.add(new CTTraSachClass(String.valueOf(R.drawable.hinh_ss),"Phải lòng với cô đơn ",2012,"Biện Thanh Nhựt",false));
        List.add(new CTTraSachClass(String.valueOf(R.drawable.hinh_ss),"Phải lòng với cô đơn ",2012,"Biện Thanh Nhựt",false));
        List.add(new CTTraSachClass(String.valueOf(R.drawable.hinh_ss),"Phải lòng với cô đơn ",2012,"Biện Thanh Nhựt",false));
    }
}