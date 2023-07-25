package Class;

public class TaiLieu {
    int ID;
    String Tensach, Tacgia, Hinh;

    public TaiLieu() {
    }

    public TaiLieu(int ID, String tensach, String tacgia, String hinh) {
        this.ID = ID;
        Tensach = tensach;
        Tacgia = tacgia;
        Hinh = hinh;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTensach() {
        return Tensach;
    }

    public void setTensach(String tensach) {
        Tensach = tensach;
    }

    public String getTacgia() {
        return Tacgia;
    }

    public void setTacgia(String tacgia) {
        Tacgia = tacgia;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String hinh) {
        Hinh = hinh;
    }
}
