package Class;

public class ChiTietMuonTra {
    private String NgayTra;
    private String TinhTrangSach;
    private boolean TinhTrangTra;
    private int ID_MT;
    private int ID_Sach;

    public ChiTietMuonTra() {
    }

    public ChiTietMuonTra(String ngayTra, String tinhTrangSach, boolean tinhTrangTra, int ID_MT, int ID_Sach) {
        NgayTra = ngayTra;
        TinhTrangSach = tinhTrangSach;
        TinhTrangTra = tinhTrangTra;
        this.ID_MT = ID_MT;
        this.ID_Sach = ID_Sach;
    }

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

    public boolean isTinhTrangTra() {
        return TinhTrangTra;
    }

    public void setTinhTrangTra(boolean tinhTrangTra) {
        TinhTrangTra = tinhTrangTra;
    }

    public int getID_MT() {
        return ID_MT;
    }

    public void setID_MT(int ID_MT) {
        this.ID_MT = ID_MT;
    }

    public int getID_Sach() {
        return ID_Sach;
    }

    public void setID_Sach(int ID_Sach) {
        this.ID_Sach = ID_Sach;
    }


}
