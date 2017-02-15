package com.example.model.dictionary;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/15 11:02
 * 修改备注：
 */
public class DictionaryTypeQueryOptionDto {
    private String Code;// (string, optional): 获取或设置 类型代码
    private String LikeName;// (string, optional): 获取或设置 类型名称
    private String LikeEnName;// (string, optional),
    private Boolean IsSystem;//  (boolean, optional): 获取或设置 是否系统类型
    private String KeyWord;// (string, optional): 查询关键词
    private Integer PageIndex;// (integer, optional): 当前页索引，默认1
    private Integer PageSize;// (integer, optional): 每页记录条数，默认20
    private Object Sorts;//  (object, optional)

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Boolean getSystem() {
        return IsSystem;
    }

    public void setSystem(Boolean system) {
        IsSystem = system;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getLikeEnName() {
        return LikeEnName;
    }

    public void setLikeEnName(String likeEnName) {
        LikeEnName = likeEnName;
    }

    public String getLikeName() {
        return LikeName;
    }

    public void setLikeName(String likeName) {
        LikeName = likeName;
    }

    public Integer getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        PageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public Object getSorts() {
        return Sorts;
    }

    public void setSorts(Object sorts) {
        Sorts = sorts;
    }
}
