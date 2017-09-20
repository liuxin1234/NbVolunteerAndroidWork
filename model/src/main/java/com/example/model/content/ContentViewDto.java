package com.example.model.content;

import com.example.model.user.CreateOperationDto;
import com.example.model.user.ModifyOperationDto;

/**
 * Created by
 * 项目名称：com.example.model.content
 * 项目日期：2017/9/19
 * 作者：liux
 * 功能：
 */

public class ContentViewDto {
    private String OrganizationId;// (string, optional),
    private String PermissionUserId;// (string, optional),
    private String PermissionUserName;// (string, optional),
    private String Contents;// (string, optional),
    private String SmallImgUrl;// (string, optional),
    private String BigImgUrl;// (string, optional),
    private String AttachmentId;// (string, optional),
    private String Cn_ID;// (string, optional),
    private String LngType;// (string, optional),
    private CreateOperationDto CreateOperation;// (CreateOperationDto, optional),
    private ModifyOperationDto ModifyOperation;// (ModifyOperationDto, optional),
    private String Id;// (string, optional),
    private String CategoryId;// (string, optional),
    private String CategoryName;// (string, optional),
    private String OrganizationName;// (string, optional),
    private String ContentName;// (string, optional),
    private String SubContentName;// (string, optional),
    private String Description;// (string, optional),
    private String CategoryPathId;// (string, optional),
    private String CategoryPathName;// (string, optional),
    private Boolean IsOutsideLink;// (boolean, optional),
    private String OutsideUrl;// (string, optional),
    private String Author;// (string, optional),
    private String Source;// (string, optional),
    private String Keyword;// (string, optional),
    private Boolean IsTop;// (boolean, optional),
    private Boolean IsPic;// (boolean, optional),
    private Integer VisiteRecord;// (integer, optional),
    private String PubDepartment;// (string, optional),
    private String  PubDateTime;// (string, optional),
    private Integer SortIndex;// (integer, optional),
    private Boolean IsPermission;// (boolean, optional),
    private String PermissionTime;// (string, optional),
    private String SmallImgViewPc;// (string, optional),
    private String SmallImgViewApp;// (string, optional),
    private String BigImgViewPc;// (string, optional),
    private String BigImgViewApp;// (string, optional),
    private Boolean IsNeedTranslate;// (boolean, optional),
    private Boolean IsTranslate;// (boolean, optional)

    public ContentViewDto() {
    }

    public String getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(String organizationId) {
        OrganizationId = organizationId;
    }

    public String getPermissionUserId() {
        return PermissionUserId;
    }

    public void setPermissionUserId(String permissionUserId) {
        PermissionUserId = permissionUserId;
    }

    public String getPermissionUserName() {
        return PermissionUserName;
    }

    public void setPermissionUserName(String permissionUserName) {
        PermissionUserName = permissionUserName;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getSmallImgUrl() {
        return SmallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        SmallImgUrl = smallImgUrl;
    }

    public String getBigImgUrl() {
        return BigImgUrl;
    }

    public void setBigImgUrl(String bigImgUrl) {
        BigImgUrl = bigImgUrl;
    }

    public String getAttachmentId() {
        return AttachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        AttachmentId = attachmentId;
    }

    public String getCn_ID() {
        return Cn_ID;
    }

    public void setCn_ID(String cn_ID) {
        Cn_ID = cn_ID;
    }

    public String getLngType() {
        return LngType;
    }

    public void setLngType(String lngType) {
        LngType = lngType;
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getContentName() {
        return ContentName;
    }

    public void setContentName(String contentName) {
        ContentName = contentName;
    }

    public String getSubContentName() {
        return SubContentName;
    }

    public void setSubContentName(String subContentName) {
        SubContentName = subContentName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCategoryPathId() {
        return CategoryPathId;
    }

    public void setCategoryPathId(String categoryPathId) {
        CategoryPathId = categoryPathId;
    }

    public String getCategoryPathName() {
        return CategoryPathName;
    }

    public void setCategoryPathName(String categoryPathName) {
        CategoryPathName = categoryPathName;
    }

    public Boolean getOutsideLink() {
        return IsOutsideLink;
    }

    public void setOutsideLink(Boolean outsideLink) {
        IsOutsideLink = outsideLink;
    }

    public String getOutsideUrl() {
        return OutsideUrl;
    }

    public void setOutsideUrl(String outsideUrl) {
        OutsideUrl = outsideUrl;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        Keyword = keyword;
    }

    public Boolean getTop() {
        return IsTop;
    }

    public void setTop(Boolean top) {
        IsTop = top;
    }

    public Boolean getPic() {
        return IsPic;
    }

    public void setPic(Boolean pic) {
        IsPic = pic;
    }

    public Integer getVisiteRecord() {
        return VisiteRecord;
    }

    public void setVisiteRecord(Integer visiteRecord) {
        VisiteRecord = visiteRecord;
    }

    public String getPubDepartment() {
        return PubDepartment;
    }

    public void setPubDepartment(String pubDepartment) {
        PubDepartment = pubDepartment;
    }

    public String getPubDateTime() {
        return PubDateTime;
    }

    public void setPubDateTime(String pubDateTime) {
        PubDateTime = pubDateTime;
    }

    public Integer getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        SortIndex = sortIndex;
    }

    public Boolean getPermission() {
        return IsPermission;
    }

    public void setPermission(Boolean permission) {
        IsPermission = permission;
    }

    public String getPermissionTime() {
        return PermissionTime;
    }

    public void setPermissionTime(String permissionTime) {
        PermissionTime = permissionTime;
    }

    public String getSmallImgViewPc() {
        return SmallImgViewPc;
    }

    public void setSmallImgViewPc(String smallImgViewPc) {
        SmallImgViewPc = smallImgViewPc;
    }

    public String getSmallImgViewApp() {
        return SmallImgViewApp;
    }

    public void setSmallImgViewApp(String smallImgViewApp) {
        SmallImgViewApp = smallImgViewApp;
    }

    public String getBigImgViewPc() {
        return BigImgViewPc;
    }

    public void setBigImgViewPc(String bigImgViewPc) {
        BigImgViewPc = bigImgViewPc;
    }

    public String getBigImgViewApp() {
        return BigImgViewApp;
    }

    public void setBigImgViewApp(String bigImgViewApp) {
        BigImgViewApp = bigImgViewApp;
    }

    public Boolean getNeedTranslate() {
        return IsNeedTranslate;
    }

    public void setNeedTranslate(Boolean needTranslate) {
        IsNeedTranslate = needTranslate;
    }

    public Boolean getTranslate() {
        return IsTranslate;
    }

    public void setTranslate(Boolean translate) {
        IsTranslate = translate;
    }


}
