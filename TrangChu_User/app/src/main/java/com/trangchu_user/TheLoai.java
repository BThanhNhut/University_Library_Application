package com.trangchu_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.adapters.AdapterViewBindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trangchu_user.Adapter.AdapterViewPager;
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


public class TheLoai extends AppCompatActivity {
    TabLayout tabLayout;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    String tendg = "";
    String madg = "";
    DatabaseReference database;
    ArrayList<DocGia> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        tabLayout = (TabLayout) findViewById(R.id.tab_theloai);
        replaceFrag(new AllFragment());
        Intent intent = getIntent();
        madg = intent.getStringExtra("madg");
        System.out.println("t mún đi ngủ"+madg);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(@NonNull TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:

                        replaceFrag(new AllFragment());

                        break;
                    case 1:
                        replaceFrag(new TinHocFragment());
                        break;
                    case 2:
                        replaceFrag(new NgoaiNguFragment());
                        break;
                    case 3:
                        replaceFrag(new KeToanFragment());
                        break;
                    case 4:
                        replaceFrag(new DuLichFragment());
                        break;
                    case 5:
                        replaceFrag(new DetMayFragment());
                        break;
                    case 6:
                        replaceFrag(new SinhHocFragment());
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });

//        goiTab_CardView();
        //backTheLoai();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.item1);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        return super.onCreateOptionsMenu(menu);
    }
    public void replaceFrag(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragment);
        fragmentTransaction.commit();
    }



}