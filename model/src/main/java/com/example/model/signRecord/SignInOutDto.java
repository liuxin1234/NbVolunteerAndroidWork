package com.example.model.signRecord;

/**
 * Created by Administrator on 2016/8/20.
 */
public class SignInOutDto {
    private String Id;//(string, optional): 获取或设置 签到标识
    private Integer Version;//(integer, optional): 获取或设置 版本号
    private String ActivityId;//(string, optional): 项目标识
    private String VolunteerId;//(string, optional): 获取或设置 志愿者Id
    private String ActivityTimeId;//(string),
    private String ActivityTimeActivityActivityName;//(string, optional),
    private String VolunteerTrueName;//(string, optional): 获取或设置 志愿者
    private String Signtime;// (string, optional),
    private String DeviceId;//(string, optional),
    private Number ng;//(number, optional),
    private Number Lat;//(number, optional),
    private String SignAddress;//(string, optional),
    private Integer SourceType;//(integer),
    private Integer ComputerStatus;// (integer),
    private Integer SignType;// (integer)

    public String getActivityTimeActivityActivityName() {
        return ActivityTimeActivityActivityName;
    }

    public void setActivityTimeActivityActivityName(String activityTimeActivityActivityName) {
        ActivityTimeActivityActivityName = activityTimeActivityActivityName;
    }

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getActivityTimeId() {
        return ActivityTimeId;
    }

    public void setActivityTimeId(String activityTimeId) {
        ActivityTimeId = activityTimeId;
    }

    public Integer getComputerStatus() {
        return ComputerStatus;
    }

    public void setComputerStatus(Integer computerStatus) {
        ComputerStatus = computerStatus;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Number getLat() {
        return Lat;
    }

    public void setLat(Number lat) {
        Lat = lat;
    }

    public Number getNg() {
        return ng;
    }

    public void setNg(Number ng) {
        this.ng = ng;
    }

    public String getSignAddress() {
        return SignAddress;
    }

    public void setSignAddress(String signAddress) {
        SignAddress = signAddress;
    }

    public String getSigntime() {
        return Signtime;
    }

    public void setSigntime(String signtime) {
        Signtime = signtime;
    }

    public Integer getSignType() {
        return SignType;
    }

    public void setSignType(Integer signType) {
        SignType = signType;
    }

    public Integer getSourceType() {
        return SourceType;
    }

    public void setSourceType(Integer sourceType) {
        SourceType = sourceType;
    }

    public Integer getVersion() {
        return Version;
    }

    public void setVersion(Integer version) {
        Version = version;
    }

    public String getVolunteerId() {
        return VolunteerId;
    }

    public void setVolunteerId(String volunteerId) {
        VolunteerId = volunteerId;
    }

    public String getVolunteerTrueName() {
        return VolunteerTrueName;
    }

    public void setVolunteerTrueName(String volunteerTrueName) {
        VolunteerTrueName = volunteerTrueName;
    }
}
