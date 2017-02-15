package com.example.model.items;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/7 18:40
 * 修改备注：
 */
public class HomePageGridItem {
    private static final String TAG = "HomePageGridItem";

    private int imagUrl;
    private String titleBig;
    private String titleSmall;
    private int titleColor;

    public HomePageGridItem(int imagUrl, String titleBig, String titleSmall, int titleColor) {
        this.imagUrl = imagUrl;
        this.titleBig = titleBig;
        this.titleSmall = titleSmall;
        this.titleColor = titleColor;
    }

    public int getImagUrl() {
        return imagUrl;
    }

    public void setImagUrl(int imagUrl) {
        this.imagUrl = imagUrl;
    }

    public String getTitleBig() {
        return titleBig;
    }

    public void setTitleBig(String titleBig) {
        this.titleBig = titleBig;
    }

    public String getTitleSmall() {
        return titleSmall;
    }

    public void setTitleSmall(String titleSmall) {
        this.titleSmall = titleSmall;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }
}
