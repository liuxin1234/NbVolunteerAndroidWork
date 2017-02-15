package com.example.model.activityRecruit;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/20 14:46
 * 修改备注：
 */
public class ActivityRecruitQueryOptionDto {
    private String RecruitCode;// (string, optional): 获取或设置 报名编码
    private String ActivityId;// (string, optional): 获取或设置 活动或岗位Id
    private String ActivityName;// (string, optional): 获取或设置 活动或岗位名称
    private String VolunteerTrueName;// (string, optional): 获取或设置 志愿者姓名
    private String VolunteerId;// (string, optional): 获取或设置 志愿者Id
    private String ActivityTimeId;//(string, optional): 获取或设置 岗位时间段Id
    private String BaoMingDateBeg;//(string, optional): 获取或设置 报名时间开始
    private String BaoMingDateEnd;//(string, optional): 获取或设置 报名时间结束
    private Integer AuditStatus;//(integer, optional): 获取或设置 状态
    private String AuditTime;//(string, optional): 获取或设置 审核时间
    private String AuditUserId;//(string, optional): 获取或设置 审核人ID
    private Integer Sign;//(integer, optional): 获取或设置 是否签到0 否 1 是
    private Integer Signout;//(integer, optional): 获取或设置 是否签离0 否 1 是
    private Integer Source;// (integer, optional): 获取或设置 来源 0 1
    private String TeamId;//(string, optional): 获取或设置 团队编号如果存在则为以团队报名拉入否则为个人报名[del]
    private String KeyWord;//(string, optional): 查询关键词
    private Integer PageIndex;//(integer, optional): 当前页索引，默认1
    private Integer PageSize;// (integer, optional): 每页记录条数，默认20
    private Object Sorts;//(object, optional)

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

    public String getActivityTimeId() {
        return ActivityTimeId;
    }

    public void setActivityTimeId(String activityTimeId) {
        ActivityTimeId = activityTimeId;
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

    public String getAuditUserId() {
        return AuditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        AuditUserId = auditUserId;
    }

    public String getBaoMingDateBeg() {
        return BaoMingDateBeg;
    }

    public void setBaoMingDateBeg(String baoMingDateBeg) {
        BaoMingDateBeg = baoMingDateBeg;
    }

    public String getBaoMingDateEnd() {
        return BaoMingDateEnd;
    }

    public void setBaoMingDateEnd(String baoMingDateEnd) {
        BaoMingDateEnd = baoMingDateEnd;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public Integer getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        PageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public String getRecruitCode() {
        return RecruitCode;
    }

    public void setRecruitCode(String recruitCode) {
        RecruitCode = recruitCode;
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

    public Object getSorts() {
        return Sorts;
    }

    public void setSorts(Object sorts) {
        Sorts = sorts;
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
