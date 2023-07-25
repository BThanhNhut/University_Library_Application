package Class;

public class TaiLieuClass {
    int id;
    String hinh;
    int nxb;
    String tacGia;
    String tensach;
    int sl;

    public TaiLieuClass() {
    }

    public TaiLieuClass(int id, String hinh, int nxb, String tacGia, String tensach, int sl) {
        this.id = id;
        this.hinh = hinh;
        this.nxb = nxb;
        this.tacGia = tacGia;
        this.tensach = tensach;
        this.sl = sl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public int getNxb() {
        return nxb;
    }

    public void setNxb(int nxb) {
        this.nxb = nxb;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
