package com.example.model.dictionary;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/12 14:22
 * 修改备注：
 */
public class DictionaryListDto {
   private  String Id;//(string, optional): 数据字典标识
   private  String Version;//(integer, optional): 数据版本
   private  String Code;//(string, optional): 获取或设置 字典代码
   private  String Comment;//(string, optional): 获取或设置 描述
   private  Boolean IsEnabled;//(boolean, optional): 获取或设置 是否启用
   private  String Name;//(string, optional): 获取或设置 字典项名称
   private  String EnName;//(string, optional): 英文名称
   private  String ShortName;//(string, optional): 获取或设置 字典项简称
   private  String PinYin;//(string, optional): 获取或设置 字典项拼音
   private  String ShortPinYin;//(string, optional): 获取或设置 字典项简拼
   private  Integer SortIndex;// (integer, optional): 获取或设置 排序
   private  String SrcId;//(string, optional): 获取或设置 源记录标识
   private  Integer LevelId;//(integer, optional): 获取或设置 层级标识
   private  String IdPath;//(string, optional): 获取或设置 字典项路径标识
   private  String IdPathName;// (string, optional): 获取或设置 字典项路径名称
   private  String DictionaryTypeId;// (string, optional): 获取或设置 字典分类
   private  String ParentId;//(string, optional): 获取或设置 上级字典项
   private  Boolean IsLeaf;//(boolean, optional): 叶子节点
   private  String Culture;//(string, optional): 获取或设置 上级字典项

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

    public String getSrcId() {
        return SrcId;
    }

    public void setSrcId(String srcId) {
        SrcId = srcId;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }
}
