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

public class SharemessageQueryOptionDto {

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
     * CreateUserName : string
     * CreateTime : 2019-05-14T01:21:08.453Z
     * CreateUserId : string
     * ModifyUserId : string
     * ModifyUserName : string
     * ModifyTime : 2019-05-14T01:21:08.453Z
     * KeyWord : string
     * PageIndex : 0
     * PageSize : 0
     * Sorts : {}
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
    private String CreateUserName;
    private String CreateTime;
    private String CreateUserId;
    private String ModifyUserId;
    private String ModifyUserName;
    private String ModifyTime;
    private String KeyWord;
    private Integer PageIndex;
    private Integer PageSize;
    private SortsBean Sorts;

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

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String CreateUserName) {
        this.CreateUserName = CreateUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(String CreateUserId) {
        this.CreateUserId = CreateUserId;
    }

    public String getModifyUserId() {
        return ModifyUserId;
    }

    public void setModifyUserId(String ModifyUserId) {
        this.ModifyUserId = ModifyUserId;
    }

    public String getModifyUserName() {
        return ModifyUserName;
    }

    public void setModifyUserName(String ModifyUserName) {
        this.ModifyUserName = ModifyUserName;
    }

    public String getModifyTime() {
        return ModifyTime;
    }

    public void setModifyTime(String ModifyTime) {
        this.ModifyTime = ModifyTime;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String KeyWord) {
        this.KeyWord = KeyWord;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int PageSize) {
        this.PageSize = PageSize;
    }

    public SortsBean getSorts() {
        return Sorts;
    }

    public void setSorts(SortsBean Sorts) {
        this.Sorts = Sorts;
    }

    public static class SortsBean {
    }
}
