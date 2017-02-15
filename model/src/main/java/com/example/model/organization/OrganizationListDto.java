package com.example.model.organization;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/17 10:41
 * 修改备注：
 */
public class OrganizationListDto {
   private String Id;//(string, optional): 获取或设置
   private Integer Version;// (integer, optional): 获取或设置
   private String Code;//(string, optional): 获取或设置 部门代码
   private String Name;//(string, optional): 获取或设置 部门名称
   private String EnName;//(string, optional): 英文名称
   private String ShortName;//(string, optional): 获取或设置 名称简写
   private String PinYin;// (string, optional): 获取或设置 拼音
   private String ShortPinYin;//(string, optional): 获取或设置 拼音简写
   private Integer LevelId;//(integer, optional): 获取或设置 级层标识
   private String IdPathName;//(string, optional): 获取或设置 路径名称
   private String IdPath;//(string, optional): 获取或设置 路径标识
   private String Comment;// (string, optional): 获取或设置 备注
   private Boolean IsEnabled;//(boolean, optional): 获取或设置 是否启用
   private Boolean IsEntityOrg;//(boolean, optional): 获取或设置 是否实体机构
   private Boolean IsIndependentOrg;// (boolean, optional): 获取或设置 是否独立机构
   private Integer OrganizationType;//(integer, optional): 获取或设置 部门类型：公司，部门= ['0', '1'],
   private Integer SortIndex;//(integer, optional): 获取或设置 排序
   private String ParentId;// (string, optional): 获取或设置 上级部门
   private Boolean IsInner;//(boolean, optional): 是否内部部门
   private Boolean IsLeaf;// (boolean, optional): 是否叶子
   private String AreaCode;// (string, optional): 所属区域
   private String AreaName;//(string, optional): 所属区域

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getEnName() {
        return EnName;
    }

    public void setEnName(String enName) {
        EnName = enName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdPath() {
        return IdPath;
    }

    public void setIdPath(String idPath) {
        IdPath = idPath;
    }

    public String getIdPathName() {
        return IdPathName;
    }

    public void setIdPathName(String idPathName) {
        IdPathName = idPathName;
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

    public Boolean getInner() {
        return IsInner;
    }

    public void setInner(Boolean inner) {
        IsInner = inner;
    }

    public Boolean getLeaf() {
        return IsLeaf;
    }

    public void setLeaf(Boolean leaf) {
        IsLeaf = leaf;
    }

    public Integer getLevelId() {
        return LevelId;
    }

    public void setLevelId(Integer levelId) {
        LevelId = levelId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getOrganizationType() {
        return OrganizationType;
    }

    public void setOrganizationType(Integer organizationType) {
        OrganizationType = organizationType;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public String getPinYin() {
        return PinYin;
    }

    public void setPinYin(String pinYin) {
        PinYin = pinYin;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public String getShortPinYin() {
        return ShortPinYin;
    }

    public void setShortPinYin(String shortPinYin) {
        ShortPinYin = shortPinYin;
    }

    public Integer getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        SortIndex = sortIndex;
    }

    public Integer getVersion() {
        return Version;
    }

    public void setVersion(Integer version) {
        Version = version;
    }
}
