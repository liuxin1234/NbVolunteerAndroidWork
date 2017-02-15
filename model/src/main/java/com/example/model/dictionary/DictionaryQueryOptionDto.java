package com.example.model.dictionary;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/12 14:20
 * 修改备注：
 */
public class DictionaryQueryOptionDto {
   private  String LikeCode;//(string, optional): 获取或设置 Like字典代码
   private  Boolean IsEnabled;//(boolean, optional): 获取或设置 是否启用
   private  String LikeName;//(string, optional): 获取或设置 字典项名称
   private  String LikeEnName;//(string, optional): 英文名称
   private  String DictionaryTypeId;//(string, optional): 获取或设置 字典分类
   private  String ParentId;//(string, optional): 获取或设置 上级字典项
   private  String Culture;// (string, optional): 获取或设置 上级字典项
   private  String KeyWord;//(string, optional): 查询关键词
   private  Integer PageIndex;//(integer, optional): 当前页索引，默认1
   private  Integer PageSize;// (integer, optional): 每页记录条数，默认20
   private  Object Sorts;//(object, optional)

    public String getCulture() {
        return Culture;
    }

    public void setCulture(String culture) {
        Culture = culture;
    }

    public String getDictionaryTypeId() {
        return DictionaryTypeId;
    }

    public void setDictionaryTypeId(String dictionaryTypeId) {
        DictionaryTypeId = dictionaryTypeId;
    }

    public Boolean getEnabled() {
        return IsEnabled;
    }

    public void setEnabled(Boolean enabled) {
        IsEnabled = enabled;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getLikeCode() {
        return LikeCode;
    }

    public void setLikeCode(String likeCode) {
        LikeCode = likeCode;
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
