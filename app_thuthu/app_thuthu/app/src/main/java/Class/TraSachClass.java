package Class;

public class TraSachClass {
    private int Id;
    private  String TenDG, SoSachMuon, SoSachTra;

    public TraSachClass() {
    }

    public TraSachClass(int id, String tenDG, String soSachMuon, String soSachTra) {
        Id = id;
        TenDG = tenDG;
        SoSachMuon = soSachMuon;
        SoSachTra = soSachTra;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenDG() {
        return TenDG;
    }

    public void setTenDG(String tenDG) {
        TenDG = tenDG;
    }

    public String getSoSachMuon() {
        return SoSachMuon;
    }

    public void setSoSachMuon(String soSachMuon) {
        SoSachMuon = soSachMuon;
    }

    public String getSoSachTra() {
        return SoSachTra;
    }

    public void setSoSachTra(String soSachTra) {
        SoSachTra = soSachTra;
    }
}
