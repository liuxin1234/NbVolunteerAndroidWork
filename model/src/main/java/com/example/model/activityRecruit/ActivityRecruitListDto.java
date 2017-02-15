package com.example.model.activityRecruit;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/20 14:48
 * 修改备注：
 */
public class ActivityRecruitListDto {
    private String Id;//(string, optional): 获取或设置 报名标识
    private String RecruitCode;//(string, optional): 获取或设置 报名编码
    private String ActivityId;//(string, optional): 获取或设置 活动或岗位Id
    private String ActivityActivityName;//(string, optional): 获取或设置 活动或岗位
    private String VolunteerId;//(string, optional): 获取或设置 志愿者
    private String VolunteerTrueName;//(string, optional): 获取或设置 志愿者
    private String ActivityTimeId;//(string, optional): 获取或设置 岗位时间段Id
    private String ActivityTimeSTime;//(string, optional): 获取或设置 时间段开始
    private String ActivityTimeETime;//(string, optional): 获取或设置 时间段结束
    private String BaoMingDate;//(string, optional): 获取或设置 报名时间
    private Integer AuditStatus;//(integer, optional): 获取或设置 审核状态
    private String ExecuteTime;//(string, optional): 获取或设置 参加的活动或岗位时间
    private String AuditTime;// (string, optional): 获取或设置 审核时间
    private String AuditUserName;//(string, optional): 获取或设置 审核人用户名
    private Integer Sign;//(integer, optional): 获取或设置 是否签到0 否 1 是
    private Integer Signout;//(integer, optional): 获取或设置 是否签离0 否 1 是
    private Integer Source;//(integer, optional): 获取或设置 来源 0 1
    private String TeamId;//(string, optional): 获取或设置 团队编号如果存在则为以团队报名拉入否则为个人报名[del]
    private String Remark;//(string, optional): 获取或设置 拒绝原因
    private String Explain;//(string, optional): 获取或设置 个人说明
    private String IP;//(string, optional): 获取或设置 报名ID

    private String ActivityState;// (string, optional): 当前项目运行状态
    private String AppLstUrl;// (string, optional): App图片
    private String ActivityType;// (integer, optional): 获取或设置 类型0 活动1 岗位

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

    public String getActivityTimeETime() {
        return ActivityTimeETime;
    }

    public void setActivityTimeETime(String activityTimeETime) {
        ActivityTimeETime = activityTimeETime;
    }

    public String getActivityTimeId() {
        return ActivityTimeId;
    }

    public void setActivityTimeId(String activityTimeId) {
        ActivityTimeId = activityTimeId;
    }

    public String getActivityTimeSTime() {
        return ActivityTimeSTime;
    }

    public void setActivityTimeSTime(String activityTimeSTime) {
        ActivityTimeSTime = activityTimeSTime;
    }

    public Integer getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        AuditStatus = auditStatus;
    }

    public String getAuditTime() {
        return AuditTime;
    }

    public void setAuditTime(String auditTime) {
        AuditTime = auditTime;
    }

    public String getAuditUserName() {
        return AuditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        AuditUserName = auditUserName;
    }

    public String getBaoMingDate() {
        return BaoMingDate;
    }

    public void setBaoMingDate(String baoMingDate) {
        BaoMingDate = baoMingDate;
    }

    public String getExecuteTime() {
        return ExecuteTime;
    }

    public void setExecuteTime(String executeTime) {
        ExecuteTime = executeTime;
    }

    public String getExplain() {
        return Explain;
    }

    public void setExplain(String explain) {
        Explain = explain;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getRecruitCode() {
        return RecruitCode;
    }

    public void setRecruitCode(String recruitCode) {
        RecruitCode = recruitCode;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public Integer getSign() {
        return Sign;
    }

    public void setSign(Integer sign) {
        Sign = sign;
    }

    public Integer getSignout() {
        return Signout;
    }

    public void setSignout(Integer signout) {
        Signout = signout;
    }

    public Integer getSource() {
        return Source;
    }

    public void setSource(Integer source) {
        Source = source;
    }

    public String getTeamId() {
        return TeamId;
    }

    public void setTeamId(String teamId) {
        TeamId = teamId;
    }

    public String getVolunteerId() {
        return VolunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        VolunteerId = volunteerId;
    }

    public String getVolunteerTrueName() {
        return VolunteerTrueName;
    }

    public void setVolunteerTrueName(String volunteerTrueName) {
        VolunteerTrueName = volunteerTrueName;
    }
}
