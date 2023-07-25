package com.trangchu_user.Class;

public class TaiLieuTraSach {
    Integer ID;
    String TenSach;
    int NamXB;
    int SoLuong;
    String TacGia;
    String Hinh;
    String MoTa;

    Integer ID_Loai;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
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

    public Integer getID_Loai() {
        return ID_Loai;
    }

    public void setID_Loai(Integer ID_Loai) {
        this.ID_Loai = ID_Loai;
    }

    public Integer getID_NXB() {
        return ID_NXB;
    }

    public void setID_NXB(Integer ID_NXB) {
        this.ID_NXB = ID_NXB;
    }

    Integer ID_NXB;

    public TaiLieuTraSach() {
    }

    public TaiLieuTraSach(Integer ID, String tenSach, int namXB, int soLuong, String tacGia, String hinh, String moTa, Integer ID_Loai, Integer ID_NXB) {
        this.ID = ID;
        TenSach = tenSach;
        NamXB = namXB;
        SoLuong = soLuong;
        TacGia = tacGia;
        Hinh = hinh;
        MoTa = moTa;
        this.ID_Loai = ID_Loai;
        this.ID_NXB = ID_NXB;
    }



}
