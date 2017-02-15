package com.example.model.volunteer;

/**
 * Created by Administrator on 2016/8/4.
 */
public class VolunteerEditDto extends VolunteerViewDto {
    public static final String TAG = "VolunteerEditDto";

    private String Id;      // (string, optional): 获取或设置 志愿者标识
    private String TrueName;    // (string): 获取或设置 姓名
    private String OrgId;       // (string, optional): 获取或设置 所属机构Id
    private String OrganizationId;      // (string): 获取或设置 所属机构Id
    private String OrganizationName;    // (string, optional): 获取或设置 所属机构
    private Integer CardType;    // (integer, optional): 获取或设置 证件类型
    private String IdNumber;    // (string): 获取或设置 证件号码
    private String NickName; //(string, optional): 获取或设置 昵称
    private Boolean IsShowTrueName; //(boolean, optional): 获取或设置 是否显示真实姓名
    private String Nation;      // (string, optional): 获取或设置 国藉
    private String Volk;        // (string, optional): 获取或设置 民族
    private String Polity;      // (string, optional): 获取或设置 政治面貌
    private String Academy;     // (string, optional): 获取或设置 毕业院校
    private String SpecialtyType;       // (string, optional): 获取或设置 专业类型
    private String Specialty;   // (string, optional): 获取或设置 专业
    private String Degree;      // (string, optional): 获取或设置 学历
    private String Blood;       // (string, optional): 获取或设置 血型
    private String Origin;      // (string, optional): 获取或设置 籍贯
    private String AreaCode;    // (string): 获取或设置 区域Code
    private String AreaId;      // (string, optional): 获取或设置 区域Id
    private String AreaName;    // (string, optional): 获取或设置 区域
    private String Addr;        // (string, optional): 获取或设置 地址
    private String PostCode;    // (string, optional): 获取或设置 邮政编码
    private String Telephone;   // (string, optional): 获取或设置 固定电话
    private String Mobile;      // (string): 获取或设置 移动电话
    private String Email;       // (string, optional): 获取或设置 电子邮箱
    private Integer JobStatus;      // (integer, optional): 获取或设置 从业状态
    private Double WorkTime;    // (number, optional): 获取或设置 工作年限
    private String Grade;       // (string, optional): 获取或设置 职称
    private String Job;         // (string, optional): 获取或设置 职务
    private Double ServiceTime; // (number, optional): 获取或设置 服务时间(小时)
    private Double TrainTime;   // (number, optional): 获取或设置 培训时间
    private String Skilled;     // (string, optional): 获取或设置 特长
    private Integer LevelType;   // (integer, optional): 获取或设置 志愿者等级
    private Double Score;       // (number, optional): 获取或设置 积分
    private Integer ChronographyType;    // (integer, optional): 获取或设置 计时类型
    private String ChronographyId;  // (string, optional): 获取或设置 计时工具编号
    private String Profession;      // (string, optional): 获取或设置 职业
    private Double Height;      // (number, optional): 获取或设置 身高
    private String Workunit;    // (string, optional): 获取或设置 工作单位
    private Double Historytime;     // (number, optional): 获取或设置 历时时数
    private Double Credit;      // (number, optional): 获取或设置 征信
    private String Domicile;    // (string, optional): 获取或设置 现居地
    private String Describe;    // (string, optional): 获取或设置 描述
    private Integer LoginCount;     // (integer, optional): 获取或设置 登录次数
    private String LastLoginTime;   // (string, optional): 获取或设置 最后次登录时间
    private Integer FailCount;       // (integer, optional): 获取或设置 密码错误次数
    private String IP;      // (string, optional): 获取或设置 上次登录IP
    private Integer IsCompleteInfo;  // (integer, optional): 获取或设置 是否完成信息
    private String ZhLevel;     // (string, optional): 获取或设置 中文水平
    private String EnLevel;     // (string, optional): 获取或设置 外语水平
    private String UnitProperty;    // (string, optional): 获取或设置 单位性质
    private Integer SrcScore;    // (integer, optional): 获取或设置 原积分
    private Double Workservicetime; // (number, optional): 获取或设置 在职服务时长
    private Double Schoolservicetime;   // (number, optional): 获取或设置 在校服务时长
    private Double Retireservicetime;   // (number, optional): 获取或设置 退休服务时长
    private Boolean IsSpeciality;       // (boolean, optional): 获取或设置 是否专业志愿者
    private Boolean IsCertification;     // (boolean, optional): 获取或设置 是否认证
    private String CertificationTime;   // (string, optional): 获取或设置 认证时间
    private String LanguageType;        // (string, optional): 获取或设置 语种
    private String WeiXinOPENID;        // (string, optional): 获取或设置 微信ID
    private Boolean IsDeleted;       // (boolean, optional): 获取或设置 删除状态
    private Integer AuditStatus;     // (integer, optional): 获取或设置 审核状态0未审核 1审核通过 2审核不通过
    private String AuditTime;    // (string, optional): 获取或设置 审核时间
    private String AuditUserId;     // (string, optional): 获取或设置 审核人ID
    private String AuditUserName;   // (string, optional): 获取或设置 审核人用户名
    private String ServiceTimeIntention;  // (string, optional): 获取或设置 意向服务时间
    private String ServiceIntention; // (string, optional): 获取或设置 志愿服务意向
    private String ServiceIntentionOther; // (string, optional): 获取或设置 志愿服务意向其他
    private String CertificatePic; // (string, optional): 获取或设置 上传证书
    private Integer IsVerify; // (integer, optional): 认证状态

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public void setId(String id) {
        Id = id;
    }

    @Override
    public String getTrueName() {
        return TrueName;
    }

    @Override
    public void setTrueName(String trueName) {
        TrueName = trueName;
    }

    @Override
    public String getOrgId() {
        return OrgId;
    }

    @Override
    public void setOrgId(String orgId) {
        OrgId = orgId;
    }

    @Override
    public String getOrganizationId() {
        return OrganizationId;
    }

    @Override
    public void setOrganizationId(String organizationId) {
        OrganizationId = organizationId;
    }

    @Override
    public String getOrganizationName() {
        return OrganizationName;
    }

    @Override
    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    @Override
    public Integer getCardType() {
        return CardType;
    }

    @Override
    public void setCardType(Integer cardType) {
        CardType = cardType;
    }

    @Override
    public String getIdNumber() {
        return IdNumber;
    }

    @Override
    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    @Override
    public String getNickName() {
        return NickName;
    }

    @Override
    public void setNickName(String nickName) {
        NickName = nickName;
    }

    @Override
    public Boolean getShowTrueName() {
        return IsShowTrueName;
    }

    @Override
    public void setShowTrueName(Boolean showTrueName) {
        IsShowTrueName = showTrueName;
    }

    @Override
    public String getNation() {
        return Nation;
    }

    @Override
    public void setNation(String nation) {
        Nation = nation;
    }

    @Override
    public String getVolk() {
        return Volk;
    }

    @Override
    public void setVolk(String volk) {
        Volk = volk;
    }

    @Override
    public String getPolity() {
        return Polity;
    }

    @Override
    public void setPolity(String polity) {
        Polity = polity;
    }

    @Override
    public String getAcademy() {
        return Academy;
    }

    @Override
    public void setAcademy(String academy) {
        Academy = academy;
    }

    @Override
    public String getSpecialtyType() {
        return SpecialtyType;
    }

    @Override
    public void setSpecialtyType(String specialtyType) {
        SpecialtyType = specialtyType;
    }

    @Override
    public String getSpecialty() {
        return Specialty;
    }

    @Override
    public void setSpecialty(String specialty) {
        Specialty = specialty;
    }

    @Override
    public String getDegree() {
        return Degree;
    }

    @Override
    public void setDegree(String degree) {
        Degree = degree;
    }

    @Override
    public String getBlood() {
        return Blood;
    }

    @Override
    public void setBlood(String blood) {
        Blood = blood;
    }

    @Override
    public String getOrigin() {
        return Origin;
    }

    @Override
    public void setOrigin(String origin) {
        Origin = origin;
    }

    @Override
    public String getAreaCode() {
        return AreaCode;
    }

    @Override
    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    @Override
    public String getAreaId() {
        return AreaId;
    }

    @Override
    public void setAreaId(String areaId) {
        AreaId = areaId;
    }

    @Override
    public String getAreaName() {
        return AreaName;
    }

    @Override
    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    @Override
    public String getAddr() {
        return Addr;
    }

    @Override
    public void setAddr(String addr) {
        Addr = addr;
    }

    @Override
    public String getPostCode() {
        return PostCode;
    }

    @Override
    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    @Override
    public String getTelephone() {
        return Telephone;
    }

    @Override
    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    @Override
    public String getMobile() {
        return Mobile;
    }

    @Override
    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    @Override
    public String getEmail() {
        return Email;
    }

    @Override
    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public Integer getJobStatus() {
        return JobStatus;
    }

    @Override
    public void setJobStatus(Integer jobStatus) {
        JobStatus = jobStatus;
    }

    @Override
    public Double getWorkTime() {
        return WorkTime;
    }

    @Override
    public void setWorkTime(Double workTime) {
        WorkTime = workTime;
    }

    @Override
    public String getGrade() {
        return Grade;
    }

    @Override
    public void setGrade(String grade) {
        Grade = grade;
    }

    @Override
    public String getJob() {
        return Job;
    }

    @Override
    public void setJob(String job) {
        Job = job;
    }

    @Override
    public Double getServiceTime() {
        return ServiceTime;
    }

    @Override
    public void setServiceTime(Double serviceTime) {
        ServiceTime = serviceTime;
    }

    @Override
    public Double getTrainTime() {
        return TrainTime;
    }

    @Override
    public void setTrainTime(Double trainTime) {
        TrainTime = trainTime;
    }

    @Override
    public String getSkilled() {
        return Skilled;
    }

    @Override
    public void setSkilled(String skilled) {
        Skilled = skilled;
    }

    @Override
    public Integer getLevelType() {
        return LevelType;
    }

    @Override
    public void setLevelType(Integer levelType) {
        LevelType = levelType;
    }

    @Override
    public Double getScore() {
        return Score;
    }

    @Override
    public void setScore(Double score) {
        Score = score;
    }

    @Override
    public Integer getChronographyType() {
        return ChronographyType;
    }

    @Override
    public void setChronographyType(Integer chronographyType) {
        ChronographyType = chronographyType;
    }

    @Override
    public String getChronographyId() {
        return ChronographyId;
    }

    @Override
    public void setChronographyId(String chronographyId) {
        ChronographyId = chronographyId;
    }

    @Override
    public String getProfession() {
        return Profession;
    }

    @Override
    public void setProfession(String profession) {
        Profession = profession;
    }

    @Override
    public Double getHeight() {
        return Height;
    }

    @Override
    public void setHeight(Double height) {
        Height = height;
    }

    @Override
    public String getWorkunit() {
        return Workunit;
    }

    @Override
    public void setWorkunit(String workunit) {
        Workunit = workunit;
    }

    @Override
    public Double getHistorytime() {
        return Historytime;
    }

    @Override
    public void setHistorytime(Double historytime) {
        Historytime = historytime;
    }

    @Override
    public Double getCredit() {
        return Credit;
    }

    @Override
    public void setCredit(Double credit) {
        Credit = credit;
    }

    @Override
    public String getDomicile() {
        return Domicile;
    }

    @Override
    public void setDomicile(String domicile) {
        Domicile = domicile;
    }

    @Override
    public String getDescribe() {
        return Describe;
    }

    @Override
    public void setDescribe(String describe) {
        Describe = describe;
    }

    @Override
    public Integer getLoginCount() {
        return LoginCount;
    }

    @Override
    public void setLoginCount(Integer loginCount) {
        LoginCount = loginCount;
    }

    @Override
    public String getLastLoginTime() {
        return LastLoginTime;
    }

    @Override
    public void setLastLoginTime(String lastLoginTime) {
        LastLoginTime = lastLoginTime;
    }

    @Override
    public Integer getFailCount() {
        return FailCount;
    }

    @Override
    public void setFailCount(Integer failCount) {
        FailCount = failCount;
    }

    @Override
    public String getIP() {
        return IP;
    }

    @Override
    public void setIP(String IP) {
        this.IP = IP;
    }

    @Override
    public Integer getIsCompleteInfo() {
        return IsCompleteInfo;
    }

    @Override
    public void setIsCompleteInfo(Integer isCompleteInfo) {
        IsCompleteInfo = isCompleteInfo;
    }

    @Override
    public String getZhLevel() {
        return ZhLevel;
    }

    @Override
    public void setZhLevel(String zhLevel) {
        ZhLevel = zhLevel;
    }

    @Override
    public String getEnLevel() {
        return EnLevel;
    }

    @Override
    public void setEnLevel(String enLevel) {
        EnLevel = enLevel;
    }

    @Override
    public String getUnitProperty() {
        return UnitProperty;
    }

    @Override
    public void setUnitProperty(String unitProperty) {
        UnitProperty = unitProperty;
    }

    @Override
    public Integer getSrcScore() {
        return SrcScore;
    }

    @Override
    public void setSrcScore(Integer srcScore) {
        SrcScore = srcScore;
    }

    @Override
    public Double getWorkservicetime() {
        return Workservicetime;
    }

    @Override
    public void setWorkservicetime(Double workservicetime) {
        Workservicetime = workservicetime;
    }

    @Override
    public Double getSchoolservicetime() {
        return Schoolservicetime;
    }

    @Override
    public void setSchoolservicetime(Double schoolservicetime) {
        Schoolservicetime = schoolservicetime;
    }

    @Override
    public Double getRetireservicetime() {
        return Retireservicetime;
    }

    @Override
    public void setRetireservicetime(Double retireservicetime) {
        Retireservicetime = retireservicetime;
    }

    public Boolean getSpeciality() {
        return IsSpeciality;
    }

    public void setSpeciality(Boolean speciality) {
        IsSpeciality = speciality;
    }

    @Override
    public Boolean getCertification() {
        return IsCertification;
    }

    @Override
    public void setCertification(Boolean certification) {
        IsCertification = certification;
    }

    @Override
    public String getCertificationTime() {
        return CertificationTime;
    }

    @Override
    public void setCertificationTime(String certificationTime) {
        CertificationTime = certificationTime;
    }

    @Override
    public String getLanguageType() {
        return LanguageType;
    }

    @Override
    public void setLanguageType(String languageType) {
        LanguageType = languageType;
    }

    @Override
    public String getWeiXinOPENID() {
        return WeiXinOPENID;
    }

    @Override
    public void setWeiXinOPENID(String weiXinOPENID) {
        WeiXinOPENID = weiXinOPENID;
    }

    @Override
    public Boolean getDeleted() {
        return IsDeleted;
    }

    @Override
    public void setDeleted(Boolean deleted) {
        IsDeleted = deleted;
    }

    @Override
    public Integer getAuditStatus() {
        return AuditStatus;
    }

    @Override
    public void setAuditStatus(Integer auditStatus) {
        AuditStatus = auditStatus;
    }

    @Override
    public String getAuditTime() {
        return AuditTime;
    }

    @Override
    public void setAuditTime(String auditTime) {
        AuditTime = auditTime;
    }

    @Override
    public String getAuditUserId() {
        return AuditUserId;
    }

    @Override
    public void setAuditUserId(String auditUserId) {
        AuditUserId = auditUserId;
    }

    @Override
    public String getAuditUserName() {
        return AuditUserName;
    }

    @Override
    public void setAuditUserName(String auditUserName) {
        AuditUserName = auditUserName;
    }

    @Override
    public String getServiceTimeIntention() {
        return ServiceTimeIntention;
    }

    @Override
    public void setServiceTimeIntention(String serviceTimeIntention) {
        ServiceTimeIntention = serviceTimeIntention;
    }

    @Override
    public String getServiceIntention() {
        return ServiceIntention;
    }

    @Override
    public void setServiceIntention(String serviceIntention) {
        ServiceIntention = serviceIntention;
    }

    @Override
    public String getServiceIntentionOther() {
        return ServiceIntentionOther;
    }

    @Override
    public void setServiceIntentionOther(String serviceIntentionOther) {
        ServiceIntentionOther = serviceIntentionOther;
    }

    @Override
    public String getCertificatePic() {
        return CertificatePic;
    }

    @Override
    public void setCertificatePic(String certificatePic) {
        CertificatePic = certificatePic;
    }

    @Override
    public Integer getIsVerify() {
        return IsVerify;
    }

    @Override
    public void setIsVerify(Integer isVerify) {
        IsVerify = isVerify;
    }
}
