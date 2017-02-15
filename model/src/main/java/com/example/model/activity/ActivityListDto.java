package com.example.model.activity;

import java.io.Serializable;

/**
 * 项目名称：BaseAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/7/23 15:49
 * 修改备注：
 */
public class ActivityListDto implements Serializable {
    private String Id;//(string, optional): 获取或设置 活动标识
    private String ActivityName;//(string, optional): 获取或设置 活动名称
    private String StartTime;//(string, optional): 获取或设置 活动开始时间
    private String FinishTime;// (string, optional): 获取或设置 活动结束时间
    private String Addr;//(string, optional): 获取或设置 地址
    private String JobText;//(string, optional),
    private Integer RecruitNumber;// (integer, optional): 获取或设置 招募人数
    private Integer Recruited;//(integer, optional): 获取或设置 已招募
    private Integer Status;//(integer, optional): 获取或设置 状态
    private String StatusName;//(string, optional),
    private Integer SingleRecruit;//(integer, optional),
    private String EndTime;// (string, optional),
    private Number LengthTime;// (number, optional),
    private String DaySTime;//(string, optional),
    private String DayETime;//(string, optional),
    private String CompanyId;// (string, optional),
    private Number Lng;//(number, optional),
    private Number Lat;//(number, optional),
    private Integer Type;//(integer, optional),
    private String AreaCode;// (string, optional),
    private String AreaName;//(string, optional),
    private String CompanyName;//(string, optional)
    private String LstUrl;//(string, optional): 图片Id
    private String PcLstUrl;//(string, optional): Pc图片
    private String AppLstUrl;//(string, optional): App图片
    private String OperationState;//(string, optional): 当前项目运行状态
    private String SpecialityType;// (string, optional),
    private String SpecialityTypeName;// (string, optional),
    private String Linker;// (string, optional): 获取或设置 活动联系人 ,
    private String Tel;// (string, optional): 获取或设置 活动联电话 ,
    private String Mobile;// (string, optional): 获取或设置 活动联系手机 ,
    private String LanguageType;// (string, optional): 获取或设置 语种

    public String getOperationState() {
        return OperationState;
    }

    public void setOperationState(String operationState) {
        OperationState = operationState;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
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

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
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

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getFinishTime() {
        return FinishTime;
    }

    public void setFinishTime(String finishTime) {
        FinishTime = finishTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getJobText() {
        return JobText;
    }

    public void setJobText(String jobText) {
        JobText = jobText;
    }


    public Number getLengthTime() {
        return LengthTime;
    }

    public void setLengthTime(Number lengthTime) {
        LengthTime = lengthTime;
    }

    public Number getLat() {
        return Lat;
    }

    public void setLat(Number lat) {
        Lat = lat;
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

    public String getPcLstUrl() {
        return PcLstUrl;
    }

    public void setPcLstUrl(String pcLstUrl) {
        PcLstUrl = pcLstUrl;
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

    public Integer getSingleRecruit() {
        return SingleRecruit;
    }

    public void setSingleRecruit(Integer singleRecruit) {
        SingleRecruit = singleRecruit;
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

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public String getSpecialityType() {
        return SpecialityType;
    }

    public void setSpecialityType(String specialityType) {
        SpecialityType = specialityType;
    }

    public String getSpecialityTypeName() {
        return SpecialityTypeName;
    }

    public void setSpecialityTypeName(String specialityTypeName) {
        SpecialityTypeName = specialityTypeName;
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

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String languageType) {
        LanguageType = languageType;
    }
}
