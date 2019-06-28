package com.example.model.appraiseCompany;

/**
 * Created by
 * 项目名称：com.example.model.appraiseCompany
 * 项目日期：2019/5/14
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class VolunteerAppraiseCompanyDto {

    /**
     * Id : string
     * TotalScore : 0
     * Code1 : string
     * Score1 : 0
     * Code2 : string
     * Score2 : 0
     * Code3 : string
     * Score3 : 0
     * Code4 : string
     * Score4 : 0
     * Code5 : string
     * Score5 : 0
     * Feeling : string
     * Opinion : string
     * ActivityId : string
     * VolunteerId : string
     * MobileType : string
     * Version : 0
     */

    private String Id;
    private Integer TotalScore; //总评分
    private String Code1;
    private String Score1;
    private String Code2;
    private String Score2;
    private String Code3;
    private String Score3;
    private String Code4;
    private String Score4;
    private String Code5;
    private String Score5;
    private String Feeling;  //感受
    private String Opinion; //意见
    private String ActivityId;
    private String VolunteerId;
    private String MobileType;  //机型
    private Integer Version;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getTotalScore() {
        return TotalScore;
    }

    public void setTotalScore(int totalScore) {
        TotalScore = totalScore;
    }

    public String getCode1() {
        return Code1;
    }

    public void setCode1(String code1) {
        Code1 = code1;
    }

    public String getScore1() {
        return Score1;
    }

    public void setScore1(String score1) {
        Score1 = score1;
    }

    public String getCode2() {
        return Code2;
    }

    public void setCode2(String code2) {
        Code2 = code2;
    }

    public String getScore2() {
        return Score2;
    }

    public void setScore2(String score2) {
        Score2 = score2;
    }

    public String getCode3() {
        return Code3;
    }

    public void setCode3(String code3) {
        Code3 = code3;
    }

    public String getScore3() {
        return Score3;
    }

    public void setScore3(String score3) {
        Score3 = score3;
    }

    public String getCode4() {
        return Code4;
    }

    public void setCode4(String code4) {
        Code4 = code4;
    }

    public String getScore4() {
        return Score4;
    }

    public void setScore4(String score4) {
        Score4 = score4;
    }

    public String getCode5() {
        return Code5;
    }

    public void setCode5(String code5) {
        Code5 = code5;
    }

    public String getScore5() {
        return Score5;
    }

    public void setScore5(String score5) {
        Score5 = score5;
    }

    public String getFeeling() {
        return Feeling;
    }

    public void setFeeling(String feeling) {
        Feeling = feeling;
    }

    public String getOpinion() {
        return Opinion;
    }

    public void setOpinion(String opinion) {
        Opinion = opinion;
    }

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getVolunteerId() {
        return VolunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        VolunteerId = volunteerId;
    }

    public String getMobileType() {
        return MobileType;
    }

    public void setMobileType(String mobileType) {
        MobileType = mobileType;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int version) {
        Version = version;
    }
}
