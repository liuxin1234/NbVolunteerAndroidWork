package com.example.model.volunteer;

/**
 * Created by Administrator on 2016/8/20.
 */
public class VolunteerBaseListDto {

    private String Id;// (string, optional): 获取或设置 主键ID ,
    private String OldId;// (string, optional): 获取或设置 老系统主键ID ,
    private String LngType;// (string, optional): 获取或设置 语言类型 ,
    private Integer TypeId;// (integer, optional): 获取或设置 类型1.志愿服务驿站 2.志愿者指导中心 3.志愿者招募基地 4.志愿者服务基地 ,
    private String DictionaryName;// (string, optional),
    private String Title;// (string, optional): 获取或设置 名称 ,
    private String Address;// (string, optional): 获取或设置 地址 ,
    private String Contacts;// (string, optional): 获取或设置 联系人 ,
    private String Phone;// (string, optional): 获取或设置 联系电话 ,
    private String Description;// (string, optional): 获取或设置 简介 ,
    private String Contents;// (string, optional): 获取或设置 介绍 ,
    private String Remark;// (string, optional): 获取或设置 备注 ,
    private String SmallImgUrl;// (string, optional): 获取或设置 小图片 ,
    private String OutsideUrl;// (string, optional): 获取或设置 外链地址 ,
    private Integer VisiteRecord;// (integer, optional): 获取或设置 访问数 ,
    private Integer SortIndex;// (integer, optional): 获取或设置 排序号 ,
    private String Lng;// (string, optional): 获取或设置 经度 ,
    private String Lat;// (string, optional): 获取或设置 纬度,

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContacts() {
        return Contacts;
    }

    public void setContacts(String contacts) {
        Contacts = contacts;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDictionaryName() {
        return DictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        DictionaryName = dictionaryName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLng() {
        return Lng;
    }

    public void setLng(String lng) {
        Lng = lng;
    }

    public String getLngType() {
        return LngType;
    }

    public void setLngType(String lngType) {
        LngType = lngType;
    }

    public String getOldId() {
        return OldId;
    }

    public void setOldId(String oldId) {
        OldId = oldId;
    }

    public String getOutsideUrl() {
        return OutsideUrl;
    }

    public void setOutsideUrl(String outsideUrl) {
        OutsideUrl = outsideUrl;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getSmallImgUrl() {
        return SmallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        SmallImgUrl = smallImgUrl;
    }

    public Integer getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        SortIndex = sortIndex;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Integer getTypeId() {
        return TypeId;
    }

    public void setTypeId(Integer typeId) {
        TypeId = typeId;
    }

    public Integer getVisiteRecord() {
        return VisiteRecord;
    }

    public void setVisiteRecord(Integer visiteRecord) {
        VisiteRecord = visiteRecord;
    }
}
