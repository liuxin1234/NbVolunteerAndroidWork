package com.example.model.volunteer;
/*
 *
 * Created by Ge on 2016/9/20  15:47.
 *
 */

public class VolunteerTopViewDto {
    private String UserId; //(string, optional): 获取或设置 志愿者标识
    private String Topic; // (string, optional): 获取或设置 姓名
    private String TopicTime; //(string, optional): 关注或报名时间


    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getTopicTime() {
        return TopicTime;
    }

    public void setTopicTime(String topicTime) {
        TopicTime = topicTime;
    }
}
