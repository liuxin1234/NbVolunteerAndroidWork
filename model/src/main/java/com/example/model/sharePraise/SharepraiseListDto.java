package com.example.model.sharePraise;

/**
 * Created by
 * 项目名称：com.example.model.sharePraise
 * 项目日期：2019/5/14
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class SharepraiseListDto {

    /**
     * Id : string
     * ShareId : string
     * VolunteerId : string
     * VolunteerName : string
     * Statu : 0
     * Version : 0
     */

    private String Id;
    private String ShareId;
    private String VolunteerId;
    private String VolunteerName;
    private Integer Statu;
    private Integer Version;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getShareId() {
        return ShareId;
    }

    public void setShareId(String ShareId) {
        this.ShareId = ShareId;
    }

    public String getVolunteerId() {
        return VolunteerId;
    }

    public void setVolunteerId(String VolunteerId) {
        this.VolunteerId = VolunteerId;
    }

    public String getVolunteerName() {
        return VolunteerName;
    }

    public void setVolunteerName(String VolunteerName) {
        this.VolunteerName = VolunteerName;
    }

    public int getStatu() {
        return Statu;
    }

    public void setStatu(int Statu) {
        this.Statu = Statu;
    }

    public int getVersion() {
        return Version;
    }

    public void setVersion(int Version) {
        this.Version = Version;
    }
}
