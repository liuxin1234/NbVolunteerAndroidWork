package com.example.model.items;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/9 13:08
 * 修改备注：
 */
public class OrganizationListItem {
    private static final String TAG = "OrganizationListItem";

    private String iconUrl;
    private String name;
    private String address;
    private String contactName;
    private String contactPhone;

    public OrganizationListItem() {
    }

    public OrganizationListItem(String address, String contactName, String contactPhone, String iconUrl, String name) {
        this.address = address;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
        this.iconUrl = iconUrl;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
