package com.trangchu_user.Class;


public class  MuonTra_SPTra {
    private Integer ID;
    private String NgayMuon;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        NgayMuon = ngayMuon;
    }

    public Boolean getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public Integer getID_DG() {
        return ID_DG;
    }

    public void setID_DG(Integer ID_DG) {
        this.ID_DG = ID_DG;
    }

    private Boolean TinhTrang;


    private Integer ID_DG;

    public MuonTra_SPTra() {
    }

    public MuonTra_SPTra(Integer ID, String ngayMuon, Boolean tinhTrang, Integer ID_DG) {
        this.ID = ID;
        NgayMuon = ngayMuon;
        TinhTrang = tinhTrang;
        this.ID_DG = ID_DG;
    }


}
