package com.example.renhao.wevolunteer.event;

import com.example.model.Attachment.AttachmentParaDto;

import java.util.List;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/25 15:48
 * 修改备注：
 */
public class UpLoadFileEvent {
    List<AttachmentParaDto> files;

    public UpLoadFileEvent(List<AttachmentParaDto> files) {
        this.files = files;
    }

    public UpLoadFileEvent() {
    }

    public List<AttachmentParaDto> getFiles() {
        return files;
    }

    public void setFiles(List<AttachmentParaDto> files) {
        this.files = files;
    }
}
