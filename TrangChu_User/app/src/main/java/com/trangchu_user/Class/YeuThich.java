package com.trangchu_user.Class;

public class YeuThich {
    public YeuThich() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_DG() {
        return id_DG;
    }

    public void setId_DG(int id_DG) {
        this.id_DG = id_DG;
    }

    public int getId_Sach() {
        return id_Sach;
    }

    public void setId_Sach(int id_Sach) {
        this.id_Sach = id_Sach;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public YeuThich(int id, int id_DG, int id_Sach, String hinh, String tenSach, String tacGia) {
        this.id = id;
        this.id_DG = id_DG;
        this.id_Sach = id_Sach;
        this.hinh = hinh;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
    }

    private int id;
    private int id_DG;
    private int id_Sach;
    private String hinh;
    private String tenSach;


    private String tacGia;


}
