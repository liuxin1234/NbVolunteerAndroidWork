package com.example.model.volunteer;

/**
 * Created by Administrator on 2016/8/3.
 */
public class VolunteerListDto {
    public static final String TAG = "VolunteerListDto";


    private String Id;      //(string, optional): 获取或设置 志愿者标识
    private String TrueName;    //(string, optional): 获取或设置 姓名
    private String NickName;//获取或设置昵称
    private Boolean IsShowTrueName; //是否显示真实姓名
    private String OrganizationName;    //(string, optional): 获取或设置 所属机构
    private Integer CardType;   //(integer, optional): 获取或设置 证件类型
    private String AreaCode; //获取或设置 区域编码
    private String AreaName;    //(string, optional): 获取或设置 区域
    private String Addr;    //(string, optional): 获取或设置 地址
    private String Mobile;  //(string, optional): 获取或设置 移动电话
    private Integer LevelType;  //(integer, optional): 获取或设置 志愿者等级
    private Double Score;   //(number, optional): 获取或设置 积分
    private String Domicile;    //(string, optional): 获取或设置 现居地
    private Integer IsCompleteInfo;     // (integer, optional): 获取或设置 是否完成信息
    private Boolean IsSpeciality;   // (Boolean, optional): 获取或设置 是否专业志愿者
    private Boolean IsCertification;    // (Boolean, optional): 获取或设置 是否认证
    private Integer AuditStatus;     // (integer, optional): 获取或设置 审核状态0未审核 1审核通过 2审核不通过


    @Override
    public String toString() {
        return "VolunteerRowsDto{" +
                "AuditStatus=" + AuditStatus +
                ", Id='" + Id + '\'' +
                ", TrueName='" + TrueName + '\'' +
                ", NickName='" + NickName + '\'' +
                ", IsShowTrueName=" + IsShowTrueName +
                ", OrganizationName='" + OrganizationName + '\'' +
                ", CardType=" + CardType +
                ", AreaCode='" + AreaCode + '\'' +
                ", AreaName='" + AreaName + '\'' +
                ", Addr='" + Addr + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", LevelType=" + LevelType +
                ", Score=" + Score +
                ", Domicile='" + Domicile + '\'' +
                ", IsCompleteInfo=" + IsCompleteInfo +
                ", IsSpeciality=" + IsSpeciality +
                ", IsCertification=" + IsCertification +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTrueName() {
        return TrueName;
    }

    public void setTrueName(String trueName) {
        TrueName = trueName;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public Boolean getShowTrueName() {
        return IsShowTrueName;
    }

    public void setShowTrueName(Boolean showTrueName) {
        IsShowTrueName = showTrueName;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public Integer getCardType() {
        return CardType;
    }

    public void setCardType(Integer cardType) {
        CardType = cardType;
    }

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

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public Integer getLevelType() {
        return LevelType;
    }

    public void setLevelType(Integer levelType) {
        LevelType = levelType;
    }

    public Double getScore() {
        return Score;
    }

    public void setScore(Double score) {
        Score = score;
    }

    public String getDomicile() {
        return Domicile;
    }

    public void setDomicile(String domicile) {
        Domicile = domicile;
    }

    public Integer getIsCompleteInfo() {
        return IsCompleteInfo;
    }

    public void setIsCompleteInfo(Integer isCompleteInfo) {
        IsCompleteInfo = isCompleteInfo;
    }

    public Boolean getSpeciality() {
        return IsSpeciality;
    }

    public void setSpeciality(Boolean speciality) {
        IsSpeciality = speciality;
    }

    public Boolean getCertification() {
        return IsCertification;
    }

    public void setCertification(Boolean certification) {
        IsCertification = certification;
    }

    public Integer getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        AuditStatus = auditStatus;
    }
}
