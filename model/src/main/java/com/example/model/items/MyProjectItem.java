package com.example.model.items;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/22 10:52
 * 修改备注：
 */
public class MyProjectItem {
    private String neme;
    private String time;
    private String state;
    private String pic;
    private String activityRecruitId;
    private Integer AuditStatus;

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
}
