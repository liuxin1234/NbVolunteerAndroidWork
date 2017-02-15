package com.example.model.user;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/3 10:48
 * 修改备注：
 */
public class CreateOperationDto {
    private static final String TAG = "CreateOperationDto";

    private String CreateUserName;// (string, optional): 创建人员姓名
    private String CreateUserId;//(string, optional): 登记人员
    private String CreateTime;//(string, optional): 创建时间

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String createUserName) {
        CreateUserName = createUserName;
    }

    public String getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(String createUserId) {
        CreateUserId = createUserId;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
}
