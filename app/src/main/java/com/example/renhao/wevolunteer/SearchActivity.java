package com.example.renhao.wevolunteer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.core.local.LocalDate;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.event.SearchHistoryEvent;
import com.example.renhao.wevolunteer.fragment.SearchFragment;
import com.example.renhao.wevolunteer.fragment.SearchResultFragment;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/18 16:53
 * 修改备注：
 */
public class SearchActivity extends BaseActivity {
    private static final String TAG = "SearchActivity";
    public static final int MAX_HISTORY_SIZE = 20;
    public static final int SEARCH = 0;
    public static final int SHOW = 1;

    @Bind(R.id.tv_search_cancel)
    TextView mSearchCancel;
    @Bind(R.id.searchview_searchactivity_search)
    SearchView mSearchView;
    @Bind(R.id.feagment_searchactivity)
    FrameLayout mFragment;

    private int state = SEARCH;
    private List<String> historyStr = new ArrayList<>();

    private SearchFragment mSearchFragment;
    private SearchResultFragment mSearchResultFragment;

    private int searchType = 0;
    private String keyWords;
    public int origin;

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        origin = getIntent().getIntExtra("origin", 0);

        initHistoryDate();

        init();

        initSearchView();

    }

    private void initHistoryDate() {
        String tempStr = LocalDate.getInstance(this).getLocalDate("SearchHistory", "");
        if (!TextUtils.isEmpty(tempStr)) {
            String[] s = tempStr.split("&&");
            for (int i = 0; i < s.length; i++) {
                historyStr.add(s[i]);
            }

        }
    }

    private void init() {
        setFragment(SEARCH);
    }

    private void initSearchView() {
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchView.setIconified(false);
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setKeyWords(query);
                addSearchHistoer(query);
                setFragment(SHOW);
                mSearchView.setFocusable(false);
                mSearchView.clearFocus();
                if (state == SHOW)
                    EventBus.getDefault().post(new SearchHistoryEvent(query, REFRESH, searchType));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Logger.v(TAG, "onQueryTextChange  " + newText);
                return false;
            }
        });
        mSearchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Logger.v(TAG, "has focus " + hasFocus);
                if (hasFocus && state == SHOW) {
                    setFragment(SEARCH);
                }
            }
        });
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Logger.v(TAG, "close ");
                if (state == SHOW) {
                    setFragment(SEARCH);
                }
                return false;
            }
        });
    }

    public void addSearchHistoer(String query) {

        boolean isExist = false;
        for (int i = 0; i < historyStr.size(); i++) {
            if (historyStr.get(i).equals(query)) {
                isExist = true;
                break;
            }
        }
        if (isExist)
            return;
        String str = query;
        int MaxSize = historyStr.size() > MAX_HISTORY_SIZE ? MAX_HISTORY_SIZE : historyStr.size();
        Logger.v(TAG, "Max Size " + MaxSize);
        for (int i = 0; i < MaxSize; i++) {
            str += "&&" + historyStr.get(i);
        }
        historyStr.add(0, query);
        LocalDate.getInstance(this).setLocalDate("SearchHistory", str);
        //EventBus.getDefault().post(new SearchHistoryEvent(new ArrayList<String>()));
    }

    public void clearSearchHistory() {
        LocalDate.getInstance(this).setLocalDate("SearchHistory", "");
        historyStr = new ArrayList<>();
    }

    /**
     * 供外部调用的搜索方法
     *
     * @param text
     */
    public void search(String text) {
        mSearchView.setQuery(text, true);
    }

    public void setFragment(int state) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (state) {
            case SEARCH:
                if (mSearchFragment == null) {
                    mSearchFragment = new SearchFragment();
                }
                transaction.setCustomAnimations(
                        R.animator.fragment_slide_right_in, R.animator.fragment_slide_left_out,
                        R.animator.fragment_slide_left_in, R.animator.fragment_slide_right_out);
                transaction.replace(R.id.feagment_searchactivity, mSearchFragment);
                this.state = SEARCH;
                break;
            case SHOW:
                if (mSearchResultFragment == null) {
                    mSearchResultFragment = new SearchResultFragment();
                }
                transaction.setCustomAnimations(
                        R.animator.fragment_slide_left_in, R.animator.fragment_slide_right_out,
                        R.animator.fragment_slide_right_in, R.animator.fragment_slide_left_out);
                transaction.replace(R.id.feagment_searchactivity, mSearchResultFragment);
                this.state = SHOW;
                break;
        }
        transaction.commit();
    }


    @OnClick({R.id.tv_search_cancel, R.id.searchview_searchactivity_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search_cancel:
                if (state == SEARCH)
                    finish();
                else if (state == SHOW)
                    setFragment(SEARCH);
                break;
            case R.id.searchview_searchactivity_search:

                break;
        }
    }

    public List<String> getHistoryStr() {
        return historyStr;
    }

    public void setHistoryStr(List<String> historyStr) {
        this.historyStr = historyStr;
    }
}
