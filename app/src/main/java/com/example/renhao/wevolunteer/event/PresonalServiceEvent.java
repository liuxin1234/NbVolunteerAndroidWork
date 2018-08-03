package com.example.renhao.wevolunteer.event;

import android.content.Intent;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.event
 * 项目日期：2018/7/23
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class PresonalServiceEvent {

    private Integer tag;

    public PresonalServiceEvent(Integer tag) {
        this.tag = tag;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }
}
