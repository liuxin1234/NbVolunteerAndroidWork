package com.example.model.Attachment;
/*
 *
 * Created by Ge on 2016/8/22  10:28.
 *
 */

import java.io.Serializable;

public class AttachmentParaDto implements Serializable {
    private String FileName;   //(string, optional): 文件名
    private String FileData;   //(string, optional): 文件二进制数据
    private String IsPublic;   // (string, optional): 附件是否公开
    private String InfoId;   // (string, optional): 关联信息标识
    private String BundleName;   //(string, optional): 调用插件名称
    private String MaxSize;   // (string, optional): 默认10Mb
    private String PcWH;   // (string, optional): PC宽|高 560|320
    private String AppWH;   // (string, optional): APP宽|高 280|160
    private String ChunkSize;   // (string, optional): 1Mb分片大小

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getFileData() {
        return FileData;
    }

    public void setFileData(String fileData) {
        FileData = fileData;
    }

    public String getIsPublic() {
        return IsPublic;
    }

    public void setIsPublic(String isPublic) {
        IsPublic = isPublic;
    }

    public String getInfoId() {
        return InfoId;
    }

    public void setInfoId(String infoId) {
        InfoId = infoId;
    }

    public String getBundleName() {
        return BundleName;
    }

    public void setBundleName(String bundleName) {
        BundleName = bundleName;
    }

    public String getMaxSize() {
        return MaxSize;
    }

    public void setMaxSize(String maxSize) {
        MaxSize = maxSize;
    }

    public String getPcWH() {
        return PcWH;
    }

    public void setPcWH(String pcWH) {
        PcWH = pcWH;
    }

    public String getAppWH() {
        return AppWH;
    }

    public void setAppWH(String appWH) {
        AppWH = appWH;
    }

    public String getChunkSize() {
        return ChunkSize;
    }

    public void setChunkSize(String chunkSize) {
        ChunkSize = chunkSize;
    }
}
