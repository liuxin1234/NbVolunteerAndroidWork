package com.example.model.activity;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/2 17:50
 * 修改备注：
 */
public class ActivityCreateBO {
    private static final String TAG = "ActivityCreateBO";

    private String Id;//(string, optional): 获取或设置 活动标识
    private int Version;// (integer, optional): 获取或设置 数据版本
    private String ActivityId;// (string, optional): 获取或设置 活动编码
    private String ActivityName;// (string): 获取或设置 活动名称
    private String ActivityTypeId;// (string, optional): 获取或设置 活动类别
    private int Type;//(integer, optional): 获取或设置 类型0 活动1 岗位
    private String OtherServiceTypeName;// (string, optional): 获取或设置 务方式其他服内容
    private String StartTime;// (string): 获取或设置 活动开始时间
    private String FinishTime;// (string, optional): 获取或设置 活动结束时间
    private String DaySTime;//(string): 获取或设置 区间开始时间
    private String DayETime;//(string): 获取或设置 区间结束时间
    private double LengthTime;// (number, optional): 获取或设置 活动时长(分钟)
    private String BeginTime;//(string): 获取或设置 报名开始时间
    private String EndTime;//(string, optional): 获取或设置 报名结束时间
    private int EndTimeChoice;//(integer): 获取或设置 报名结束时间
    private Double Score;//(integer): 获取或设置 获取积分
    private String AreaId;// (string): 获取或设置 区域Id
    private String AreaName;//(string)
    private String Addr;//(string): 获取或设置 地址
    private int PersonNum;//(integer): 获取或设置 计划服务人数
    private int JoinNum;//(integer): 获取或设置 报名人数
    private int RecruitNumber;// (integer): 获取或设置 招募人数
    private int Recruited;//(integer, optional): 获取或设置 已招募
    private int SingleRecruit;// (integer, optional): 获取或设置 单次招募
    private String Summary;//(string, optional): 获取或设置 活动摘要
    private String Text;//(string, optional): 获取或设置 活动详情
    private String Linker;//(string, optional): 获取或设置 活动联系人
    private String Tel;//(string, optional): 获取或设置 活动联电话
    private String Mobile;//(string, optional): 获取或设置 活动联系手机
    private String Email;//(string, optional): 获取或设置 活动联系邮箱
    private double BMeals;//(number, optional): 获取或设置 是否提供餐补
    private double Safe;//(number, optional): 获取或设置 是否提供保险
    private double Traffic;//(number, optional): 获取或设置 供交通补贴
    private double BMealsType;//(number, optional): 获取或设置 提供餐补类型
    private double SafeType;//(number, optional): 获取或设置 是否提供保险 类型
    private double TrafficType;//(number, optional): 获取或设置 供交通补贴类型
    private double ScopeType;// (number, optional): 获取或设置 签到范围
    private int IisTrain;//(integer, optional): 获取或设置 是否培训
    private int Stick;//(integer, optional): 获取或设置 是否置顶
    private String StickTime;//(string, optional): 获取或设置 置顶开始时间
    private String StickendTime;//(string, optional): 获取或设置 置顶结束时间
    private int StickHistory;//(integer, optional): 获取或设置 是否有过置顶
    private int Hits;//(integer, optional): 获取或设置 浏览次数
    private int Follow;//(integer, optional): 获取或设置 关注次数
    private int Praise;//(integer, optional): 获取或设置 点赞数
    private double Lng;//(number, optional): 获取或设置 经度
    private double Lat;//(number, optional): 获取或设置 纬度
    private String LstUrl;//(string, optional): 获取或设置 图片列表
    private String AppImgUrl;//(string, optional): 获取或设置 app图片列表
    private int Status;//(integer): 获取或设置 状态
    private int RunStatus;//(integer, optional): 获取或设置 执行状态
    private String Keywords;//(string, optional): 获取或设置 关键词
    private String LanguageType;//(string, optional): 获取或设置 语种
    private boolean IsDeleted;//(boolean, optional): 获取或设置 删除状态

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int version) {
        Version = version;
    }

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

    public String getActivityTypeId() {
        return ActivityTypeId;
    }

    public void setActivityTypeId(String activityTypeId) {
        ActivityTypeId = activityTypeId;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getOtherServiceTypeName() {
        return OtherServiceTypeName;
    }

    public void setOtherServiceTypeName(String otherServiceTypeName) {
        OtherServiceTypeName = otherServiceTypeName;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public String getDaySTime() {
        return DaySTime;
    }

    public void setDaySTime(String daySTime) {
        DaySTime = daySTime;
    }

    public String getDayETime() {
        return DayETime;
    }

    public void setDayETime(String dayETime) {
        DayETime = dayETime;
    }

    public double getLengthTime() {
        return LengthTime;
    }

    public void setLengthTime(double lengthTime) {
        LengthTime = lengthTime;
    }

    public String getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(String beginTime) {
        BeginTime = beginTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public int getEndTimeChoice() {
        return EndTimeChoice;
    }

    public void setEndTimeChoice(int endTimeChoice) {
        EndTimeChoice = endTimeChoice;
    }

    public Double getScore() {
        return Score;
    }

    public void setScore(Double score) {
        Score = score;
    }

    public String getAreaId() {
        return AreaId;
    }

    public void setAreaId(String areaId) {
        AreaId = areaId;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public int getPersonNum() {
        return PersonNum;
    }

    public void setPersonNum(int personNum) {
        PersonNum = personNum;
    }

    public int getJoinNum() {
        return JoinNum;
    }

    public void setJoinNum(int joinNum) {
        JoinNum = joinNum;
    }

    public int getRecruitNumber() {
        return RecruitNumber;
    }

    public void setRecruitNumber(int recruitNumber) {
        RecruitNumber = recruitNumber;
    }

    public int getRecruited() {
        return Recruited;
    }

    public void setRecruited(int recruited) {
        Recruited = recruited;
    }

    public int getSingleRecruit() {
        return SingleRecruit;
    }

    public void setSingleRecruit(int singleRecruit) {
        SingleRecruit = singleRecruit;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getLinker() {
        return Linker;
    }

    public void setLinker(String linker) {
        Linker = linker;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public double getBMeals() {
        return BMeals;
    }

    public void setBMeals(double BMeals) {
        this.BMeals = BMeals;
    }

    public double getSafe() {
        return Safe;
    }

    public void setSafe(double safe) {
        Safe = safe;
    }

    public double getTraffic() {
        return Traffic;
    }

    public void setTraffic(double traffic) {
        Traffic = traffic;
    }

    public double getBMealsType() {
        return BMealsType;
    }

    public void setBMealsType(double BMealsType) {
        this.BMealsType = BMealsType;
    }

    public double getSafeType() {
        return SafeType;
    }

    public void setSafeType(double safeType) {
        SafeType = safeType;
    }

    public double getTrafficType() {
        return TrafficType;
    }

    public void setTrafficType(double trafficType) {
        TrafficType = trafficType;
    }

    public double getScopeType() {
        return ScopeType;
    }

    public void setScopeType(double scopeType) {
        ScopeType = scopeType;
    }

    public int getIisTrain() {
        return IisTrain;
    }

    public void setIisTrain(int iisTrain) {
        IisTrain = iisTrain;
    }

    public int getStick() {
        return Stick;
    }

    public void setStick(int stick) {
        Stick = stick;
    }

    public String getStickTime() {
        return StickTime;
    }

    public void setStickTime(String stickTime) {
        StickTime = stickTime;
    }

    public String getStickendTime() {
        return StickendTime;
    }

    public void setStickendTime(String stickendTime) {
        StickendTime = stickendTime;
    }

    public int getStickHistory() {
        return StickHistory;
    }

    public void setStickHistory(int stickHistory) {
        StickHistory = stickHistory;
    }

    public int getHits() {
        return Hits;
    }

    public void setHits(int hits) {
        Hits = hits;
    }

    public int getFollow() {
        return Follow;
    }

    public void setFollow(int follow) {
        Follow = follow;
    }

    public int getPraise() {
        return Praise;
    }

    public void setPraise(int praise) {
        Praise = praise;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public String getLstUrl() {
        return LstUrl;
    }

    public void setLstUrl(String lstUrl) {
        LstUrl = lstUrl;
    }

    public String getAppImgUrl() {
        return AppImgUrl;
    }

    public void setAppImgUrl(String appImgUrl) {
        AppImgUrl = appImgUrl;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getRunStatus() {
        return RunStatus;
    }

    public void setRunStatus(int runStatus) {
        RunStatus = runStatus;
    }

    public String getKeywords() {
        return Keywords;
    }

    public void setKeywords(String keywords) {
        Keywords = keywords;
    }

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String languageType) {
        LanguageType = languageType;
    }

    public boolean isDeleted() {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted) {
        IsDeleted = deleted;
    }
}
