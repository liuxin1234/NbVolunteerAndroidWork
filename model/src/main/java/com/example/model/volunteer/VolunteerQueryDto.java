package com.example.model.volunteer;

/**
 * Created by Administrator on 2016/8/3.
 */
public class VolunteerQueryDto {
    public static final String TAG = "VolunteerQueryDto";


    private String UserId;      // (string, optional): 获取或设置 系统用户表IdUserId
    private String NickName;    //(string, optional): 获取或设置 昵称
    private String IsShowTrueName; //(Boolean, optional): 获取或设置 是否显示真实姓名
    private String TrueName;    // (string, optional): 获取或设置 姓名
    private String OrgId;       // (string, optional): 获取或设置 所属机构Id
    private Integer CardType;       // (Integer, optional): 获取或设置 证件类型
    private String IdNumber;    // (string, optional): 获取或设置 证件号码
    private String Nation;      // (string, optional): 获取或设置 国藉
    private String Volk;        // (string, optional): 获取或设置 民族
    private String Polity;      // (string, optional): 获取或设置 政治面貌
    private String Academy;     // (string, optional): 获取或设置 毕业院校
    private String SpecialtyType;   // (string, optional): 获取或设置 专业类型
    private String Specialty;   // (string, optional): 获取或设置 专业
    private String Degree;      // (string, optional): 获取或设置 学历
    private String Blood;       // (string, optional): 获取或设置 血型
    private String Origin;      // (string, optional): 获取或设置 籍贯
    private String AreaCode;    // (string, optional): 获取或设置 区域Code
    private String Addr;        // (string, optional): 获取或设置 地址
    private String PostCode;    // (string, optional): 获取或设置 邮政编码
    private String Telephone;   // (string, optional): 获取或设置 固定电话
    private String Mobile;      // (string, optional): 获取或设置 移动电话
    private String Email;       // (string, optional): 获取或设置 电子邮箱
    private Integer JobStatus;      // (Integer, optional): 获取或设置 从业状态
    private Double WorkTime;    // (number, optional): 获取或设置 工作年限
    private Integer Grade;      // (Integer, optional): 获取或设置 职称
    private String Job;     // (string, optional): 获取或设置 职务
    private Double ServiceTime; // (number, optional): 获取或设置 服务时间(小时)
    private Double TrainTime;   // (number, optional): 获取或设置 培训时间
    private String Skilled;     // (string, optional): 获取或设置 特长
    private Integer LevelType;      // (Integer, optional): 获取或设置 志愿者等级
    private Double Score;       // (number, optional): 获取或设置 积分
    private Integer ChronographyType;   // (Integer, optional): 获取或设置 计时类型
    private String ChronographyId;  // (string, optional): 获取或设置 计时工具编号
    private String Profession;      // (string, optional): 获取或设置 职业
    private Double Height;          // (number, optional): 获取或设置 身高
    private String Workunit;        // (string, optional): 获取或设置 工作单位
    private Double Historytime;     // (number, optional): 获取或设置 历时时数
    private Double Credit;          // (number, optional): 获取或设置 征信
    private String Domicile;        // (string, optional): 获取或设置 现居地
    private Integer IsCompleteInfo;     // (Integer, optional): 获取或设置 是否完成信息
    private Integer ZhLevel;        // (Integer, optional): 获取或设置 中文水平
    private Integer EnLevel;        // (Integer, optional): 获取或设置 外语水平
    private String UnitProperty;    // (string, optional): 获取或设置 单位性质
    private Integer SrcScore;       // (Integer, optional): 获取或设置 原积分
    private Double Workservicetime;     // (number, optional): 获取或设置 在职服务时长
    private Double Schoolservicetime;   // (number, optional): 获取或设置 在校服务时长
    private Double Retireservicetime;   // (number, optional): 获取或设置 退休服务时长
    private Boolean IsSpeciality;       // (Boolean, optional): 获取或设置 是否专业志愿者
    private Boolean IsCertification;    // (Boolean, optional): 获取或设置 是否认证
    private String CertificationTimeBeg;    // (string, optional): 获取或设置 认证时间开始
    private String CertificationTimeEnd;    // (string, optional): 获取或设置 认证时间结束
    private String LanguageType;        // (string, optional): 获取或设置 语种
    private String WeiXinOPENID;        // (string, optional): 获取或设置 微信ID
    private Integer AuditStatus;            // (integer, optional): 获取或设置 审核状态0未审核 1审核通过 2审核不通过
    private String AuditUserId;         // (string, optional): 获取或设置 审核人ID
    private String KeyWord;             // (string, optional): 查询关键词
    private Integer PageIndex;              // (integer, optional): 当前页索引，默认1
    private Integer PageSize;               // (integer, optional): 每页记录条数，默认20
    private Object Sorts;               // (object, optional)

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getIsShowTrueName() {
        return IsShowTrueName;
    }

    public void setIsShowTrueName(String isShowTrueName) {
        IsShowTrueName = isShowTrueName;
    }

    public String getTrueName() {
        return TrueName;
    }

    public void setTrueName(String trueName) {
        TrueName = trueName;
    }

    public String getOrgId() {
        return OrgId;
    }

    public void setOrgId(String orgId) {
        OrgId = orgId;
    }

    public Integer getCardType() {
        return CardType;
    }

    public void setCardType(Integer cardType) {
        CardType = cardType;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getNation() {
        return Nation;
    }

    public void setNation(String nation) {
        Nation = nation;
    }

    public String getVolk() {
        return Volk;
    }

    public void setVolk(String volk) {
        Volk = volk;
    }

    public String getPolity() {
        return Polity;
    }

    public void setPolity(String polity) {
        Polity = polity;
    }

    public String getAcademy() {
        return Academy;
    }

    public void setAcademy(String academy) {
        Academy = academy;
    }

    public String getSpecialtyType() {
        return SpecialtyType;
    }

    public void setSpecialtyType(String specialtyType) {
        SpecialtyType = specialtyType;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public void setSpecialty(String specialty) {
        Specialty = specialty;
    }

    public String getDegree() {
        return Degree;
    }

    public void setDegree(String degree) {
        Degree = degree;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public String getOrigin() {
        return Origin;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Integer getJobStatus() {
        return JobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        JobStatus = jobStatus;
    }

    public Double getWorkTime() {
        return WorkTime;
    }

    public void setWorkTime(Double workTime) {
        WorkTime = workTime;
    }

    public Integer getGrade() {
        return Grade;
    }

    public void setGrade(Integer grade) {
        Grade = grade;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String job) {
        Job = job;
    }

    public Double getServiceTime() {
        return ServiceTime;
    }

    public void setServiceTime(Double serviceTime) {
        ServiceTime = serviceTime;
    }

    public Double getTrainTime() {
        return TrainTime;
    }

    public void setTrainTime(Double trainTime) {
        TrainTime = trainTime;
    }

    public String getSkilled() {
        return Skilled;
    }

    public void setSkilled(String skilled) {
        Skilled = skilled;
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

    public Integer getChronographyType() {
        return ChronographyType;
    }

    public void setChronographyType(Integer chronographyType) {
        ChronographyType = chronographyType;
    }

    public String getChronographyId() {
        return ChronographyId;
    }

    public void setChronographyId(String chronographyId) {
        ChronographyId = chronographyId;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public Double getHeight() {
        return Height;
    }

    public void setHeight(Double height) {
        Height = height;
    }

    public String getWorkunit() {
        return Workunit;
    }

    public void setWorkunit(String workunit) {
        Workunit = workunit;
    }

    public Double getHistorytime() {
        return Historytime;
    }

    public void setHistorytime(Double historytime) {
        Historytime = historytime;
    }

    public Double getCredit() {
        return Credit;
    }

    public void setCredit(Double credit) {
        Credit = credit;
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

    public Integer getZhLevel() {
        return ZhLevel;
    }

    public void setZhLevel(Integer zhLevel) {
        ZhLevel = zhLevel;
    }

    public Integer getEnLevel() {
        return EnLevel;
    }

    public void setEnLevel(Integer enLevel) {
        EnLevel = enLevel;
    }

    public String getUnitProperty() {
        return UnitProperty;
    }

    public void setUnitProperty(String unitProperty) {
        UnitProperty = unitProperty;
    }

    public Integer getSrcScore() {
        return SrcScore;
    }

    public void setSrcScore(Integer srcScore) {
        SrcScore = srcScore;
    }

    public Double getWorkservicetime() {
        return Workservicetime;
    }

    public void setWorkservicetime(Double workservicetime) {
        Workservicetime = workservicetime;
    }

    public Double getSchoolservicetime() {
        return Schoolservicetime;
    }

    public void setSchoolservicetime(Double schoolservicetime) {
        Schoolservicetime = schoolservicetime;
    }

    public Double getRetireservicetime() {
        return Retireservicetime;
    }

    public void setRetireservicetime(Double retireservicetime) {
        Retireservicetime = retireservicetime;
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

    public String getCertificationTimeBeg() {
        return CertificationTimeBeg;
    }

    public void setCertificationTimeBeg(String certificationTimeBeg) {
        CertificationTimeBeg = certificationTimeBeg;
    }

    public String getCertificationTimeEnd() {
        return CertificationTimeEnd;
    }

    public void setCertificationTimeEnd(String certificationTimeEnd) {
        CertificationTimeEnd = certificationTimeEnd;
    }

    public String getLanguageType() {
        return LanguageType;
    }

    public void setLanguageType(String languageType) {
        LanguageType = languageType;
    }

    public String getWeiXinOPENID() {
        return WeiXinOPENID;
    }

    public void setWeiXinOPENID(String weiXinOPENID) {
        WeiXinOPENID = weiXinOPENID;
    }

    public Integer getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        AuditStatus = auditStatus;
    }

    public String getAuditUserId() {
        return AuditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        AuditUserId = auditUserId;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
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

    public Object getSorts() {
        return Sorts;
    }

    public void setSorts(Object sorts) {
        Sorts = sorts;
    }
}
