package com.example.model.content;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/20 11:02
 * 修改备注：
 */
public class ContentQueryOptionDto {
    private String CategoryId;//(string, optional),
    private String OrganizationName;//(string, optional),
    private String OrgId;//(string, optional),
    private String ContentName;//(string, optional),
    private String CategoryPathId;// (string, optional),
    private String CategoryPathName;//(string, optional),
    private Boolean IsOutsideLink;// (boolean, optional),
    private String PubDateTimeBeg;//(string, optional),
    private String PubDateTimeEnd;//(string, optional),
    private Boolean IsPermission;// (boolean, optional),
    private String PermissionUserId;//(string, optional),
    private String Author;//(string, optional),
    private Boolean IsNeedTranslate;//(boolean, optional),
    private Boolean IsTranslate;// (boolean, optional),
    private String Cn_ID;//(string, optional),
    private String KeyWord;//(string, optional): 查询关键词
    private Integer PageIndex;//(integer, optional): 当前页索引，默认1
    private Integer PageSize;//(integer, optional): 每页记录条数，默认20
    private Object Sorts;//(object, optional)

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
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

    public String getCn_ID() {
        return Cn_ID;
    }

    public void setCn_ID(String cn_ID) {
        Cn_ID = cn_ID;
    }

    public String getContentName() {
        return ContentName;
    }

    public void setContentName(String contentName) {
        ContentName = contentName;
    }

    public Boolean getNeedTranslate() {
        return IsNeedTranslate;
    }

    public void setNeedTranslate(Boolean needTranslate) {
        IsNeedTranslate = needTranslate;
    }

    public Boolean getOutsideLink() {
        return IsOutsideLink;
    }

    public void setOutsideLink(Boolean outsideLink) {
        IsOutsideLink = outsideLink;
    }

    public Boolean getPermission() {
        return IsPermission;
    }

    public void setPermission(Boolean permission) {
        IsPermission = permission;
    }

    public Boolean getTranslate() {
        return IsTranslate;
    }

    public void setTranslate(Boolean translate) {
        IsTranslate = translate;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getOrgId() {
        return OrgId;
    }

    public void setOrgId(String orgId) {
        OrgId = orgId;
    }

    public Integer getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        PageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public String getPermissionUserId() {
        return PermissionUserId;
    }

    public void setPermissionUserId(String permissionUserId) {
        PermissionUserId = permissionUserId;
    }

    public String getPubDateTimeBeg() {
        return PubDateTimeBeg;
    }

    public void setPubDateTimeBeg(String pubDateTimeBeg) {
        PubDateTimeBeg = pubDateTimeBeg;
    }

    public String getPubDateTimeEnd() {
        return PubDateTimeEnd;
    }

    public void setPubDateTimeEnd(String pubDateTimeEnd) {
        PubDateTimeEnd = pubDateTimeEnd;
    }

    public Object getSorts() {
        return Sorts;
    }

    public void setSorts(Object sorts) {
        Sorts = sorts;
    }
}
