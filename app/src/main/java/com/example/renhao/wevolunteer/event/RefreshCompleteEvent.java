package com.example.renhao.wevolunteer.event;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/17 9:53
 * 修改备注：
 */
public class RefreshCompleteEvent {

    private int Id;

    public RefreshCompleteEvent() {
    }

    public RefreshCompleteEvent(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
