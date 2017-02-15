package com.example.model.activityattention;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/21 18:17
 * 修改备注：
 */
public class ActivityAttentionQueryOptionDto {
    private String Id;//(string, optional): 获取或设置 关注标识
    private String ActivityId;//(string, optional): 获取或设置 活动编号
    private String UserId;//(string, optional): 获取或设置 志愿者编号
    private String AttentionTime;// (string, optional): 获取或设置 关注时间
    private String KeyWord;//(string, optional): 查询关键词
    private Integer PageIndex;//(integer, optional): 当前页索引，默认1
    private Integer PageSize;//(integer, optional): 每页记录条数，默认20
    private Object Sorts;//(object, optional)

    public String getActivityId() {
        return ActivityId;
    }

    public void setActivityId(String activityId) {
        ActivityId = activityId;
    }

    public String getAttentionTime() {
        return AttentionTime;
    }

    public void setAttentionTime(String attentionTime) {
        AttentionTime = attentionTime;
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

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
