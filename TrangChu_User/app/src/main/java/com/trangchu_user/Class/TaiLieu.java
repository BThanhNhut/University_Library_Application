package com.trangchu_user.Class;

public class TaiLieu {
    private int ID;
    private String TenSach;
    private int NamXB;
    private int SoLuong;
    private String TacGia;
    private String Hinh;
    private String MoTa;
    private String tenNXB;

    private int ID_Loai;
    private int ID_NXB;

    public TaiLieu() {
    }

    public TaiLieu(int ID, String tenSach, int namXB, int soLuong, String tacGia, String hinh, String moTa, int ID_Loai, int ID_NXB, String ten_NXB) {
        this.ID = ID;
        TenSach = tenSach;
        NamXB = namXB;
        SoLuong = soLuong;
        TacGia = tacGia;
        Hinh = hinh;
        MoTa = moTa;
        this.ID_Loai = ID_Loai;
        this.ID_NXB = ID_NXB;
        this.tenNXB = ten_NXB;
    }
    public TaiLieu(String tenSach, String hinh)
    {
        TenSach = tenSach;
        Hinh = hinh;
    }

    public TaiLieu(String tenSach)
    {
        TenSach = tenSach;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public int getNamXB() {
        return NamXB;
    }

    public void setNamXB(int namXB) {
        NamXB = namXB;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getID_Loai() {
        return ID_Loai;
    }

    public void setID_Loai(int ID_Loai) {
        this.ID_Loai = ID_Loai;
    }

    public int getID_NXB() {
        return ID_NXB;
    }

    public void setID_NXB(int ID_NXB) {
        this.ID_NXB = ID_NXB;
    }

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }
}
