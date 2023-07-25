package Class;

public class CTTraSachClass {
    private String hinh, tensach;
    private int namxuatban;
    private String tacgia;
    boolean tinhtrang;

    public CTTraSachClass() {
    }

    public CTTraSachClass(String hinh, String tensach, int namxuatban, String tacgia, boolean tinhtrang) {
        this.hinh = hinh;
        this.tensach = tensach;
        this.namxuatban = namxuatban;
        this.tacgia = tacgia;
        this.tinhtrang = tinhtrang;
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

    public int getNamxuatban() {
        return namxuatban;
    }

    public void setNamxuatban(int namxuatban) {
        this.namxuatban = namxuatban;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public boolean isTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(boolean tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
}
