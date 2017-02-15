package com.example.model.activityattention;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/21 18:16
 * 修改备注：
 */
public class ActivityAttentionListDto {
  private String Id ;//(string, optional): 获取或设置 关注标识
  private String ActivityId ;//(string, optional): 获取或设置 活动编号
  private String UserId;// (string, optional): 获取或设置 志愿者编号
  private String UserRealName ;//(string, optional),
  private String AttentionTime ;//(string, optional): 获取或设置 关注时间
  private String ActivityActivityName ;//(string, optional),
  private String VolunteerTrueName ;//(string, optional),
  private String ActivityState;// (string, optional),
  private String ActivityType ;//(string, optional),
  private String LstUrl ;//(string, optional),
  private String PcLstUrl ;//(string, optional),
  private String AppLstUrl ;//(string, optional

    public String getActivityActivityName() {
        return ActivityActivityName;
    }

    public void setActivityActivityName(String activityActivityName) {
        ActivityActivityName = activityActivityName;
    }

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getActivityState() {
        return ActivityState;
    }

    public void setActivityState(String activityState) {
        ActivityState = activityState;
    }

    public String getActivityType() {
        return ActivityType;
    }

    public void setActivityType(String activityType) {
        ActivityType = activityType;
    }

    public String getAppLstUrl() {
        return AppLstUrl;
    }

    public void setAppLstUrl(String appLstUrl) {
        AppLstUrl = appLstUrl;
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

    public String getLstUrl() {
        return LstUrl;
    }

    public void setLstUrl(String lstUrl) {
        LstUrl = lstUrl;
    }

    public String getPcLstUrl() {
        return PcLstUrl;
    }

    public void setPcLstUrl(String pcLstUrl) {
        PcLstUrl = pcLstUrl;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserRealName() {
        return UserRealName;
    }

    public void setUserRealName(String userRealName) {
        UserRealName = userRealName;
    }

    public String getVolunteerTrueName() {
        return VolunteerTrueName;
    }

    public void setVolunteerTrueName(String volunteerTrueName) {
        VolunteerTrueName = volunteerTrueName;
    }
}
