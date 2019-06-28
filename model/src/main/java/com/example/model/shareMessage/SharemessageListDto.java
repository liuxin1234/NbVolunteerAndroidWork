package com.example.model.shareMessage;

/**
 * Created by
 * 项目名称：com.example.model.shareMessage
 * 项目日期：2019/5/14
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class SharemessageListDto {

    /**
     * Id : string
     * ShareId : string
     * VolunteerId : string
     * VolunteerName : string
     * Statu : 0
     * Description : string
     * IsPublish : 0
     * IsDeleted : true
     * Version : 0
     */

    private String Id;
    private String ShareId;
    private String VolunteerId;
    private String VolunteerName;
    private Integer Statu;
    private String Description;
    private Integer IsPublish;
    private boolean IsDeleted;
    private Integer Version;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getShareId() {
        return ShareId;
    }

    public void setShareId(String ShareId) {
        this.ShareId = ShareId;
    }

    public String getVolunteerId() {
        return VolunteerId;
    }

    public void setVolunteerId(String VolunteerId) {
        this.VolunteerId = VolunteerId;
    }

    public String getVolunteerName() {
        return VolunteerName;
    }

    public void setVolunteerName(String VolunteerName) {
        this.VolunteerName = VolunteerName;
    }

    public int getStatu() {
        return Statu;
    }

    public void setStatu(int Statu) {
        this.Statu = Statu;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getIsPublish() {
        return IsPublish;
    }

    public void setIsPublish(int IsPublish) {
        this.IsPublish = IsPublish;
    }

    public boolean isIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(boolean IsDeleted) {
        this.IsDeleted = IsDeleted;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }
}
