package com.example.model.dictionary;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/15 11:04
 * 修改备注：
 */
public class DictionaryTypeListDto {
   private  String Id;//(string, optional): 获取或设置 类型标识
   private  Integer Version;//(integer, optional): 获取或设置 数据版本
   private  String Code;//(string, optional): 获取或设置 类型代码
   private  String Name;//(string, optional): 获取或设置 类型名称
   private  String EnName;//(string, optional),
   private  Boolean IsSystem;// (boolean, optional): 获取或设置 是否系统类型
   private  Integer SortIndex;//(integer, optional): 获取或设置 排序
   private  String Comment;//(string, optional): 获取或设置 描述
   private  String ShortName;//(string, optional): 获取或设置 类型简称
   private  String PinYin;// (string, optional): 获取或设置 名称全拼
   private  String ShortPinYin;//(string, optional): 获取或设置 名称简拼

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

    public Boolean getSystem() {
        return IsSystem;
    }

    public void setSystem(Boolean system) {
        IsSystem = system;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
