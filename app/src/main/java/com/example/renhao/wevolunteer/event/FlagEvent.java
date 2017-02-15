package com.example.renhao.wevolunteer.event;

/**
 *EventBus发送消息
 * Created by Administrator on 2016/8/11.
 */
public class FlagEvent {

    private String mMsg;

    public FlagEvent(String msg){
        mMsg = msg;

    }

    public String getMsg(){
        return mMsg;
    }
}
