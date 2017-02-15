package com.example.model.activityTime;

/**
 * Created by Administrator on 2016/8/28.
 */
public class ActivityTimeListDto {
    private String Id;// (string, optional): 获取或设置 岗位时间段标识 ,
    private String TimeCode;// (string, optional): 获取或设置 ,
    private String ActivityId;// (string, optional): 获取或设置 项目编号 ,
    private String ActivityName;// (string),
    private String STime;// (string, optional): 获取或设置 开始时间 ,
    private String ETime;// (string, optional): 获取或设置 结束时间 ,
    private Number Lentime;// (number, optional): 获取或设置 时长(分钟) ,
    private Integer AllowNum;// (integer, optional): 获取或设置 招募人数 ,
    private Integer SignNum;// (integer, optional): 获取或设置 已报名人数 ,
    private Integer DayScore;// (integer, optional): 获取或设置 单日积分 ,
    private String MountguardTime;// (string, optional): 获取或设置 到岗位时间 ,
    private Integer RecruitedNum;// (integer, optional): 获取或设置 已招募 ,
    private Integer Type;// (integer, optional): 获取或设置 类型：日周月年


    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public Integer getAllowNum() {
        return AllowNum;
    }

    public void setAllowNum(Integer allowNum) {
        AllowNum = allowNum;
    }

    public Integer getDayScore() {
        return DayScore;
    }

    public void setDayScore(Integer dayScore) {
        DayScore = dayScore;
    }

    public String getETime() {
        return ETime;
    }

    public void setETime(String ETime) {
        this.ETime = ETime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Number getLentime() {
        return Lentime;
    }

    public void setLentime(Number lentime) {
        Lentime = lentime;
    }

    public String getMountguardTime() {
        return MountguardTime;
    }

    public void setMountguardTime(String mountguardTime) {
        MountguardTime = mountguardTime;
    }

    public Integer getRecruitedNum() {
        return RecruitedNum;
    }

    public void setRecruitedNum(Integer recruitedNum) {
        RecruitedNum = recruitedNum;
    }

    public Integer getSignNum() {
        return SignNum;
    }

    public void setSignNum(Integer signNum) {
        SignNum = signNum;
    }

    public String getSTime() {
        return STime;
    }

    public void setSTime(String STime) {
        this.STime = STime;
    }

    public String getTimeCode() {
        return TimeCode;
    }

    public void setTimeCode(String timeCode) {
        TimeCode = timeCode;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }
}
