package com.example.model;

import java.io.Serializable;

/**
 * 项目名称：BaseAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/7/21 16:13
 * 修改备注：
 */
public class AccessTokenBO implements Serializable {

    private String access_token;
    private String token_type;
    private String expires_in;
    private String refresh_token;
    private String userId;
    private String userName;
    private String scope;
    private String issued;
    private String expires;

    @Override
    public String toString() {
        return "AccessTokenBO{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in='" + expires_in + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", scope='" + scope + '\'' +
                ", issued='" + issued + '\'' +
                ", expires='" + expires + '\'' +
                '}';
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getIssued() {
        return issued;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }
}
