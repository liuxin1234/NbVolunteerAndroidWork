package com.example.model.items;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/22 10:52
 * 修改备注：
 */
public class MyProjectItem {
    private String neme;    //活动名称
    private String time;    //活动时间
    private String state;   //活动状态
    private String pic;     //活动图片
    private String activityRecruitId;   //活动ID
    private Integer AuditStatus;    //审批状态
    private String signInTime;      //签到时间
    private String signOutTime ;    //签退时间
    private Number computerHour;    //时长
    private int type;   //当前进入界面的类型  我的项目：1  我的关注：2  我的报名记录：3

    public String getNeme() {
        return neme;
    }

    public void setNeme(String neme) {
        this.neme = neme;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getActivityRecruitId() {
        return activityRecruitId;
    }

    public void setActivityRecruitId(String activityRecruitId) {
        this.activityRecruitId = activityRecruitId;
    }

    public Integer getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        AuditStatus = auditStatus;
    }

    public String getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(String signInTime) {
        this.signInTime = signInTime;
    }

    public String getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(String signOutTime) {
        this.signOutTime = signOutTime;
    }

    public Number getComputerHour() {
        return computerHour;
    }

    public void setComputerHour(Number computerHour) {
        this.computerHour = computerHour;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
