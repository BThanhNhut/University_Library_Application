package com.example.app_thuthu;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new MuonSachFragment();
            case 1:
                return new TraSachFragment();
            case 2:
                return new TaiKhoanFragment();
            default:
                return new MuonSachFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
}
