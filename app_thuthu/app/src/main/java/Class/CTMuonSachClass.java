package Class;

public class CTMuonSachClass {
    private String TinhTrangSach;
    private boolean TinhTrangTra;
    private int ID_MT;
    private int ID_Sach;
    private String hinh, tensach;
    private int namxuatban;
    private String tacgia;
    private String key;
    private Integer soluong;

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public CTMuonSachClass(String tinhTrangSach, boolean tinhTrangTra, int ID_MT, int ID_Sach, String hinh, String tensach, int namxuatban, String tacgia, String key, Integer soluong) {
        TinhTrangSach = tinhTrangSach;
        TinhTrangTra = tinhTrangTra;
        this.ID_MT = ID_MT;
        this.ID_Sach = ID_Sach;
        this.hinh = hinh;
        this.tensach = tensach;
        this.namxuatban = namxuatban;
        this.tacgia = tacgia;
        this.key = key;
        this.soluong = soluong;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public CTMuonSachClass() {
    }

    public String getTinhTrangSach() {
        return TinhTrangSach;
    }

    public boolean isTinhTrangTra() {
        return TinhTrangTra;
    }

    public int getID_MT() {
        return ID_MT;
    }

    public int getID_Sach() {
        return ID_Sach;
    }

    public String getHinh() {
        return hinh;
    }

    public String getTensach() {
        return tensach;
    }

    public int getNamxuatban() {
        return namxuatban;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTinhTrangSach(String tinhTrangSach)
    {
        TinhTrangSach = tinhTrangSach;
    }

    public void setTinhTrangTra(boolean tinhTrangTra) {
        TinhTrangTra = tinhTrangTra;
    }

    public void setID_MT(int ID_MT) {
        this.ID_MT = ID_MT;
    }

    public void setID_Sach(int ID_Sach) {
        this.ID_Sach = ID_Sach;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public void setNamxuatban(int namxuatban) {
        this.namxuatban = namxuatban;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }
}
