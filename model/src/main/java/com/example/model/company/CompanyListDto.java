package com.example.model.company;
/*
 *
 * Created by Ge on 2016/8/5  10:46.
 *
 */

public class CompanyListDto {
    private String AreaName;// 区域名称
    private String AreaId;//区域标识
    private String Id;//企业单位标识
    private int Version;//数据版本
    private String UserId;//系统用户Id
    private String UserName;//
    private String Password;//
    private String CompanyName;//企业单位
    private String AreaCode;//区域编号
    private String Addr;//地址
    private String Person;//联系人
    private String Tel;//联系电话
    private String Mobile;//手机
    private int Status;//状态
    private String LicenseUrl;//执照url
    private String OrganizationUrl;//组织结构url
    private String CreateTime;//
    private String GetTime;//申请时间
    private String Reject;//驳回说明
    private Number Lng;//经度
    private Number Lat;//纬度
    private String Linker;//法人联系人
    private String LinkerTel;//法人电话
    private String AmountSrc;//资金来源
    private String OrgCode;//组织结构代码
    private Number ServiceTime;//服务时长
    private int QuartersNum;//岗位数
    private int ActivityNum;//活动数
    private String RegisterTime;//注册时间
    private int IsAuth;//是否认证
    private int Member;//成员人员
    private String Description;//描述
    private String Listurl;//列表图片
    private String AppLstUrl ;//(string, optional): App图片
    private int Registration;//登记备案
    private String Institution;//登记备案机构
    private int SortIndex;//排序
    private String OrganizationId;//所属组织Id
    private String OrganizationName;//
    private String ServiceType;//服务类型
    private String ServiceTypeOther;//服务类型(其他)
    private String LanguageType;//语种类型
    private boolean IsDeleted;//删除状态

    @Override
    public String toString() {
        return "CompanyListDto{" +
                "AreaName='" + AreaName + '\'' +
                ", AreaId='" + AreaId + '\'' +
                ", Id='" + Id + '\'' +
                ", Version=" + Version +
                ", UserId='" + UserId + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", AreaCode='" + AreaCode + '\'' +
                ", Addr='" + Addr + '\'' +
                ", Person='" + Person + '\'' +
                ", Tel='" + Tel + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Status=" + Status +
                ", LicenseUrl='" + LicenseUrl + '\'' +
                ", OrganizationUrl='" + OrganizationUrl + '\'' +
                ", CreateTime='" + CreateTime + '\'' +
                ", GetTime='" + GetTime + '\'' +
                ", Reject='" + Reject + '\'' +
                ", Lng=" + Lng +
                ", Lat=" + Lat +
                ", Linker='" + Linker + '\'' +
                ", LinkerTel='" + LinkerTel + '\'' +
                ", AmountSrc='" + AmountSrc + '\'' +
                ", OrgCode='" + OrgCode + '\'' +
                ", ServiceTime=" + ServiceTime +
                ", QuartersNum=" + QuartersNum +
                ", ActivityNum=" + ActivityNum +
                ", RegisterTime='" + RegisterTime + '\'' +
                ", IsAuth=" + IsAuth +
                ", Member=" + Member +
                ", Description='" + Description + '\'' +
                ", Listurl='" + Listurl + '\'' +
                ", Registration=" + Registration +
                ", Institution='" + Institution + '\'' +
                ", SortIndex=" + SortIndex +
                ", OrganizationId='" + OrganizationId + '\'' +
                ", OrganizationName='" + OrganizationName + '\'' +
                ", ServiceType='" + ServiceType + '\'' +
                ", ServiceTypeOther='" + ServiceTypeOther + '\'' +
                ", LanguageType='" + LanguageType + '\'' +
                ", IsDeleted=" + IsDeleted +
                '}';
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getAreaId() {
        return AreaId;
    }

    public void setAreaId(String areaId) {
        AreaId = areaId;
    }

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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
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

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getLicenseUrl() {
        return LicenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        LicenseUrl = licenseUrl;
    }

    public String getOrganizationUrl() {
        return OrganizationUrl;
    }

    public void setOrganizationUrl(String organizationUrl) {
        OrganizationUrl = organizationUrl;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getGetTime() {
        return GetTime;
    }

    public void setGetTime(String getTime) {
        GetTime = getTime;
    }

    public String getReject() {
        return Reject;
    }

    public void setReject(String reject) {
        Reject = reject;
    }

    public Number getLng() {
        return Lng;
    }

    public void setLng(Number lng) {
        Lng = lng;
    }

    public Number getLat() {
        return Lat;
    }

    public void setLat(Number lat) {
        Lat = lat;
    }

    public String getLinker() {
        return Linker;
    }

    public void setLinker(String linker) {
        Linker = linker;
    }

    public String getLinkerTel() {
        return LinkerTel;
    }

    public void setLinkerTel(String linkerTel) {
        LinkerTel = linkerTel;
    }

    public String getAmountSrc() {
        return AmountSrc;
    }

    public void setAmountSrc(String amountSrc) {
        AmountSrc = amountSrc;
    }

    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String orgCode) {
        OrgCode = orgCode;
    }

    public Number getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(Number serviceTime) {
        ServiceTime = serviceTime;
    }

    public int getQuartersNum() {
        return QuartersNum;
    }

    public void setQuartersNum(int quartersNum) {
        QuartersNum = quartersNum;
    }

    public int getActivityNum() {
        return ActivityNum;
    }

    public void setActivityNum(int activityNum) {
        ActivityNum = activityNum;
    }

    public String getRegisterTime() {
        return RegisterTime;
    }

    public void setRegisterTime(String registerTime) {
        RegisterTime = registerTime;
    }

    public int getIsAuth() {
        return IsAuth;
    }

    public void setIsAuth(int isAuth) {
        IsAuth = isAuth;
    }

    public int getMember() {
        return Member;
    }

    public void setMember(int member) {
        Member = member;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getListurl() {
        return Listurl;
    }

    public void setListurl(String listurl) {
        Listurl = listurl;
    }

    public int getRegistration() {
        return Registration;
    }

    public void setRegistration(int registration) {
        Registration = registration;
    }

    public String getInstitution() {
        return Institution;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }

    public int getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(int sortIndex) {
        SortIndex = sortIndex;
    }

    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(String organizationId) {
        OrganizationId = organizationId;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public String getServiceTypeOther() {
        return ServiceTypeOther;
    }

    public void setServiceTypeOther(String serviceTypeOther) {
        ServiceTypeOther = serviceTypeOther;
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

    public String getAppLstUrl() {
        return AppLstUrl;
    }

    public void setAppLstUrl(String appLstUrl) {
        AppLstUrl = appLstUrl;
    }
}
