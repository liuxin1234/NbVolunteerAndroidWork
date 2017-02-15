package com.example.model.activity;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/16 14:56
 * 修改备注：
 */
public class ActivityTimeSimpleDto {
   private  String Id;//(string): 获取或设置 岗位时间段标识
   private  String STime;//(string, optional): 获取或设置 开始时间
   private  String ETime;//(string, optional): 获取或设置 结束时间
   private  String MountguardTime;//(string, optional): 获取或设置 到岗位时间
   private  Integer RecruitedNum;// (integer, optional): 获取或设置 已招募
   private  Integer AllowNum;//(integer, optional): 获取或设置 招募人数
   private  String TimeCode;// (string, optional): 获取或设置
   private  String ActivityId;//(string, optional): 获取或设置 项目编号

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public Integer getAllowNum() {
        return AllowNum;
    }

    public void setAllowNum(Integer allowNum) {
        AllowNum = allowNum;
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
}
