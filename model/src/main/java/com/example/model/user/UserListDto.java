package com.example.model.user;

/**
 * 用户登录获取用户数据
 * Created by Administrator on 2016/8/17.
 */
public class UserListDto {
    private static final String TAG = "UserListDto";

    private String Id;// (string, optional): 获取或设置 人员标识 ,
    private String Version;// (integer, optional): 获取或设置 数据版本 ,
    private String UserName;// (string, optional): 获取或设置 用户名 ,
    private String RealName;// (string, optional): 获取或设置 用户姓名 ,
    private String Birthday;// (string, optional): 出生日期 ,
    private String Comment;// (string, optional): 获取或设置 描述 ,
    private String Email;// (string, optional): 获取或设置 邮件 ,
    private Boolean EmailConfirmed;// (boolean, optional): 邮件确认状态 ,
    private String PinYin;// (string, optional): 获取或设置 全拼 ,
    private String ShortPinYin;// (string, optional): 获取或设置 简拼 ,
    private String IdNumber;// (string, optional): 获取或设置 身份证 ,
    private Integer LoginErrorNumber;// (integer, optional): 获取或设置 登录错误次数 ,
    private Boolean IsActive;// (boolean, optional): 获取或设置 是否激活 ,
    private String LastLoginTime;// (string, optional): 获取或设置 上次登录时间 ,
    private String LoginTime;// (string, optional): 获取或设置 登录时间 ,
    private String Mobile;// (string, optional): 获取或设置 手机号码 ,
    private Boolean MobileConfirmed;// (boolean, optional): 手机确认状态 ,
    private Integer Sex;// (integer, optional): 获取或设置 性别 = ['0', '1'],
    private Integer SortIndex;// (integer, optional): 获取或设置 排序 ,
    private Integer UserType;// (integer, optional): 获取或设置 用户类型 ,
    private String OrganizationId;// (string, optional): 获取或设置 所属部门 ,
    private String OrganizationName;// (string, optional): 获取或设置 所属部门 ,
    private String JobId;// (string, optional): 获取或设置 岗位 ,
    private String JobName;// (string, optional): 获取或设置 岗位 ,
    private Boolean IsLocked;// (boolean, optional): 锁定

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Boolean getEmailConfirmed() {
        return EmailConfirmed;
    }

    public void setEmailConfirmed(Boolean emailConfirmed) {
        EmailConfirmed = emailConfirmed;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public Boolean getActive() {
        return IsActive;
    }

    public void setActive(Boolean active) {
        IsActive = active;
    }

    public Boolean getLocked() {
        return IsLocked;
    }

    public void setLocked(Boolean locked) {
        IsLocked = locked;
    }

    public String getJobId() {
        return JobId;
    }

    public void setJobId(String jobId) {
        JobId = jobId;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }

    public String getLastLoginTime() {
        return LastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        LastLoginTime = lastLoginTime;
    }

    public Integer getLoginErrorNumber() {
        return LoginErrorNumber;
    }

    public void setLoginErrorNumber(Integer loginErrorNumber) {
        LoginErrorNumber = loginErrorNumber;
    }

    public String getLoginTime() {
        return LoginTime;
    }

    public void setLoginTime(String loginTime) {
        LoginTime = loginTime;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public Boolean getMobileConfirmed() {
        return MobileConfirmed;
    }

    public void setMobileConfirmed(Boolean mobileConfirmed) {
        MobileConfirmed = mobileConfirmed;
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

    public String getPinYin() {
        return PinYin;
    }

    public void setPinYin(String pinYin) {
        PinYin = pinYin;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public Integer getSex() {
        return Sex;
    }

    public void setSex(Integer sex) {
        Sex = sex;
    }

    public String getShortPinYin() {
        return ShortPinYin;
    }

    public void setShortPinYin(String shortPinYin) {
        ShortPinYin = shortPinYin;
    }

    public Integer getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        SortIndex = sortIndex;
    }

    public static String getTAG() {
        return TAG;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public Integer getUserType() {
        return UserType;
    }

    public void setUserType(Integer userType) {
        UserType = userType;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
