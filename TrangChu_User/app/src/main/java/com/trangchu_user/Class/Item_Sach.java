package com.trangchu_user.Class;

public class Item_Sach {
    String tvHeading, tvNgay;
    int titleImage;

    public Item_Sach(String tvHeading, String tvNgay, int titleImage) {
        this.tvHeading = tvHeading;
        this.tvNgay = tvNgay;
        this.titleImage = titleImage;
    }

    public String getTvHeading() {
        return tvHeading;
    }

    public void setTvHeading(String tvHeading) {
        this.tvHeading = tvHeading;
    }

    public String getTvNgay() {
        return tvNgay;
    }

    public void setTvNgay(String tvNgay) {
        this.tvNgay = tvNgay;
    }

    public int getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }
}
