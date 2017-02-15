package com.example.model.mobileVersion;

/**
 * 项目名称：NbVolunteerAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/9/17 17:52
 * 修改备注：
 */
public class MobileVersionQueryOptionDto {
    private String VersionName;//(string, optional): 获取或设置
    private String KeyWord;//(string, optional): 查询关键词
    private Integer PageIndex;//(integer, optional): 当前页索引，默认1
    private Integer PageSize;//(integer, optional): 每页记录条数，默认20
    private Object Sorts;//(object, optional)

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
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

    public String getVersionName() {
        return VersionName;
    }

    public void setVersionName(String versionName) {
        VersionName = versionName;
    }
}
