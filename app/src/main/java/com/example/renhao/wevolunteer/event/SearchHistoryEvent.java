package com.example.renhao.wevolunteer.event;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/19 13:31
 * 修改备注：
 */
public class SearchHistoryEvent {

    private String keyWords;
    private int searchType;
    private int refreshType;

    public SearchHistoryEvent() {
    }

    public SearchHistoryEvent(String keyWords, int refreshType, int searchType) {
        this.keyWords = keyWords;
        this.refreshType = refreshType;
        this.searchType = searchType;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public int getRefreshType() {
        return refreshType;
    }

    public void setRefreshType(int refreshType) {
        this.refreshType = refreshType;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }
}
