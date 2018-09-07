package com.example.model.durationRecord;

/**
 * Created by
 * 项目名称：com.example.model.durationRecord
 * 项目日期：2018/9/7
 * 作者：liux
 * 功能：历史时长补录的请求 dto
 *
 * @author 750954283(qq)
 */

public class DurationRecordQueryOptionDto {

    /**
     * Id : string
     * Version : 0
     * VolunteerId : string
     * LType : 0
     * JobStatus : string
     * JobStatusName : string
     * DatePart : 2018-09-07T05:47:54.995Z
     * Source : string
     * AddLenth : 0
     * InTime : 2018-09-07T05:47:54.995Z
     * OutTime : 2018-09-07T05:47:54.995Z
     * ActivityName : string
     * OrgName : string
     * IdNumber : string
     * VolunteerName : string
     * Status : 0
     * IsDeleted : true
     * CreateTime : 2018-09-07T05:47:54.995Z
     * CreateUserId : string
     * CreateUserName : string
     * ModifyTime : 2018-09-07T05:47:54.995Z
     * ModifyUserId : string
     * ModifyUserName : string
     * KeyWord : string
     * PageIndex : 0
     * PageSize : 0
     * Sorts : {}
     */

    private String Id;
//    private int Version;
    private String VolunteerId;
//    private int LType;
    private String JobStatus;
    private String JobStatusName;
    private String DatePart;
    private String Source;
//    private int AddLenth;
    private String InTime;
    private String OutTime;
    private String ActivityName;
    private String OrgName;
    private String IdNumber;
    private String VolunteerName;
    private int Status; //审核状态 1 才取数据
//    private boolean IsDeleted;
    private String CreateTime;
    private String CreateUserId;
    private String CreateUserName;
    private String ModifyTime;
    private String ModifyUserId;
    private String ModifyUserName;
    private String KeyWord;
    private int PageIndex;
    private int PageSize;
    private Object Sorts;


    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

//    public int getVersion() {
//        return Version;
//    }
//
//    public void setVersion(int Version) {
//        this.Version = Version;
//    }

    public String getVolunteerId() {
        return VolunteerId;
    }

    public void setVolunteerId(String VolunteerId) {
        this.VolunteerId = VolunteerId;
    }

//    public int getLType() {
//        return LType;
//    }
//
//    public void setLType(int LType) {
//        this.LType = LType;
//    }


    public String getJobStatus() {
        return JobStatus;
    }

    public void setJobStatus(String JobStatus) {
        this.JobStatus = JobStatus;
    }

    public String getJobStatusName() {
        return JobStatusName;
    }

    public void setJobStatusName(String JobStatusName) {
        this.JobStatusName = JobStatusName;
    }

    public String getDatePart() {
        return DatePart;
    }

    public void setDatePart(String DatePart) {
        this.DatePart = DatePart;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

//    public int getAddLenth() {
//        return AddLenth;
//    }
//
//    public void setAddLenth(int AddLenth) {
//        this.AddLenth = AddLenth;
//    }

    public String getInTime() {
        return InTime;
    }

    public void setInTime(String InTime) {
        this.InTime = InTime;
    }

    public String getOutTime() {
        return OutTime;
    }

    public void setOutTime(String OutTime) {
        this.OutTime = OutTime;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String ActivityName) {
        this.ActivityName = ActivityName;
    }

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String OrgName) {
        this.OrgName = OrgName;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String IdNumber) {
        this.IdNumber = IdNumber;
    }

    public String getVolunteerName() {
        return VolunteerName;
    }

    public void setVolunteerName(String VolunteerName) {
        this.VolunteerName = VolunteerName;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
//
//    public boolean isIsDeleted() {
//        return IsDeleted;
//    }
//
//    public void setIsDeleted(boolean IsDeleted) {
//        this.IsDeleted = IsDeleted;
//    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public String getCreateUserId() {
        return CreateUserId;
    }

    public void setCreateUserId(String CreateUserId) {
        this.CreateUserId = CreateUserId;
    }

    public String getCreateUserName() {
        return CreateUserName;
    }

    public void setCreateUserName(String CreateUserName) {
        this.CreateUserName = CreateUserName;
    }

    public String getModifyTime() {
        return ModifyTime;
    }

    public void setModifyTime(String ModifyTime) {
        this.ModifyTime = ModifyTime;
    }

    public String getModifyUserId() {
        return ModifyUserId;
    }

    public void setModifyUserId(String ModifyUserId) {
        this.ModifyUserId = ModifyUserId;
    }

    public String getModifyUserName() {
        return ModifyUserName;
    }

    public void setModifyUserName(String ModifyUserName) {
        this.ModifyUserName = ModifyUserName;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String KeyWord) {
        this.KeyWord = KeyWord;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int PageIndex) {
        this.PageIndex = PageIndex;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int PageSize) {
        this.PageSize = PageSize;
    }

    public Object getSorts() {
        return Sorts;
    }

    public void setSorts(Object sorts) {
        Sorts = sorts;
    }
}
