package com.example.model.jobActivity;

import com.example.model.activity.ActivityTimeSimpleDto;
import com.example.model.user.CreateOperationDto;
import com.example.model.user.ModifyOperationDto;

import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/20 16:46
 * 修改备注：
 */
public class JobActivityViewDto {
    private CreateOperationDto CreateOperation;//(CreateOperationDto, optional): 登记信息
    private ModifyOperationDto ModifyOperation;//(ModifyOperationDto, optional): 更新信息
    private String ActivityCode;// (string, optional): 获取或设置 活动编码
    private String ActivityTypeId;//(string, optional),
    private String ActivityTypeName;//(string, optional),
    private String DaySTime;//(string, optional): 获取或设置 区间开始时间
    private String DayETime;//(string, optional): 获取或设置 区间结束时间
    private Number LengthTime;//(number, optional): 获取或设置 活动时长(分钟)
    private String OtherServiceTypeName;// (string, optional): 获取或设置 务方式其他服内容
    private String AreaName;//(string, optional): 区域名称
    private String AreaId;//(string, optional): 区域标识
    private String AreaCode;//(string, optional): 获取或设置 区域编号
    private String Addr;// (string, optional): 获取或设置 地址
    private Integer PersonNum;//(integer, optional): 获取或设置 计划服务人数
    private String Summary;//(string, optional): 获取或设置 活动摘要
    private String Text;//(string, optional): 获取或设置 活动详情
    private String JobText;//(string, optional): 获取或设置 岗位要求
    private String JobTextName;//(string, optional): 获取或设置 岗位要求
    private Integer RecruitNumber;//(integer, optional): 获取或设置 招募人数
    private Double Score;//(integer, optional): 获取或设置 获取积分
    private String Linker;//(string, optional): 获取或设置 活动联系人
    private String Tel;//(string, optional): 获取或设置 活动联电话
    private String Mobile;//(string, optional): 获取或设置 活动联系手机
    private String Email;//(string, optional): 获取或设置 活动联系邮箱
    private String BeginTime;//(string, optional): 获取或设置 报名开始时间
    private Number BMeals;// (number, optional): 获取或设置 是否提供餐补
    private Number Safe;//(number, optional),
    private Number Traffic;// (number, optional): 获取或设置 供交通补贴
    private Integer BMealsType;//(integer, optional): 获取或设置 提供餐补类型
    private Integer SafeType;//(integer, optional): 获取或设置 是否提供保险 类型
    private Integer TrafficType;// (integer, optional): 获取或设置 供交通补贴类型
    private String BMealsName;//(string, optional): 获取或设置 提供餐补类型
    private String SafeName;// (string, optional): 获取或设置 是否提供保险 类型
    private String TrafficName;//(string, optional): 获取或设置 供交通补贴类型
    private Number ScopeType;// (number, optional): 获取或设置 签到范围
    private String PeriodType;// (string, optional): 获取或设置 周期
    private Integer EndTimeChoice;// (integer, optional),
    private String EndTimeChoiceName;//(string, optional),
    private String EndTime;//(string, optional): 获取或设置 报名结束时间
    private Integer Stick;//(integer, optional): 获取或设置 是否置顶
    private String StickName;//(string, optional): 获取或设置 是否置顶
    private String StickTime;//(string, optional): 获取或设置 置顶开始时间
    private String StickendTime;// (string, optional): 获取或设置 置顶结束时间
    private Integer StickHistory;//(integer, optional): 获取或设置 是否有过置顶
    private String StickHistoryName;//(string, optional): 获取或设置 是否有过置顶
    private Integer Hits;//(integer, optional): 获取或设置 浏览次数
    private Integer Follow;//(integer, optional): 获取或设置 关注次数
    private Integer Praise;//(integer, optional): 获取或设置 点赞数
    private String LstUrl;//(string, optional): 获取或设置 图片列表
    private Integer JoinNum;//(integer, optional): 获取或设置 报名人数
    private Integer IisTrain;//(integer, optional): 获取或设置 是否培训
    private String IisTrainName;//(string, optional): 获取或设置 是否培训
    private Integer Status;//(integer, optional): 获取或设置 状态
    private String StatusName;//(string, optional): 获取或设置 状态
    private Integer RunStatus;//(integer, optional): 获取或设置 执行状态
    private Number Lng;//(number, optional): 获取或设置 经度
    private Number Lat;//(number, optional): 获取或设置 纬度
    private String Keywords;//(string, optional): 获取或设置 关键词
    private String AppImgUrl;// (string, optional): 获取或设置 app图片列表
    private Integer IsSpecify;//(integer, optional): 获取或设置 是否指定专业愿者
    private String IsSpecifyName;// (string, optional): 获取或设置 是否指定专业愿者
    private Integer Recruited;//(integer, optional): 获取或设置 已招募
    private Integer SingleRecruit;//(integer, optional): 获取或设置 单次招募
    private String LanguageType;//(string, optional): 获取或设置 语种
    private String LanguageTypeName;//(string, optional): 获取或设置 语种
    private Boolean IsDeleted;//(boolean, optional): 获取或设置 删除状态
    private List<ActivityTimeSimpleDto> ActivityTimes;// (Array[ActivityTimeSimpleDto], optional): 岗位时间列表
    private String CompanyId;//(string, optional): 获取或设置 单位Id
    private String SpecialityType;//(string, optional): 志愿者专业要求
    private String Id;//(string, optional): 获取或设置 活动标识
    private String ActivityName;//(string, optional): 获取或设置 活动名称
    private String StartTime;//(string, optional): 获取或设置 活动开始时间
    private String FinishTime;//(string, optional): 获取或设置 活动结束时间
    private String CompanyName;//(string, optional),
    private String AttachName;//(string, optional): 图片名称
    private String PcLstUrl;//(string, optional): Pc图片
    private String AppLstUrl;// (string, optional): App图片
    private String OperationState;//(string, optional): 当前项目运行状态
    private String SpecialityTypeName;// (string, optional)

    public String getOperationState() {
        return OperationState;
    }

    public void setOperationState(String operationState) {
        OperationState = operationState;
    }

    public String getActivityCode() {
        return ActivityCode;
    }

    public void setActivityCode(String activityCode) {
        ActivityCode = activityCode;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public List<ActivityTimeSimpleDto> getActivityTimes() {
        return ActivityTimes;
    }

    public void setActivityTimes(List<ActivityTimeSimpleDto> activityTimes) {
        ActivityTimes = activityTimes;
    }

    public String getActivityTypeId() {
        return ActivityTypeId;
    }

    public void setActivityTypeId(String activityTypeId) {
        ActivityTypeId = activityTypeId;
    }

    public String getActivityTypeName() {
        return ActivityTypeName;
    }

    public void setActivityTypeName(String activityTypeName) {
        ActivityTypeName = activityTypeName;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getAppImgUrl() {
        return AppImgUrl;
    }

    public void setAppImgUrl(String appImgUrl) {
        AppImgUrl = appImgUrl;
    }

    public String getAppLstUrl() {
        return AppLstUrl;
    }

    public void setAppLstUrl(String appLstUrl) {
        AppLstUrl = appLstUrl;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
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

    public String getAttachName() {
        return AttachName;
    }

    public void setAttachName(String attachName) {
        AttachName = attachName;
    }

    public String getBeginTime() {
        return BeginTime;
    }

    public void setBeginTime(String beginTime) {
        BeginTime = beginTime;
    }

    public Number getBMeals() {
        return BMeals;
    }

    public void setBMeals(Number BMeals) {
        this.BMeals = BMeals;
    }

    public String getBMealsName() {
        return BMealsName;
    }

    public void setBMealsName(String BMealsName) {
        this.BMealsName = BMealsName;
    }

    public Integer getBMealsType() {
        return BMealsType;
    }

    public void setBMealsType(Integer BMealsType) {
        this.BMealsType = BMealsType;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public CreateOperationDto getCreateOperation() {
        return CreateOperation;
    }

    public void setCreateOperation(CreateOperationDto createOperation) {
        CreateOperation = createOperation;
    }

    public String getDayETime() {
        return DayETime;
    }

    public void setDayETime(String dayETime) {
        DayETime = dayETime;
    }

    public String getDaySTime() {
        return DaySTime;
    }

    public void setDaySTime(String daySTime) {
        DaySTime = daySTime;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public Integer getEndTimeChoice() {
        return EndTimeChoice;
    }

    public void setEndTimeChoice(Integer endTimeChoice) {
        EndTimeChoice = endTimeChoice;
    }

    public String getEndTimeChoiceName() {
        return EndTimeChoiceName;
    }

    public void setEndTimeChoiceName(String endTimeChoiceName) {
        EndTimeChoiceName = endTimeChoiceName;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public Integer getFollow() {
        return Follow;
    }

    public void setFollow(Integer follow) {
        Follow = follow;
    }

    public Integer getHits() {
        return Hits;
    }

    public void setHits(Integer hits) {
        Hits = hits;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Integer getIisTrain() {
        return IisTrain;
    }

    public void setIisTrain(Integer iisTrain) {
        IisTrain = iisTrain;
    }

    public String getIisTrainName() {
        return IisTrainName;
    }

    public void setIisTrainName(String iisTrainName) {
        IisTrainName = iisTrainName;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }

    public Integer getIsSpecify() {
        return IsSpecify;
    }

    public void setIsSpecify(Integer isSpecify) {
        IsSpecify = isSpecify;
    }

    public String getIsSpecifyName() {
        return IsSpecifyName;
    }

    public void setIsSpecifyName(String isSpecifyName) {
        IsSpecifyName = isSpecifyName;
    }

    public String getJobText() {
        return JobText;
    }

    public void setJobText(String jobText) {
        JobText = jobText;
    }

    public String getJobTextName() {
        return JobTextName;
    }

    public void setJobTextName(String jobTextName) {
        JobTextName = jobTextName;
    }

    public Integer getJoinNum() {
        return JoinNum;
    }

    public void setJoinNum(Integer joinNum) {
        JoinNum = joinNum;
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

    public String getLanguageTypeName() {
        return LanguageTypeName;
    }

    public void setLanguageTypeName(String languageTypeName) {
        LanguageTypeName = languageTypeName;
    }

    public Number getLat() {
        return Lat;
    }

    public void setLat(Number lat) {
        Lat = lat;
    }

    public Number getLengthTime() {
        return LengthTime;
    }

    public void setLengthTime(Number lengthTime) {
        LengthTime = lengthTime;
    }

    public String getLinker() {
        return Linker;
    }

    public void setLinker(String linker) {
        Linker = linker;
    }

    public Number getLng() {
        return Lng;
    }

    public void setLng(Number lng) {
        Lng = lng;
    }

    public String getLstUrl() {
        return LstUrl;
    }

    public void setLstUrl(String lstUrl) {
        LstUrl = lstUrl;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public ModifyOperationDto getModifyOperation() {
        return ModifyOperation;
    }

    public void setModifyOperation(ModifyOperationDto modifyOperation) {
        ModifyOperation = modifyOperation;
    }

    public String getOtherServiceTypeName() {
        return OtherServiceTypeName;
    }

    public void setOtherServiceTypeName(String otherServiceTypeName) {
        OtherServiceTypeName = otherServiceTypeName;
    }

    public String getPcLstUrl() {
        return PcLstUrl;
    }

    public void setPcLstUrl(String pcLstUrl) {
        PcLstUrl = pcLstUrl;
    }

    public String getPeriodType() {
        return PeriodType;
    }

    public void setPeriodType(String periodType) {
        PeriodType = periodType;
    }

    public Integer getPersonNum() {
        return PersonNum;
    }

    public void setPersonNum(Integer personNum) {
        PersonNum = personNum;
    }

    public Integer getPraise() {
        return Praise;
    }

    public void setPraise(Integer praise) {
        Praise = praise;
    }

    public Integer getRecruited() {
        return Recruited;
    }

    public void setRecruited(Integer recruited) {
        Recruited = recruited;
    }

    public Integer getRecruitNumber() {
        return RecruitNumber;
    }

    public void setRecruitNumber(Integer recruitNumber) {
        RecruitNumber = recruitNumber;
    }

    public Integer getRunStatus() {
        return RunStatus;
    }

    public void setRunStatus(Integer runStatus) {
        RunStatus = runStatus;
    }

    public Number getSafe() {
        return Safe;
    }

    public void setSafe(Number safe) {
        Safe = safe;
    }

    public String getSafeName() {
        return SafeName;
    }

    public void setSafeName(String safeName) {
        SafeName = safeName;
    }

    public Integer getSafeType() {
        return SafeType;
    }

    public void setSafeType(Integer safeType) {
        SafeType = safeType;
    }

    public Number getScopeType() {
        return ScopeType;
    }

    public void setScopeType(Number scopeType) {
        ScopeType = scopeType;
    }

    public Double getScore() {
        return Score;
    }

    public void setScore(Double score) {
        Score = score;
    }

    public Integer getSingleRecruit() {
        return SingleRecruit;
    }

    public void setSingleRecruit(Integer singleRecruit) {
        SingleRecruit = singleRecruit;
    }

    public String getSpecialityType() {
        return SpecialityType;
    }

    public void setSpecialityType(String specialityType) {
        SpecialityType = specialityType;
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

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String statusName) {
        StatusName = statusName;
    }

    public Integer getStick() {
        return Stick;
    }

    public void setStick(Integer stick) {
        Stick = stick;
    }

    public String getStickendTime() {
        return StickendTime;
    }

    public void setStickendTime(String stickendTime) {
        StickendTime = stickendTime;
    }

    public Integer getStickHistory() {
        return StickHistory;
    }

    public void setStickHistory(Integer stickHistory) {
        StickHistory = stickHistory;
    }

    public String getStickHistoryName() {
        return StickHistoryName;
    }

    public void setStickHistoryName(String stickHistoryName) {
        StickHistoryName = stickHistoryName;
    }

    public String getStickName() {
        return StickName;
    }

    public void setStickName(String stickName) {
        StickName = stickName;
    }

    public String getStickTime() {
        return StickTime;
    }

    public void setStickTime(String stickTime) {
        StickTime = stickTime;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public Number getTraffic() {
        return Traffic;
    }

    public void setTraffic(Number traffic) {
        Traffic = traffic;
    }

    public String getTrafficName() {
        return TrafficName;
    }

    public void setTrafficName(String trafficName) {
        TrafficName = trafficName;
    }

    public Integer getTrafficType() {
        return TrafficType;
    }

    public void setTrafficType(Integer trafficType) {
        TrafficType = trafficType;
    }

    public String getSpecialityTypeName() {
        return SpecialityTypeName;
    }

    public void setSpecialityTypeName(String specialityTypeName) {
        SpecialityTypeName = specialityTypeName;
    }
}
