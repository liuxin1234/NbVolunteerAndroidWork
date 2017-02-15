package com.example.model.activityattention;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/21 17:01
 * 修改备注：
 */
public class ActivityAttentionDto {
    private String Id;//(string): 获取或设置 关注标识
    private String ActivityId;//(string, optional): 获取或设置 活动编号
    private String UserId;//(string, optional): 获取或设置 志愿者编号
    private String AttentionTime;//(string, optional): 获取或设置 关注时间

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getAttentionTime() {
        return AttentionTime;
    }

    public void setAttentionTime(String attentionTime) {
        AttentionTime = attentionTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
