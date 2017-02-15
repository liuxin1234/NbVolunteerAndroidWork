package com.example.model.user;

import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/3 13:22
 * 修改备注：
 */
public class UserDto {
    private static final String TAG = "UserDto";
    private String Id;//(string): 获取或设置 人员标识
    private int Version;//(integer): 获取或设置 数据版本
    private String UserName;//(string): 获取或设置 用户名
    private String RealName;//(string): 获取或设置 用户姓名
    private String Birthday;// (string, optional): 出生日期
    private String Comment;//(string, optional): 获取或设置 描述
    private String Email;//(string, optional): 获取或设置 邮件
    private String IdNumber;//(string, optional): 获取或设置 身份证
    private boolean IsActive;// (boolean, optional): 获取或设置 是否激活
    private String Mobile;//(string, optional): 获取或设置 手机号码
    private int Sex;//(integer): 获取或设置 性别= ['0', '1'],
    private int SortIndex;//(integer, optional): 获取或设置 排序
    private int UserType;// (integer): 获取或设置 用户类型
    private String InitPwd;//(string, optional): 获取或设置 初始密码
    private String OrganizationId;//(string): 获取或设置 所属部门
    private String JobId;//(string, optional): 获取或设置 岗位
    private boolean IsLocked;//(boolean, optional): 锁定
    private List<String> GroupIds;// (Array[string], optional): 所属群组
    private List<String> RoleIds;// (Array[string], optional): 授权角色

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

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
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

    public String getInitPwd() {
        return InitPwd;
    }

    public void setInitPwd(String initPwd) {
        InitPwd = initPwd;
    }

    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(String organizationId) {
        OrganizationId = organizationId;
    }

    public String getJobId() {
        return JobId;
    }

    public void setJobId(String jobId) {
        JobId = jobId;
    }

    public boolean isLocked() {
        return IsLocked;
    }

    public void setLocked(boolean locked) {
        IsLocked = locked;
    }

    public List<String> getGroupIds() {
        return GroupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        GroupIds = groupIds;
    }

    public List<String> getRoleIds() {
        return RoleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        RoleIds = roleIds;
    }
}
