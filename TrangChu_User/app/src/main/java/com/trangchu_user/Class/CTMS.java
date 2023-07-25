package com.trangchu_user.Class;

public class CTMS {
    String NgayTra;
    String TinhTrangSach;

    Boolean TinhTrangTra;
    Integer ID_MT;
    Integer ID_Sach;
    Integer ID_DG;
    String hinh;

    public String getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(String ngayTra) {
        NgayTra = ngayTra;
    }

    public String getTinhTrangSach() {
        return TinhTrangSach;
    }

    public void setTinhTrangSach(String tinhTrangSach) {
        TinhTrangSach = tinhTrangSach;
    }

    public Boolean getTinhTrangTra() {
        return TinhTrangTra;
    }

    public void setTinhTrangTra(Boolean tinhTrangTra) {
        TinhTrangTra = tinhTrangTra;
    }

    public Integer getID_MT() {
        return ID_MT;
    }

    public void setID_MT(Integer ID_MT) {
        this.ID_MT = ID_MT;
    }

    public Integer getID_Sach() {
        return ID_Sach;
    }

    public void setID_Sach(Integer ID_Sach) {
        this.ID_Sach = ID_Sach;
    }

    public Integer getID_DG() {
        return ID_DG;
    }

    public void setID_DG(Integer ID_DG) {
        this.ID_DG = ID_DG;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    String tensach;
    public CTMS(String tinhTrangSach, Boolean tinhTrangTra, Integer ID_MT, Integer ID_Sach,Integer ID_DG, String hinh, String tensach, String NgayTra) {
        TinhTrangSach = tinhTrangSach;
        TinhTrangTra = tinhTrangTra;
        this.ID_MT = ID_MT;
        this.ID_Sach = ID_Sach;
        this.ID_DG = ID_DG;
        this.hinh = hinh;
        this.tensach = tensach;
        this.NgayTra = NgayTra;
    }



    public CTMS() {
    }


}
