package com.example.app_thuthu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import Adapter.TaiKhoanAdapter;
import Class.TaiKhoanClass;

public class TaiKhoanFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<TaiKhoanClass> list = new ArrayList<TaiKhoanClass>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tai_khoan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addData();

        recyclerView = view.findViewById(R.id.recyclerview_taikhoan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        TaiKhoanAdapter adapter = new TaiKhoanAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void addData()
    {
        TaiKhoanClass muonSachClass = new TaiKhoanClass("Ho Phuong Thao","hphthao1705@gmail.com");
        list.add(muonSachClass);
        TaiKhoanClass muonSachClass1 = new TaiKhoanClass("Bien Thanh Nhut", "hahahaa");
        list.add(muonSachClass1);
    }
}