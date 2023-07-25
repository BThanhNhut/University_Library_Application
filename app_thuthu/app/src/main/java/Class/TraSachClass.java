package Class;

public class TraSachClass {
    int id;
    String TenDG;
    int slMuon, slTra;

    public TraSachClass() {
    }

    public TraSachClass(int id, String tenDG, int slMuon, int slTra) {
        this.id = id;
        TenDG = tenDG;
        this.slMuon = slMuon;
        this.slTra = slTra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDG() {
        return TenDG;
    }

    public void setTenDG(String tenDG) {
        TenDG = tenDG;
    }

    public int getSlMuon() {
        return slMuon;
    }

    public void setSlMuon(int slMuon) {
        this.slMuon = slMuon;
    }

    public int getSlTra() {
        return slTra;
    }

    public void setSlTra(int slTra) {
        this.slTra = slTra;
    }
}
