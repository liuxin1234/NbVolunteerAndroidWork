package com.example.model.user;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/3 10:53
 * 修改备注：
 */
public class UserViewDto {
    private static final String TAG = "UserViewDto";

    private CreateOperationDto CreateOperation;// (CreateOperationDto, optional): 登记信息
    private ModifyOperationDto ModifyOperation;// (ModifyOperationDto, optional): 更新信息
    private RoleSimpleViewDto Roles;//(Array[RoleSimpleViewDto], optional): 授权角色
    private GroupSimpleViewDto Groups;//(Array[GroupSimpleViewDto], optional): 所属群组
    private String Id;//(string, optional): 获取或设置 人员标识
    private int Version;//(integer, optional): 获取或设置 数据版本
    private String UserName;// (string, optional): 获取或设置 用户名
    private String RealName;//(string, optional): 获取或设置 用户姓名
    private String Birthday;//(string, optional): 出生日期
    private String Comment;// (string, optional): 获取或设置 描述
    private String Email;// (string, optional): 获取或设置 邮件
    private boolean EmailConfirmed;// (boolean, optional): 邮件确认状态
    private String PinYin;// (string, optional): 获取或设置 全拼
    private String ShortPinYin;//(string, optional): 获取或设置 简拼
    private String IdNumber;// (string, optional): 获取或设置 身份证
    private int LoginErrorNumber;// (integer, optional): 获取或设置 登录错误次数
    private boolean IsActive;//(boolean, optional): 获取或设置 是否激活
    private String LastLoginTime;//(string, optional): 获取或设置 上次登录时间
    private String LoginTime;//(string, optional): 获取或设置 登录时间
    private String Mobile;//(string, optional): 获取或设置 手机号码
    private boolean MobileConfirmed;//(boolean, optional): 手机确认状态
    private int Sex;//(integer, optional): 获取或设置 性别= ['0', '1'],
    private int SortIndex;//(integer, optional): 获取或设置 排序
    private int UserType;//(integer, optional): 获取或设置 用户类型
    private String OrganizationId;// (string, optional): 获取或设置 所属部门
    private String OrganizationName;//(string, optional): 获取或设置 所属部门
    private String JobId;//(string, optional): 获取或设置 岗位
    private String JobName;//(string, optional): 获取或设置 岗位
    private boolean IsLocked;//(boolean, optional): 锁定

    public CreateOperationDto getCreateOperation() {
        return CreateOperation;
    }

    public void setCreateOperation(CreateOperationDto createOperation) {
        CreateOperation = createOperation;
    }

    public ModifyOperationDto getModifyOperation() {
        return ModifyOperation;
    }

    public void setModifyOperation(ModifyOperationDto modifyOperation) {
        ModifyOperation = modifyOperation;
    }

    public RoleSimpleViewDto getRoles() {
        return Roles;
    }

    public void setRoles(RoleSimpleViewDto roles) {
        Roles = roles;
    }

    public GroupSimpleViewDto getGroups() {
        return Groups;
    }

    public void setGroups(GroupSimpleViewDto groups) {
        Groups = groups;
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

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

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

    public boolean isEmailConfirmed() {
        return EmailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        EmailConfirmed = emailConfirmed;
    }

    public String getPinYin() {
        return PinYin;
    }

    public void setPinYin(String pinYin) {
        PinYin = pinYin;
    }

    public String getShortPinYin() {
        return ShortPinYin;
    }

    public void setShortPinYin(String shortPinYin) {
        ShortPinYin = shortPinYin;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public int getLoginErrorNumber() {
        return LoginErrorNumber;
    }

    public void setLoginErrorNumber(int loginErrorNumber) {
        LoginErrorNumber = loginErrorNumber;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public String getLastLoginTime() {
        return LastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        LastLoginTime = lastLoginTime;
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

    public boolean isMobileConfirmed() {
        return MobileConfirmed;
    }

    public void setMobileConfirmed(boolean mobileConfirmed) {
        MobileConfirmed = mobileConfirmed;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public int getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(int sortIndex) {
        SortIndex = sortIndex;
    }

    public int getUserType() {
        return UserType;
    }

    public void setUserType(int userType) {
        UserType = userType;
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

    public boolean isLocked() {
        return IsLocked;
    }

    public void setLocked(boolean locked) {
        IsLocked = locked;
    }
}
