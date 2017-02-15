package com.example.model.activity;

/**
 * 项目名称：BaseAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/7/23 15:41
 * 修改备注：
 */
public class ActivityQueryOptionDto {
    private String ActivityName;//(string, optional): 获取或设置 活动名称
    private Integer Type;//(integer, optional): 获取或设置 类型0 活动1 岗位 ，没有两个都传
    private String StartTime;//(string, optional): 获取或设置 活动开始时间
    private String AreaCode;// (string, optional),
    private String CompanyId;//(string, optional),
    private String ActivityType;// (string, optional),
    private String LanguageType;//(string, optional),
    private String ActivityState;//(string, optional),
    private Integer Status;//(integer, optional),
    private Integer Stick;//(integer, optional): 是否置顶 1置顶 几个取几页
    private String KeyWord;// (string, optional): 查询关键词
    private Integer PageIndex;//(integer, optional): 当前页索引，默认1
    private Integer PageSize;//(integer, optional): 每页记录条数，默认20
    private Object Sorts;//(object, optional)
    private Boolean IsDeleted;
    private String lng;//(string, optional),
    private String lat;//(string, optional),
    private Integer scope;//(integer, optional),

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
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

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String languageType) {
        LanguageType = languageType;
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

    public Object getSorts() {
        return Sorts;
    }

    public void setSorts(Object sorts) {
        Sorts = sorts;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Integer getStick() {
        return Stick;
    }

    public void setStick(Integer stick) {
        Stick = stick;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }
}
