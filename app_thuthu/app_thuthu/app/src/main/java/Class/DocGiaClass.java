package Class;

public class DocGiaClass {
    String email, gioitinh, tendocgia, username;
    Integer id;

    public DocGiaClass(String email, String gioitinh, String tendocgia, String username, Integer id) {
        this.email = email;
        this.gioitinh = gioitinh;
        this.tendocgia = tendocgia;
        this.username = username;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public String getTendocgia() {
        return tendocgia;
    }

    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setTendocgia(String tendocgia) {
        this.tendocgia = tendocgia;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
