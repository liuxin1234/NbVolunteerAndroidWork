package com.example.model.signRecord;

/**
 * Created by
 * 项目名称：com.example.model.signRecord
 * 项目日期：2017/9/20
 * 作者：liux
 * 功能：
 */

public class SignRecordSimple {
    private String ActivityId;// (string, optional): 项目标识
    private String VolunteerId;// (string, optional): 获取或设置 志愿者Id外键
    private String SignCode;// (string, optional): 随机签到签离码
    private String Signtime;// (string, optional): 获取或设置 签到时间



    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getVolunteerId() {
        return VolunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        VolunteerId = volunteerId;
    }

    public String getSignCode() {
        return SignCode;
    }

    public void setSignCode(String signCode) {
        SignCode = signCode;
    }

    public String getSigntime() {
        return Signtime;
    }

    public void setSigntime(String signtime) {
        Signtime = signtime;
    }
}
