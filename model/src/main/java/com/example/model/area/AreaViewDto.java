package com.example.model.area;

import com.example.model.user.CreateOperationDto;
import com.example.model.user.ModifyOperationDto;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/20 14:33
 * 修改备注：
 */
public class AreaViewDto {
    private CreateOperationDto CreateOperation;//(CreateOperationDto, optional),
    private ModifyOperationDto ModifyOperation;//(ModifyOperationDto, optional),
    private String Id;//(string, optional): 标识
    private Integer Version;//(integer, optional): 数据版本
    private String Code;//(string, optional): 地区代码
    private Boolean IsEnabled;//(boolean, optional): 是否启用
    private String Name;//(string, optional): 地区名称
    private String EnName;//(string, optional): 英文名称
    private String ShortName;//(string, optional): 地区简称
    private String PinYin;// (string, optional): 地区全拼
    private String ShortPinYin;// (string, optional): 地区全拼
    private Integer LevelId;// (integer, optional): 层级标识
    private String IdPath;//(string, optional): 层级路径
    private String IdPathName;// (string, optional): 层级名称
    private String Comment;//(string, optional): 描述
    private Integer SortIndex;//(integer, optional): 排序
    private String ParentId;//(string, optional): 上级标识
    private String ParentName;//(string, optional): 上级名称
    private Integer AreaType;//(integer, optional): 地区类型= ['0', '1', '2', '3', '4'],
    private Boolean IsLeaf;//(boolean, optional): 叶子节点
    private String Culture;//(string, optional): 获取或设置 上级字典项

    public Integer getAreaType() {
        return AreaType;
    }

    public void setAreaType(Integer areaType) {
        AreaType = areaType;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
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

    public String getCulture() {
        return Culture;
    }

    public void setCulture(String culture) {
        Culture = culture;
    }

    public String getEnName() {
        return EnName;
    }

    public void setEnName(String enName) {
        EnName = enName;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdPath() {
        return IdPath;
    }

    public void setIdPath(String idPath) {
        IdPath = idPath;
    }

    public String getIdPathName() {
        return IdPathName;
    }

    public void setIdPathName(String idPathName) {
        IdPathName = idPathName;
    }

    public Boolean getEnabled() {
        return IsEnabled;
    }

    public void setEnabled(Boolean enabled) {
        IsEnabled = enabled;
    }

    public Boolean getLeaf() {
        return IsLeaf;
    }

    public void setLeaf(Boolean leaf) {
        IsLeaf = leaf;
    }

    public Integer getLevelId() {
        return LevelId;
    }

    public void setLevelId(Integer levelId) {
        LevelId = levelId;
    }

    public ModifyOperationDto getModifyOperation() {
        return ModifyOperation;
    }

    public void setModifyOperation(ModifyOperationDto modifyOperation) {
        ModifyOperation = modifyOperation;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getParentId() {
        return ParentId;
    }

    public void setParentId(String parentId) {
        ParentId = parentId;
    }

    public String getParentName() {
        return ParentName;
    }

    public void setParentName(String parentName) {
        ParentName = parentName;
    }

    public String getPinYin() {
        return PinYin;
    }

    public void setPinYin(String pinYin) {
        PinYin = pinYin;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public String getShortPinYin() {
        return ShortPinYin;
    }

    public void setShortPinYin(String shortPinYin) {
        ShortPinYin = shortPinYin;
    }

    public Integer getSortIndex() {
        return SortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        SortIndex = sortIndex;
    }

    public Integer getVersion() {
        return Version;
    }

    public void setVersion(Integer version) {
        Version = version;
    }
}
