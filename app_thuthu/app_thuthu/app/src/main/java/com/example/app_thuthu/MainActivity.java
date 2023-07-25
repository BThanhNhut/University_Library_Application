package com.example.app_thuthu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;
    BottomNavigationView bottom_navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_navigation = findViewById(R.id.bottom_navigation);
        viewPager2 = findViewById(R.id.view_pager);

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);

        checkBack();


        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.muonsach:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.trasach:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.taikhoan:
                        viewPager2.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottom_navigation.getMenu().findItem(R.id.muonsach).setChecked(true);
                        break;
                    case 1:
                        bottom_navigation.getMenu().findItem(R.id.trasach).setChecked(true);
                        break;
                    case 2:
                        bottom_navigation.getMenu().findItem(R.id.taikhoan).setChecked(true);
                        break;
                }
                super.onPageSelected(position);
            }
        });
    }

    private void checkBack () {
        String so = getIntent().getStringExtra("number");
        if( so != null) {
            System.out.println("so la = " + so);
            if (Integer.parseInt(so) == 0)
                viewPager2.setCurrentItem(0);
            else if (Integer.parseInt(so) == 1)
                viewPager2.setCurrentItem(1);
            else if (Integer.parseInt(so) == 2)
                viewPager2.setCurrentItem(2);
        }
    }


}