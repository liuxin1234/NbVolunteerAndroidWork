package com.example.api;

/**
 * 项目名称：BaseAndroid
 * 类描述：用于服务器端向客户端发送数据
 * 创建人：renhao
 * 创建时间：2016/7/19 11:24
 * 修改备注：
 */
public class ApiResponse<T> {
    private static final String TAG = "ApiResponse";

    private T Data;
    private boolean Success;
    private String Message;
    private Object Info;

    public ApiResponse(boolean Success, String Message) {
        this.Success = Success;
        this.Message = Message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public Object getInfo() {
        return Info;
    }

    public void setInfo(Object info) {
        Info = info;
    }
}
