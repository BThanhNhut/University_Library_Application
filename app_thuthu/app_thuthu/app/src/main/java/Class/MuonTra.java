package Class;


public class MuonTra {
    private int ID;
    private String NgayMuon;
    private Boolean TinhTrang;


    private int ID_DG;

    public MuonTra() {
    }

    public MuonTra(int ID, String ngayMuon, Boolean tinhTrang, int ID_DG) {
        this.ID = ID;
        NgayMuon = ngayMuon;
        TinhTrang = tinhTrang;
        this.ID_DG = ID_DG;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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

    public int getID_DG() {
        return ID_DG;
    }

    public void setID_DG(int ID_DG) {
        this.ID_DG = ID_DG;
    }

}
