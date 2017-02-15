package com.example.model.user;
/*
 *
 * Created by Ge on 2016/8/20  16:20.
 *
 */

public class UserPhotoDto {
    private String UserId; // (string, optional),
    private String Photo; //(string, optional)

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
