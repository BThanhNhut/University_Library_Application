package Class;

public class TaiKhoanClass {
    String tendocgia, username;

    public TaiKhoanClass(String tendocgia, String username) {
        this.tendocgia = tendocgia;
        this.username = username;
    }

    public TaiKhoanClass() {
    }

    public String getTendocgia() {
        return tendocgia;
    }

    public String getUsername() {
        return username;
    }

    public void setTendocgia(String tendocgia) {
        this.tendocgia = tendocgia;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
