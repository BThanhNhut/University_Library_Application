package com.trangchu_user.Class;

public class Item_TrangChu {
    private int resourceImg;
    private String name;

    public Item_TrangChu(String name, int resourceImg) {
        this.resourceImg= resourceImg;
        this.name= name;
    }

    public int getResourceImg() {
        return resourceImg;
    }

    public void setResourceImg(int resourceImg) {
        this.resourceImg = resourceImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
