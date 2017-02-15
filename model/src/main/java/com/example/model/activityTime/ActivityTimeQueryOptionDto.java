package com.example.model.activityTime;

/**
 * Created by Administrator on 2016/8/28.
 */
public class ActivityTimeQueryOptionDto {
    private String Id;// (string, optional): 获取或设置 岗位时间段标识 ,
    private String TimeCode;// (string, optional): 获取或设置 岗位时间段编号 ,
    private String ActivityId;// (string, optional): 获取或设置 项目编号 ,
    private String ActivityName;// (string),
    private String STime;// (string, optional): 获取或设置 开始时间 ,
    private String KeyWord;// (string, optional): 查询关键词 ,
    private Integer PageIndex;// (integer, optional): 当前页索引，默认1 ,
    private Integer PageSize;// (integer, optional): 每页记录条数，默认20 ,
    private Object Sorts;// (object, optional)

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String activityName) {
        ActivityName = activityName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getSTime() {
        return STime;
    }

    public void setSTime(String STime) {
        this.STime = STime;
    }

    public String getTimeCode() {
        return TimeCode;
    }

    public void setTimeCode(String timeCode) {
        TimeCode = timeCode;
    }
}
