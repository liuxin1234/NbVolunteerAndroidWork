package com.example.model.items;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/5 23:00
 * 修改备注：
 */
public class HomePageListItem {
    private static final String TAG = "HomePageItem";

    private int type;//0活动，1岗位
    private String state;//0招募中,1已结束
    private String title;
    private int num;
    private int maxNum;
    private Number time;
    private String img;

    public HomePageListItem() {
    }

    public HomePageListItem(String img, int maxNum, int num, String state, Number time, String title, int type) {
        this.img = img;
        this.maxNum = maxNum;
        this.num = num;
        this.state = state;
        this.time = time;
        this.title = title;
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static String getTAG() {
        return TAG;
    }

    public Number getTime() {
        return time;
    }

    public void setTime(Number time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
