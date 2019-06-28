package com.example.model.show;

import java.util.List;

/**
 * Created by
 * 项目名称：com.example.model.show
 * 项目日期：2019/5/7
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class ShowBean {
    private List<String> listImgUrl;
    private String orgName;
    private String orgHeadUrl;
    private String activityName;
    private String showTime;

    public List<String> getListImgUrl() {
        return listImgUrl;
    }

    public void setListImgUrl(List<String> listImgUrl) {
        this.listImgUrl = listImgUrl;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgHeadUrl() {
        return orgHeadUrl;
    }

    public void setOrgHeadUrl(String orgHeadUrl) {
        this.orgHeadUrl = orgHeadUrl;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
