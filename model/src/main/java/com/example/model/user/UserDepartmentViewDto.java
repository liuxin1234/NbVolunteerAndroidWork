package com.example.model.user;
/*
 *
 * Created by Ge on 2016/10/11  14:07.
 *
 */

public class UserDepartmentViewDto {

    private String Id; //(string, optional): 用户部门岗位关系标识 ,
    private Integer Version; //(integer, optional): 数据版本 ,
    private String UserId; //(string, optional): 用户标识 ,
    private String UserRealName;  //(string, optional): 用户标识 ,
    private String OrganizationId; //(string, optional): 所属部门标识 ,
    private String OrganizationName; //(string, optional): 所属部门标识 ,
    private String JobId; //(string, optional): 所属岗位标识 ,
    private String JobName; //(string, optional): 所属岗位标识 ,
    private String IsMain; //(boolean, optional): 是否主部门 ,
    private CreateOperationDto CreateOperation; // (CreateOperationDto, optional): 登记信息 ,
    private ModifyOperationDto ModifyOperation; //(ModifyOperationDto, optional): 更新信息

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Integer getVersion() {
        return Version;
    }

    public void setVersion(Integer version) {
        Version = version;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserRealName() {
        return UserRealName;
    }

    public void setUserRealName(String userRealName) {
        UserRealName = userRealName;
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

    public String getIsMain() {
        return IsMain;
    }

    public void setIsMain(String isMain) {
        IsMain = isMain;
    }

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
}
