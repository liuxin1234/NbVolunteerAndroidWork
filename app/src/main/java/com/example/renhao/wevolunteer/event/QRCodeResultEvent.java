package com.example.renhao.wevolunteer.event;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/22 19:14
 * 修改备注：
 */
public class QRCodeResultEvent {
    private String qrcodeMsg;
    private int state;
    private double lat;
    private double lng;

    public QRCodeResultEvent() {
    }

    public QRCodeResultEvent(String qrcodeMsg, int state) {
        this.qrcodeMsg = qrcodeMsg;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getQrcodeMsg() {
        return qrcodeMsg;
    }

    public void setQrcodeMsg(String qrcodeMsg) {
        this.qrcodeMsg = qrcodeMsg;
    }
}
