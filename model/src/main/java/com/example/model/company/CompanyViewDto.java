package com.example.model.company;

import com.example.model.activity.ActivityViewDto;

import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/17 14:01
 * 修改备注：
 */
public class CompanyViewDto {
    private List<ActivityViewDto> Activitys;// (Array[ActivityDto], optional): 所属活动列表
    private String AreaName;// (string, optional): 区域名称
    private String AreaId;// (string, optional): 区域标识
    private String Id;//  (string, optional): 获取或设置 企业单位标识
    private Integer Version;// (integer, optional): 获取或设置 数据版本
    private String UserId;// (string, optional): 获取或设置 系统用户Id
    private String UserName;//  (string),
    private String Password;// (string, optional),
    private String CompanyName;// (string, optional): 获取或设置 企业单位
    private String AreaCode;// (string, optional): 获取或设置 区域编号
    private String Addr;// (string, optional): 获取或设置 地址
    private String Person;// (string, optional): 获取或设置 联系人
    private String Tel;// (string, optional): 获取或设置 联系电话
    private String Mobile;// (string, optional): 获取或设置 手机
    private Integer Status;// (integer, optional): 获取或设置 状态
    private String LicenseUrl;// (string, optional): 获取或设置 执照url
    private String OrganizationUrl;//  (string, optional): 获取或设置 组织结构url
    private String CreateTime;//  (string, optional),
    private String GetTime;// (string, optional): 获取或设置 申请时间
    private String Reject;//  (string, optional): 获取或设置 驳回说明
    private Number Lng;// (number, optional): 获取或设置 经度
    private Number Lat;// (number, optional): 获取或设置 纬度
    private String Linker;// (string, optional): 获取或设置 法人联系人
    private String LinkerTel;// (string, optional): 获取或设置 法人电话
    private String LinkerMobile;// (string, optional): 获取或设置 法人手机
    private String AmountSrc;// (string, optional): 获取或设置 资金来源
    private String OrgCode;// (string, optional): 获取或设置 组织结构代码
    private Number ServiceTime;// (number, optional): 获取或设置 服务时长
    private Integer QuartersNum;// (integer, optional): 获取或设置 岗位数
    private Integer ActivityNum;// (integer, optional): 获取或设置 活动数
    private String RegisterTime;//  (string, optional): 获取或设置 注册时间
    private Integer IsAuth;// (integer, optional): 获取或设置 是否认证
    private Integer Member;// (integer, optional): 获取或设置 成员人员
    private String Description;// (string, optional): 获取或设置 描述
    private String Listurl;// (string, optional): 获取或设置 列表图片
    private Integer Registration;//  (integer, optional): 获取或设置 登记备案
    private String Institution;// (string, optional): 获取或设置 登记备案机构
    private Integer SortIndex;// (integer, optional): 获取或设置 排序
    private String OrganizationId;// (string, optional): 获取或设置 所属组织Id
    private String OrganizationName;// (string, optional),
    private String ServiceType;// (string, optional): 获取或设置 服务类型
    private String ServiceTypeName;// (string, optional): 获取或设置 服务类型
    private String ServiceTypeOther;// (string, optional): 获取或设置 服务类型(其他)
    private String LanguageType;// (string, optional): 获取或设置 语种类型
    private Boolean IsDeleted;//  (boolean, optional): 获取或设置 删除状态
    private String AppLstUrl ;//(string, optional): App图片
    private String Honors ;//(string, optional): 组织荣誉


    public String getHonors() {
        return Honors;
    }

    public void setHonors(String honors) {
        Honors = honors;
    }

    public String getAppLstUrl() {
        return AppLstUrl;
    }

    public void setAppLstUrl(String appLstUrl) {
        AppLstUrl = appLstUrl;
    }

    public Integer getActivityNum() {
        return ActivityNum;
    }

    public void setActivityNum(Integer activityNum) {
        ActivityNum = activityNum;
    }

    public List<ActivityViewDto> getActivitys() {
        return Activitys;
    }

    public void setActivitys(List<ActivityViewDto> activitys) {
        Activitys = activitys;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getAmountSrc() {
        return AmountSrc;
    }

    public void setAmountSrc(String amountSrc) {
        AmountSrc = amountSrc;
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

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getGetTime() {
        return GetTime;
    }

    public void setGetTime(String getTime) {
        GetTime = getTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getInstitution() {
        return Institution;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }

    public Integer getIsAuth() {
        return IsAuth;
    }

    public void setIsAuth(Integer isAuth) {
        IsAuth = isAuth;
    }

    public Boolean getDeleted() {
        return IsDeleted;
    }

    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String languageType) {
        LanguageType = languageType;
    }

    public Number getLat() {
        return Lat;
    }

    public void setLat(Number lat) {
        Lat = lat;
    }

    public String getLicenseUrl() {
        return LicenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        LicenseUrl = licenseUrl;
    }

    public String getLinker() {
        return Linker;
    }

    public void setLinker(String linker) {
        Linker = linker;
    }

    public String getLinkerMobile() {
        return LinkerMobile;
    }

    public void setLinkerMobile(String linkerMobile) {
        LinkerMobile = linkerMobile;
    }

    public String getLinkerTel() {
        return LinkerTel;
    }

    public void setLinkerTel(String linkerTel) {
        LinkerTel = linkerTel;
    }

    public String getListurl() {
        return Listurl;
    }

    public void setListurl(String listurl) {
        Listurl = listurl;
    }

    public Number getLng() {
        return Lng;
    }

    public void setLng(Number lng) {
        Lng = lng;
    }

    public Integer getMember() {
        return Member;
    }

    public void setMember(Integer member) {
        Member = member;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
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

    public String getOrganizationUrl() {
        return OrganizationUrl;
    }

    public void setOrganizationUrl(String organizationUrl) {
        OrganizationUrl = organizationUrl;
    }

    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String orgCode) {
        OrgCode = orgCode;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public Integer getQuartersNum() {
        return QuartersNum;
    }

    public void setQuartersNum(Integer quartersNum) {
        QuartersNum = quartersNum;
    }

    public String getRegisterTime() {
        return RegisterTime;
    }

    public void setRegisterTime(String registerTime) {
        RegisterTime = registerTime;
    }

    public Integer getRegistration() {
        return Registration;
    }

    public void setRegistration(Integer registration) {
        Registration = registration;
    }

    public String getReject() {
        return Reject;
    }

    public void setReject(String reject) {
        Reject = reject;
    }

    public Number getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(Number serviceTime) {
        ServiceTime = serviceTime;
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

    public Integer getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        SortIndex = sortIndex;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
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

    public Integer getVersion() {
        return Version;
    }

    public void setVersion(Integer version) {
        Version = version;
    }

    public String getServiceTypeName() {
        return ServiceTypeName;
    }

    public void setServiceTypeName(String serviceTypeName) {
        ServiceTypeName = serviceTypeName;
    }
}
