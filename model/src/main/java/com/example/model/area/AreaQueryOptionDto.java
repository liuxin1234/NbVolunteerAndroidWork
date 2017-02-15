package com.example.model.area;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/18 9:40
 * 修改备注：
 */
public class AreaQueryOptionDto {
   private String Code;//(string, optional): 地区代码
   private Boolean Enabled;//(boolean, optional): 是否启用
   private String LikeName;// (string, optional): 名称Like查询
   private String LikeEnName;// (string, optional): 英文名称
   private Integer AreaType;// (integer, optional): 地区类型= ['0', '1', '2', '3', '4'],
   private String ParentId;//(string, optional): 上级地区
   private String Culture;//(string, optional): 获取或设置 上级字典项
   private String KeyWord;//(string, optional): 查询关键词
   private Integer PageIndex;// (integer, optional): 当前页索引，默认1
   private Integer PageSize;//(integer, optional): 每页记录条数，默认20
   private Object Sorts;//(object, optional)

    public Integer getAreaType() {
        return AreaType;
    }

    public void setAreaType(Integer areaType) {
        AreaType = areaType;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getCulture() {
        return Culture;
    }

    public void setCulture(String culture) {
        Culture = culture;
    }

    public Boolean getEnabled() {
        return Enabled;
    }

    public void setEnabled(Boolean enabled) {
        Enabled = enabled;
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

    public String getLikeName() {
        return LikeName;
    }

    public void setLikeName(String likeName) {
        LikeName = likeName;
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
