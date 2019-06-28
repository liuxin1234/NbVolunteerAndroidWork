package com.example.model.evaluate;

import java.util.Set;

/**
 * Created by
 * 项目名称：com.example.model.evaluate
 * 项目日期：2019/5/6
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class StarTypeBean {
    private String Code;
    private String Name;
    private Integer Rating;
    private Boolean isCheck;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getRating() {
        return Rating;
    }

    public void setRating(Integer rating) {
        Rating = rating;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }
}
