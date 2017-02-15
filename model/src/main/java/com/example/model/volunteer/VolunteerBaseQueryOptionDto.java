package com.example.model.volunteer;

/**
 * Created by Administrator on 2016/8/20.
 */
public class VolunteerBaseQueryOptionDto {

    private String OldId;// (string, optional): 获取或设置 老系统主键ID ,
    private String LngType;// (string, optional): 获取或设置 语言类型 ,
    private Integer TypeId;// (integer, optional): 获取或设置 类型1.志愿服务驿站 2.志愿者指导中心 3.志愿者招募基地 4.志愿者服务基地 ,
    private String Title;// (string, optional): 获取或设置 名称 ,
    private String Address;// (string, optional): 获取或设置 地址 ,
    private String Contacts;// (string, optional): 获取或设置 联系人 ,
    private String Phone;// (string, optional): 获取或设置 联系电话 ,
    private String Description;// (string, optional): 获取或设置 简介 ,
    private String CnId;// (string, optional): 获取或设置 中文ID ,
    private String KeyWord;// (string, optional): 查询关键词 ,
    private Integer PageIndex;// (integer, optional): 当前页索引，默认1 ,
    private Integer PageSize;// (integer, optional): 每页记录条数，默认20 ,
    private Object Sorts;// (object, optional),

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCnId() {
        return CnId;
    }

    public void setCnId(String cnId) {
        CnId = cnId;
    }

    public String getContacts() {
        return Contacts;
    }

    public void setContacts(String contacts) {
        Contacts = contacts;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Object getSorts() {
        return Sorts;
    }

    public void setSorts(Object sorts) {
        Sorts = sorts;
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
}
