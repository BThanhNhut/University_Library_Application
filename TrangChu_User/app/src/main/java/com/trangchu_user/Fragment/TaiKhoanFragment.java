package com.trangchu_user.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trangchu_user.Activity_ThongTinDocGia;
import com.trangchu_user.DangNhap;
import com.trangchu_user.GioiThieu;
import com.trangchu_user.LichSuMuonTra;
import com.trangchu_user.Nhut_menu;
import com.trangchu_user.R;

import java.io.File;

public class TaiKhoanFragment extends Fragment {

    public TaiKhoanFragment(String tendg, Integer madg) {
        this.tendg = tendg;
        this.madg = madg;
    }
    String tendg;
    Integer madg;
    Button btn_xemthongtin;
    TextView txt_tendocgia;
    RelativeLayout view_lichsumuontra, view_thongke, view_caidat, view_gthieu, view_thoat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_tai_khoan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls(view);
        if (tendg.length() == 0)
        {
            txt_tendocgia.setText("Tài khoản");
            btn_xemthongtin.setText("Đăng nhập");
        }
        else
        {
            txt_tendocgia.setText(tendg);
            btn_xemthongtin.setText("Xem thông tin");
        }

        view_lichsumuontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(btn_xemthongtin.getText().equals("Đăng nhập"))
//                {
//                    Intent intent = new Intent();
//                    intent.setClass(getContext(), DangNhap.class);
//                    startActivity(intent);
//                }
//                else{
                    Intent intent = new Intent();
                    intent.setClass(getContext(), LichSuMuonTra.class);
                    startActivity(intent);
                }
//            }
        });
        view_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_xemthongtin.getText().equals("Đăng nhập"))
                {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), DangNhap.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), Nhut_menu.class);
                    startActivity(intent);
                }
            }
        });
        view_gthieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getContext(), GioiThieu.class);
                startActivity(intent);
            }
        });
        btn_xemthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_xemthongtin.getText().equals("Đăng nhập"))
                {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), DangNhap.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent();
                    intent.setClass(getContext(), Activity_ThongTinDocGia.class);
                    intent.putExtra("madg", String.valueOf(madg));
                    startActivity(intent);
                }
            }
        });
        view_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File("/data/data/com.trangchu_user/shared_prefs/tk_mk.xml");
                if(file.exists())
                {
                    file.delete();
                }
                Intent intent = new Intent();
                intent.setClass(getContext(), DangNhap.class);
                startActivity(intent);
            }
        });
    }

    private void addControls(View view)
    {
        btn_xemthongtin = view.findViewById(R.id.btn_xemthongtin);
        view_caidat = view.findViewById(R.id.view_caidat);
        view_lichsumuontra = view.findViewById(R.id.view_lichsumuontra);
        view_thoat = view.findViewById(R.id.view_thoat);
        view_thongke = view.findViewById(R.id.view_thongke);
        view_gthieu = view.findViewById(R.id.view_gioithieu);
        txt_tendocgia = view.findViewById(R.id.txt_tendocgia);
    }

}