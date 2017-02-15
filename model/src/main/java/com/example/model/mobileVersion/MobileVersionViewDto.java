package com.example.model.mobileVersion;

import com.example.model.user.CreateOperationDto;
import com.example.model.user.ModifyOperationDto;

/**
 * 项目名称：NbVolunteerAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/9/17 17:53
 * 修改备注：
 */
public class MobileVersionViewDto {
    private String AttachmentId;//(string, optional): 获取或设置
    private String Description;// (string, optional): 获取或设置
    private CreateOperationDto CreateOperation;//(CreateOperationDto, optional): 登记信息
    private ModifyOperationDto ModifyOperation;// (ModifyOperationDto, optional): 更新信息
    private String Id;//(string, optional): 获取或设置
    private String VersionName;//(string, optional): 获取或设置
    private String VersionNumber;//(string, optional): 获取或设置

    public String getAttachmentId() {
        return AttachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        AttachmentId = attachmentId;
    }

    public CreateOperationDto getCreateOperation() {
        return CreateOperation;
    }

    public void setCreateOperation(CreateOperationDto createOperation) {
        CreateOperation = createOperation;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public ModifyOperationDto getModifyOperation() {
        return ModifyOperation;
    }

    public void setModifyOperation(ModifyOperationDto modifyOperation) {
        ModifyOperation = modifyOperation;
    }

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String versionName) {
        VersionName = versionName;
    }

    public String getVersionNumber() {
        return VersionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        VersionNumber = versionNumber;
    }
}
