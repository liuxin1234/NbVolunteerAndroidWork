package com.example.renhao.wevolunteer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.SearchActivity;
import com.example.renhao.wevolunteer.adapter.HistoryAdapter;
import com.example.renhao.wevolunteer.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/19 9:40
 * 修改备注：
 */
public class SearchFragment extends BaseFragment {
    private static final String TAG = "SearchFragment";

    public static final int ACTIVITY = 0;
    public static final int JOB = 1;
    public static final int ORGAZIZATION = 2;

    @Bind(R.id.gridview_search_history)
    GridView mSearchHistory;
    @Bind(R.id.btn_search_clearHistory)
    Button mClearHistory;

    private SearchActivity mSearchActivity;


    private HistoryAdapter mAdapter;
    private List<String> history = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchActivity = (SearchActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        history = mSearchActivity.getHistoryStr();
        mAdapter = new HistoryAdapter(getActivity(), history);
        mSearchHistory.setAdapter(mAdapter);

        mSearchHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSearchActivity.search((String) mAdapter.getItem(position));
            }
        });

        return view;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_search_clearHistory})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_search_clearHistory:
                mSearchActivity.clearSearchHistory();
                mAdapter.setDate(new ArrayList<String>());
                break;
            default:
                break;
        }
    }
}
