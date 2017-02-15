package com.example.model.organization;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/17 10:39
 * 修改备注：
 */
public class OrganizationQueryOptionDto {
   private  String Code;//(string, optional): 获取或设置 部门代码
   private  String LikeName;//(string, optional): 部门名称Like查询
   private  String LikeEnName;//(string, optional): 英文名称
   private  String ParentId;//(string, optional),
   private  String LikeIdPath;//(string, optional): 路径标识Like查询
   private  Boolean IsEnabled;// (boolean, optional): 获取或设置 是否启用
   private  Boolean IsEntityOrg;//(boolean, optional): 获取或设置 是否实体机构
   private  Boolean IsIndependentOrg;//(boolean, optional): 获取或设置 是否独立机构
   private  Integer OrganizationType;//(integer, optional): 获取或设置 部门类型：公司，部门= ['0', '1'],
   private  String AuthorizeUserId;//(string, optional),
   private  String AuthorizeApplicationId;// (string, optional),
   private  String AreaCode;// (string, optional),
   private  String KeyWord;//(string, optional): 查询关键词
   private  Integer PageIndex;//(integer, optional): 当前页索引，默认1
   private  Integer PageSize;//(integer, optional): 每页记录条数，默认20
   private  Object Sorts;// (object, optional)

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getAuthorizeApplicationId() {
        return AuthorizeApplicationId;
    }

    public void setAuthorizeApplicationId(String authorizeApplicationId) {
        AuthorizeApplicationId = authorizeApplicationId;
    }

    public String getAuthorizeUserId() {
        return AuthorizeUserId;
    }

    public void setAuthorizeUserId(String authorizeUserId) {
        AuthorizeUserId = authorizeUserId;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Boolean getEnabled() {
        return IsEnabled;
    }

    public void setEnabled(Boolean enabled) {
        IsEnabled = enabled;
    }

    public Boolean getEntityOrg() {
        return IsEntityOrg;
    }

    public void setEntityOrg(Boolean entityOrg) {
        IsEntityOrg = entityOrg;
    }

    public Boolean getIndependentOrg() {
        return IsIndependentOrg;
    }

    public void setIndependentOrg(Boolean independentOrg) {
        IsIndependentOrg = independentOrg;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getLikeEnName() {
        return LikeEnName;
    }

    public void setLikeEnName(String likeEnName) {
        LikeEnName = likeEnName;
    }

    public String getLikeIdPath() {
        return LikeIdPath;
    }

    public void setLikeIdPath(String likeIdPath) {
        LikeIdPath = likeIdPath;
    }

    public String getLikeName() {
        return LikeName;
    }

    public void setLikeName(String likeName) {
        LikeName = likeName;
    }

    public Integer getOrganizationType() {
        return OrganizationType;
    }

    public void setOrganizationType(Integer organizationType) {
        OrganizationType = organizationType;
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

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public Object getSorts() {
        return Sorts;
    }

    public void setSorts(Object sorts) {
        Sorts = sorts;
    }
}
