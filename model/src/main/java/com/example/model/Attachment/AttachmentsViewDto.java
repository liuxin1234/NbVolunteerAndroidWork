package com.example.model.Attachment;

import com.example.model.user.CreateOperationDto;
import com.example.model.user.ModifyOperationDto;

/**
 * 项目名称：NbVolunteerAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/9/17 17:42
 * 修改备注：
 */
public class AttachmentsViewDto {
    private String FileName;//(string, optional): 获取或设置
    private String PhysicalPath;//(string, optional): 获取或设置
    private Integer PageSize;//(integer, optional): 分页数
    private String Comment;//(string, optional): 备注
    private String ImgId;//(string, optional): ImgId
    private String PDFId;//(string, optional): PDFId
    private String SWFId;//(string, optional): SWFId
    private Integer PDFError;//(integer, optional): PDFError
    private Integer SWFError;//(integer, optional): SWFError
    private String LinkFileId;//(string, optional): LinkFileId
    private String CustomTwo;//(string, optional): 预留字段二
    private String CustomThree;//(string, optional): 预留字段三
    private String AttchId;// (string, optional): 附件Id
    private CreateOperationDto CreateOperation;//(CreateOperationDto, optional): 登记信息
    private ModifyOperationDto ModifyOperation;//(ModifyOperationDto, optional): 更新信息
    private String Id;// (string, optional): 获取或设置
    private Integer Version;//(integer, optional): 获取或设置
    private Integer StoreType;//(integer, optional): 获取或设置
    private String FileDisplayName;//(string, optional): 获取或设置
    private String FileExtension;//(string, optional): 获取或设置
    private String FileType;//(string, optional): 获取或设置
    private String FileUrl;// (string, optional): 获取或设置
    private Integer FileSize;//(integer, optional): 文件大小
    private String CustomOne;// (string, optional): 预留字段一 APP缩略图
    private String MenuId;//(string, optional): 菜单Id
    private String BundleName;//(string, optional)

    public String getAttchId() {
        return AttchId;
    }

    public void setAttchId(String attchId) {
        AttchId = attchId;
    }

    public String getBundleName() {
        return BundleName;
    }

    public void setBundleName(String bundleName) {
        BundleName = bundleName;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public CreateOperationDto getCreateOperation() {
        return CreateOperation;
    }

    public void setCreateOperation(CreateOperationDto createOperation) {
        CreateOperation = createOperation;
    }

    public String getCustomOne() {
        return CustomOne;
    }

    public void setCustomOne(String customOne) {
        CustomOne = customOne;
    }

    public String getCustomThree() {
        return CustomThree;
    }

    public void setCustomThree(String customThree) {
        CustomThree = customThree;
    }

    public String getCustomTwo() {
        return CustomTwo;
    }

    public void setCustomTwo(String customTwo) {
        CustomTwo = customTwo;
    }

    public String getFileDisplayName() {
        return FileDisplayName;
    }

    public void setFileDisplayName(String fileDisplayName) {
        FileDisplayName = fileDisplayName;
    }

    public String getFileExtension() {
        return FileExtension;
    }

    public void setFileExtension(String fileExtension) {
        FileExtension = fileExtension;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public Integer getFileSize() {
        return FileSize;
    }

    public void setFileSize(Integer fileSize) {
        FileSize = fileSize;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        FileUrl = fileUrl;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImgId() {
        return ImgId;
    }

    public void setImgId(String imgId) {
        ImgId = imgId;
    }

    public String getLinkFileId() {
        return LinkFileId;
    }

    public void setLinkFileId(String linkFileId) {
        LinkFileId = linkFileId;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public ModifyOperationDto getModifyOperation() {
        return ModifyOperation;
    }

    public void setModifyOperation(ModifyOperationDto modifyOperation) {
        ModifyOperation = modifyOperation;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public Integer getPDFError() {
        return PDFError;
    }

    public void setPDFError(Integer PDFError) {
        this.PDFError = PDFError;
    }

    public String getPDFId() {
        return PDFId;
    }

    public void setPDFId(String PDFId) {
        this.PDFId = PDFId;
    }

    public String getPhysicalPath() {
        return PhysicalPath;
    }

    public void setPhysicalPath(String physicalPath) {
        PhysicalPath = physicalPath;
    }

    public Integer getStoreType() {
        return StoreType;
    }

    public void setStoreType(Integer storeType) {
        StoreType = storeType;
    }

    public Integer getSWFError() {
        return SWFError;
    }

    public void setSWFError(Integer SWFError) {
        this.SWFError = SWFError;
    }

    public String getSWFId() {
        return SWFId;
    }

    public void setSWFId(String SWFId) {
        this.SWFId = SWFId;
    }

    public Integer getVersion() {
        return Version;
    }

    public void setVersion(Integer version) {
        Version = version;
    }
}
