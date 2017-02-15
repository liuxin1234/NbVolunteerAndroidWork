package com.example.model.user;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/3 10:52
 * 修改备注：
 */
public class GroupSimpleViewDto {
    private static final String TAG = "GroupSimpleViewDto";

    private String Id;//(string, optional): 获取或设置 群组标识
    private String Code;//(string, optional): 获取或设置 群组编码
    private String Name;//(string, optional): 获取或设置 群组名称
    private String EnName;//(string, optional): 英文名称
    private String ShortName;//(string, optional): 获取或设置 群组名称

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEnName() {
        return EnName;
    }

    public void setEnName(String enName) {
        EnName = enName;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }
}
