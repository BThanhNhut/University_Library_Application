package com.trangchu_user;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Adapter.AdapterViewPager;
import com.trangchu_user.Adapter.LoadAllAdapter;
import com.trangchu_user.Class.DocGia;
import com.trangchu_user.Fragment.AllFragment;
import com.trangchu_user.Fragment.DetMayFragment;
import com.trangchu_user.Fragment.DuLichFragment;
import com.trangchu_user.Fragment.GioSachFragment;
import com.trangchu_user.Fragment.KeToanFragment;
import com.trangchu_user.Fragment.NgoaiNguFragment;
import com.trangchu_user.Fragment.SinhHocFragment;
import com.trangchu_user.Fragment.TaiKhoanFragment;
import com.trangchu_user.Fragment.TinHocFragment;
import com.trangchu_user.Fragment.TrangChuFragment;
import com.trangchu_user.Fragment.YeuThichFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager2 pagerMain;
    BottomNavigationView bottomNavigationView;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    String tendg = "";
    Integer madg = 0;
    DatabaseReference database;
    ArrayList<DocGia> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        addControls();

        database = FirebaseDatabase.getInstance().getReference().child("DocGia");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Integer id = dataSnapshot.child("id").getValue(Integer.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String gioitinh = dataSnapshot.child("gioiTinh").getValue(String.class);
                    String tendg = dataSnapshot.child("tenDG").getValue(String.class);
                    String username = dataSnapshot.child("username").getValue(String.class);
                    System.out.println("22/5/2023: " + id + " -  " + email + " - " + gioitinh + " - " + tendg + " - " + username );
                    DocGia d = new DocGia(id, tendg, gioitinh, email, username);
                    list.add(d);
                }
                System.out.println(list.size());
                tudongDangNhap();
                System.out.println("- " + tendg + " + " + madg);

                fragmentArrayList.add(new TrangChuFragment());
                fragmentArrayList.add(new YeuThichFragment(tendg, madg));
                fragmentArrayList.add(new GioSachFragment(tendg, madg));
                fragmentArrayList.add(new TaiKhoanFragment(tendg, madg));
                AdapterViewPager adapterViewPager = new AdapterViewPager(MainActivity.this, fragmentArrayList);
                pagerMain.setAdapter(adapterViewPager);
                bottomNavigationView.setSelectedItemId(R.id.tab_trangchu);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        pagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.tab_trangchu);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.tab_yeuthich);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.tab_giosach);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.tab_taikhoan);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.tab_trangchu: {
                    //replaceFragment(new TrangChuFragment());
                    pagerMain.setCurrentItem(0);
                    break;
                }
                case R.id.tab_yeuthich: {
                    //replaceFragment(new GioSachFragment());
                    pagerMain.setCurrentItem(1);
                    break;
                }
                case R.id.tab_giosach:
                    //replaceFragment(new TaiKhoanFragment());
                    pagerMain.setCurrentItem(2);
                    break;
                case R.id.tab_taikhoan:
                    //replaceFragment(new YeuThichFragment());
                    pagerMain.setCurrentItem(3);
                    break;
                default:
                    //replaceFragment(new TrangChuFragment());
                    pagerMain.setCurrentItem(0);

            }
            return true;
        });
        checkBack();
    }

    private void checkBack()
    {
        String so = getIntent().getStringExtra("number");
        if (so != null)
        {
            if (Integer.parseInt(so) == 10)
            {
                pagerMain.setCurrentItem(0);
                bottomNavigationView.getMenu().findItem(R.id.tab_trangchu).setChecked(true);
            }
            else {
                pagerMain.setCurrentItem(3);
                bottomNavigationView.getMenu().findItem(R.id.tab_taikhoan).setChecked(true);
            }
        }
    }

    public void addControls()
    {
        pagerMain = (ViewPager2) findViewById(R.id.pagerMain);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
    }

    private void tudongDangNhap()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("tk_mk", MODE_PRIVATE);
        String username = sharedPreferences.getString("Username", "");
        String password = sharedPreferences.getString("Password", "");
        if(username.length() != 0 && password.length() != 0) {
            System.out.println(username);
            System.out.println("Thao 1: " + list.size());
            for(DocGia i: list)
            {
                if(i.getUsername().equals(username))
                {
                    System.out.println("Thao 2: " + i.getTenDG() + i.getID());
                    tendg = i.getTenDG();
                    madg = i.getID();
                }
            }
        }
    }
}