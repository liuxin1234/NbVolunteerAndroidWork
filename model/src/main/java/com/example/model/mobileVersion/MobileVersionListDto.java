package com.example.model.mobileVersion;

/**
 * 项目名称：NbVolunteerAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/9/17 17:51
 * 修改备注：
 */
public class MobileVersionListDto {
    private String Id;//(string, optional): 获取或设置
    private String VersionName;//(string, optional): 获取或设置
    private String VersionNumber;//(string, optional): 获取或设置
    private String AttchUrl;
    private String AttachmentId;
    private Integer Type;
    private Integer OnOff;
    private Integer TipsOnOff;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getAttchUrl() {
        return AttchUrl;
    }

    public void setAttchUrl(String attchUrl) {
        AttchUrl = attchUrl;
    }

    public String getAttachmentId() {
        return AttachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        AttachmentId = attachmentId;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public Integer getOnOff() {
        return OnOff;
    }

    public void setOnOff(Integer onOff) {
        OnOff = onOff;
    }

    public Integer getTipsOnOff() {
        return TipsOnOff;
    }

    public void setTipsOnOff(Integer tipsOnOff) {
        TipsOnOff = tipsOnOff;
    }
}
