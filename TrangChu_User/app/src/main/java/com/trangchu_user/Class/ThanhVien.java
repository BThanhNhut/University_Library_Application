package com.trangchu_user.Class;

public class ThanhVien {
    String ten;
    int hinh;
    String congviec;
    String danhgia;
    int mau;

    public ThanhVien(String ten, int hinh, String congviec, String danhgia, int mau) {
        this.ten = ten;
        this.hinh = hinh;
        this.congviec = congviec;
        this.danhgia = danhgia;
        this.mau = mau;
    }

    public void setMau(int mau) {
        this.mau = mau;
    }

    public int getMau() {
        return mau;
    }

    public String getTen() {
        return ten;
    }

    public int getHinh() {
        return hinh;
    }

    public String getCongviec() {
        return congviec;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public void setCongviec(String congviec) {
        this.congviec = congviec;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }
}
