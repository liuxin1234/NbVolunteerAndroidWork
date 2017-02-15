package com.example.model;

import java.util.List;

/**
 * 项目名称：BaseAndroid
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/7/23 15:48
 * 修改备注：
 */
public class PagedListEntityDto<T> {
    private List<T> Rows;// (Array[T], optional): 数据行
    private Integer PageIndex;//(integer, optional): 当前页码
    private Integer PageSize;//(integer, optional): 每页条数
    private Integer TotalCount;// (integer, optional): 总共记录数
    private Integer TotalPages;//(integer, optional): 总共分页数
    private Integer StartPosition;// (integer, optional): 记录开始位置
    private Integer EndPosition;//(integer, optional): 记录结束位置
    private Boolean HasPreviousPage;// (boolean, optional): 是否有上一页
    private Boolean HasNextPage;//(boolean, optional): 是否有下一页

    public Integer getEndPosition() {
        return EndPosition;
    }

    public void setEndPosition(Integer endPosition) {
        EndPosition = endPosition;
    }

    public Boolean getHasNextPage() {
        return HasNextPage;
    }

    public void setHasNextPage(Boolean hasNextPage) {
        HasNextPage = hasNextPage;
    }

    public Boolean getHasPreviousPage() {
        return HasPreviousPage;
    }

    public void setHasPreviousPage(Boolean hasPreviousPage) {
        HasPreviousPage = hasPreviousPage;
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

    public List<T> getRows() {
        return Rows;
    }

    public void setRows(List<T> rows) {
        Rows = rows;
    }

    public Integer getStartPosition() {
        return StartPosition;
    }

    public void setStartPosition(Integer startPosition) {
        StartPosition = startPosition;
    }

    public Integer getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(Integer totalCount) {
        TotalCount = totalCount;
    }

    public Integer getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(Integer totalPages) {
        TotalPages = totalPages;
    }
}
