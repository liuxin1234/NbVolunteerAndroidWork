package com.example.model.signResult;

/**
 * Created by
 * 项目名称：com.example.model.signResult
 * 项目日期：2018/6/25
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class SignResultListDto {

    /**
     * Id : string
     * ActivityTimeActivityActivityName : string
     * VolunteerTrueName : string
     * SignInTime : 2018-06-25T01:25:08.765Z
     * SignOutTime : 2018-06-25T01:25:08.765Z
     * JobStatus : 0
     * ScoreRatio : 0
     * ComputerHour : 0
     * ValidHour : 0
     * HourType : 0
     * ValidScore : 0
     * SignInUserId : string
     * SignInUserName : string
     * SignInUserType : 0
     * SignOutUserId : string
     * SignOutUserName : string
     * SignOutUserType : 0
     * ComputerTime : 2018-06-25T01:25:08.765Z
     * DataStatus : 0
     * NotVerifiedDescription : string
     * AuditStatus : 0
     * AuditUserId : string
     * AuditUserName : string
     * AuditTime : 2018-06-25T01:25:08.765Z
     * AuditResult : string
     */

    private String Id;
    private String ActivityTimeActivityActivityName;
    private String VolunteerTrueName;
    private String SignInTime;
    private String SignOutTime;
    private Integer JobStatus;
    private Number ScoreRatio;
    private Number ComputerHour;
    private Number ValidHour;
    private int HourType;
    private Number ValidScore;
    private String SignInUserId;
    private String SignInUserName;
    private int SignInUserType;
    private String SignOutUserId;
    private String SignOutUserName;
    private int SignOutUserType;
    private String ComputerTime;
    private int DataStatus;
    private String NotVerifiedDescription;
    private int AuditStatus;
    private String AuditUserId;
    private String AuditUserName;
    private String AuditTime;
    private String AuditResult;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getActivityTimeActivityActivityName() {
        return ActivityTimeActivityActivityName;
    }

    public void setActivityTimeActivityActivityName(String ActivityTimeActivityActivityName) {
        this.ActivityTimeActivityActivityName = ActivityTimeActivityActivityName;
    }

    public String getVolunteerTrueName() {
        return VolunteerTrueName;
    }

    public void setVolunteerTrueName(String VolunteerTrueName) {
        this.VolunteerTrueName = VolunteerTrueName;
    }

    public String getSignInTime() {
        return SignInTime;
    }

    public void setSignInTime(String SignInTime) {
        this.SignInTime = SignInTime;
    }

    public String getSignOutTime() {
        return SignOutTime;
    }

    public void setSignOutTime(String SignOutTime) {
        this.SignOutTime = SignOutTime;
    }

    public int getJobStatus() {
        return JobStatus;
    }

    public void setJobStatus(int JobStatus) {
        this.JobStatus = JobStatus;
    }


    public void setJobStatus(Integer jobStatus) {
        JobStatus = jobStatus;
    }

    public Number getScoreRatio() {
        return ScoreRatio;
    }

    public void setScoreRatio(Number scoreRatio) {
        ScoreRatio = scoreRatio;
    }

    public Number getComputerHour() {
        return ComputerHour;
    }

    public void setComputerHour(Number computerHour) {
        ComputerHour = computerHour;
    }

    public Number getValidHour() {
        return ValidHour;
    }

    public void setValidHour(Number validHour) {
        ValidHour = validHour;
    }

    public Number getValidScore() {
        return ValidScore;
    }

    public void setValidScore(Number validScore) {
        ValidScore = validScore;
    }

    public int getHourType() {
        return HourType;
    }

    public void setHourType(int HourType) {
        this.HourType = HourType;
    }



    public String getSignInUserId() {
        return SignInUserId;
    }

    public void setSignInUserId(String SignInUserId) {
        this.SignInUserId = SignInUserId;
    }

    public String getSignInUserName() {
        return SignInUserName;
    }

    public void setSignInUserName(String SignInUserName) {
        this.SignInUserName = SignInUserName;
    }

    public int getSignInUserType() {
        return SignInUserType;
    }

    public void setSignInUserType(int SignInUserType) {
        this.SignInUserType = SignInUserType;
    }

    public String getSignOutUserId() {
        return SignOutUserId;
    }

    public void setSignOutUserId(String SignOutUserId) {
        this.SignOutUserId = SignOutUserId;
    }

    public String getSignOutUserName() {
        return SignOutUserName;
    }

    public void setSignOutUserName(String SignOutUserName) {
        this.SignOutUserName = SignOutUserName;
    }

    public int getSignOutUserType() {
        return SignOutUserType;
    }

    public void setSignOutUserType(int SignOutUserType) {
        this.SignOutUserType = SignOutUserType;
    }

    public String getComputerTime() {
        return ComputerTime;
    }

    public void setComputerTime(String ComputerTime) {
        this.ComputerTime = ComputerTime;
    }

    public int getDataStatus() {
        return DataStatus;
    }

    public void setDataStatus(int DataStatus) {
        this.DataStatus = DataStatus;
    }

    public String getNotVerifiedDescription() {
        return NotVerifiedDescription;
    }

    public void setNotVerifiedDescription(String NotVerifiedDescription) {
        this.NotVerifiedDescription = NotVerifiedDescription;
    }

    public int getAuditStatus() {
        return AuditStatus;
    }

    public void setAuditStatus(int AuditStatus) {
        this.AuditStatus = AuditStatus;
    }

    public String getAuditUserId() {
        return AuditUserId;
    }

    public void setAuditUserId(String AuditUserId) {
        this.AuditUserId = AuditUserId;
    }

    public String getAuditUserName() {
        return AuditUserName;
    }

    public void setAuditUserName(String AuditUserName) {
        this.AuditUserName = AuditUserName;
    }

    public String getAuditTime() {
        return AuditTime;
    }

    public void setAuditTime(String AuditTime) {
        this.AuditTime = AuditTime;
    }

    public String getAuditResult() {
        return AuditResult;
    }

    public void setAuditResult(String AuditResult) {
        this.AuditResult = AuditResult;
    }
}
