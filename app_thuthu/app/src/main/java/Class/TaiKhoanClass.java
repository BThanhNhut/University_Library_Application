package Class;

public class TaiKhoanClass {
    String tendocgia, username, password;
    Boolean tinhtrang;
    Integer madocgia;

    public String getPassword() {
        return password;
    }

    public Boolean getTinhtrang() {
        return tinhtrang;
    }

    public Integer getMadocgia() {
        return madocgia;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTinhtrang(Boolean tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public void setMadocgia(Integer madocgia) {
        this.madocgia = madocgia;
    }

    public TaiKhoanClass(String tendocgia, String username, String password, Boolean tinhtrang, Integer madocgia) {
        this.tendocgia = tendocgia;
        this.username = username;
        this.password = password;
        this.tinhtrang = tinhtrang;
        this.madocgia = madocgia;
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
