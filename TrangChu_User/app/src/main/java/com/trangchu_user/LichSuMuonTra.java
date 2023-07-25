package com.trangchu_user;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.trangchu_user.Fragment.DSMuonFragment;
import com.trangchu_user.Fragment.DangXuLyFragment;
import com.trangchu_user.Fragment.TaiKhoanFragment;
import com.trangchu_user.Fragment.TraSachFragment;

public class LichSuMuonTra extends AppCompatActivity {
    TabLayout tab_ds_muon;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_muon_tra);
        getSupportActionBar().hide();
        addControls();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new DangXuLyFragment()).commit();
        tab_ds_muon.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new DangXuLyFragment()).commit();
                        break;
                    case 1:


                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new DSMuonFragment()).commit();
//                        replaceFrag(new DSMuonFragment());
                        break;
                    case 2:


                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new TraSachFragment()).commit();
                        //replaceFrag(new TraSachFragment());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private void addControls()
    {
        tab_ds_muon = (TabLayout) findViewById(R.id.tab_ds_muon);
    }

    public void replaceFrag(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }
}
