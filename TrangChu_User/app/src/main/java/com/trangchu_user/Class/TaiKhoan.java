package com.trangchu_user.Class;

import java.util.ArrayList;
import java.util.Arrays;

public class TaiKhoan {
    private String Username;
    private String Password;
    private Boolean Tinhtrang;

    private int ID_DG;

    public TaiKhoan() {
    }

    public TaiKhoan(String username, String password, Boolean tinhtrang, int ID_DG) {
        Username = username;
        Password = password;
        Tinhtrang = tinhtrang;
        this.ID_DG = ID_DG;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean getTinhtrang() {
        return Tinhtrang;
    }

    public void setTinhtrang(Boolean tinhtrang) {
        Tinhtrang = tinhtrang;
    }

    public int getID_DG() {
        return ID_DG;
    }

    public void setID_DG(int ID_DG) {
        this.ID_DG = ID_DG;
    }


}
