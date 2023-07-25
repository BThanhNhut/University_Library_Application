package com.trangchu_user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.trangchu_user.Adapter.ThanhVienAdapter;
import com.trangchu_user.Class.ThanhVien;

import java.util.ArrayList;

public class GioiThieu extends AppCompatActivity {
    RecyclerView recycler_view;
    ImageView img_back;
    ArrayList<ThanhVien> list = new ArrayList<ThanhVien>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);
        getSupportActionBar().hide();
        recycler_view = findViewById(R.id.recycler_view);
        img_back = findViewById(R.id.img_back);

        addData();

        recycler_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recycler_view.setHasFixedSize(true);

        ThanhVienAdapter adapter = new ThanhVienAdapter(list);
        recycler_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),MainActivity.class);
                intent.putExtra("number","3");
                startActivity(intent);
            }
        });

    }

    private void addData()
    {
        ThanhVien muonSachClass = new ThanhVien("Hồ Phương Thảo",R.drawable.thao, "App thủ thư, đăng nhập, đăng ký, mail", "100%",R.color.thao);
        list.add(muonSachClass);
        muonSachClass = new ThanhVien("Huỳnh Thị Kiều Mai",R.drawable.mai, "Thể loại, chi tiết sách, mượn sách", "100%", R.color.mai);
        list.add(muonSachClass);
        muonSachClass = new ThanhVien("Biện Thanh Nhựt",R.drawable.nhut, "App thủ thư, thống kê", "100%", R.color.nhut);
        list.add(muonSachClass);
        muonSachClass = new ThanhVien("Tống Duy Trường Đạt",R.drawable.dat, "Thông tin độc giả", "100%", R.color.dat);
        list.add(muonSachClass);
        muonSachClass = new ThanhVien("Lê Minh Kha",R.drawable.kha, "Lịch sử mượn trả", "100%", R.color.kha);
        list.add(muonSachClass);
        muonSachClass = new ThanhVien("Phan Thị Ánh Linh",R.drawable.linh, "Trang chủ", "100%", R.color.linh);
        list.add(muonSachClass);
    }
}