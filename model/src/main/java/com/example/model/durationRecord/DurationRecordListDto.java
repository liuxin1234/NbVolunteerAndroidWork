package com.example.model.durationRecord;

/**
 * Created by
 * 项目名称：com.example.model.durationRecord
 * 项目日期：2018/9/7
 * 作者：liux
 * 功能：历史时长补录 Dto
 *
 * @author 750954283(qq)
 */

public class DurationRecordListDto {

    /**
     * Id : string
     * Version : 0
     * VolunteerId : string
     * LType : 0
     * LTypeName : string
     * JobStatus : string
     * JobStatusName : string
     * DatePart : 2018-09-07T05:47:56.076Z
     * Source : string
     * AddLenth : 0
     * InTime : 2018-09-07T05:47:56.076Z
     * OutTime : 2018-09-07T05:47:56.076Z
     * ActivityName : string
     * OrgName : string
     * IdNumber : string
     * VolunteerName : string
     * Status : 0
     * StatusName : string
     * IsDeleted : true
     */

    private String Id;
    private int Version;
    private String VolunteerId;
    private int LType;
    private String LTypeName;
    private String JobStatus;
    private String JobStatusName;
    private String DatePart;
    private String Source;
    private int AddLenth;
    private String InTime;
    private String OutTime;
    private String ActivityName;
    private String OrgName;
    private String IdNumber;
    private String VolunteerName;
    private int Status;
    private String StatusName;
    private boolean IsDeleted;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }

    public String getVolunteerId() {
        return VolunteerId;
    }

    public void setVolunteerId(String VolunteerId) {
        this.VolunteerId = VolunteerId;
    }

    public int getLType() {
        return LType;
    }

    public void setLType(int LType) {
        this.LType = LType;
    }

    public String getLTypeName() {
        return LTypeName;
    }

    public void setLTypeName(String LTypeName) {
        this.LTypeName = LTypeName;
    }

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

    public int getAddLenth() {
        return AddLenth;
    }

    public void setAddLenth(int AddLenth) {
        this.AddLenth = AddLenth;
    }

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

    public String getStatusName() {
        return StatusName;
    }

    public void setStatusName(String StatusName) {
        this.StatusName = StatusName;
    }

    public boolean isIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(boolean IsDeleted) {
        this.IsDeleted = IsDeleted;
    }
}
