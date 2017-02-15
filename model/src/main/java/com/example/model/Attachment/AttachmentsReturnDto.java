package com.example.model.Attachment;
/*
 *
 * Created by Ge on 2016/8/22  14:31.
 *
 */

public class AttachmentsReturnDto {
    private String Id; //(string, optional): 附件标识
    private String FileDisplayName; // (string, optional): 文件显示名
    private String FileUrl; // (string, optional): 文件Url
    private String CustomOne; // (string, optional): App文件Url

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFileDisplayName() {
        return FileDisplayName;
    }

    public void setFileDisplayName(String fileDisplayName) {
        FileDisplayName = fileDisplayName;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        FileUrl = fileUrl;
    }

    public String getCustomOne() {
        return CustomOne;
    }

    public void setCustomOne(String customOne) {
        CustomOne = customOne;
    }
}
