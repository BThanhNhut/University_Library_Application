package com.trangchu_user.Class;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DocGia {
    private int ID;
    private String TenDG;
    private String GioiTinh;
    private String Email;
    private String Username;

    public DocGia() {
    }

    public DocGia(int ID, String tenDG, String gioiTinh, String email, String username) {
        this.ID = ID;
        TenDG = tenDG;
        GioiTinh = gioiTinh;
        Email = email;
        Username = username;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenDG() {
        return TenDG;
    }

    public void setTenDG(String tenDG) {
        TenDG = tenDG;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
