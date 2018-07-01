package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.ActionBarUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.activity
 * 项目日期：2018/6/26
 * 作者：liux
 * 功能：民族界面
 *
 * @author 750954283(qq)
 */

public class VolkActivity extends BaseActivity {
    private static final String TAG = "VolkActivity";
    @Bind(R.id.rg_nation_selsec)
    ListView mListView;
    private List<String> codes;
    private List<String> names;
    private int type;
    private VolunteerViewDto personalData;

    private String mVolk;

    private int check = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nation);
        ButterKnife.bind(this);
        View actionBar = setActionBar();
        ActionBarUtils.setImgBack(actionBar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBarUtils.setTvTitlet(actionBar,getResources().getString(R.string.myNation_type_text));
        ActionBarUtils.setTvSubmit(actionBar,"提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        type = getIntent().getIntExtra("type", -1);
        try {
            Intent intent = getIntent();
            personalData = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
            mVolk = personalData.getVolk();
        } catch (Exception e) {
        }
        initView();
    }


    private void initView() {
        showNormalDialog("正在加载数据...");
        //获取字典选项
        AppActionImpl.getInstance(this).dictionaryQueryDefault("Volk", "",
                new ActionCallbackListener<List<DictionaryListDto>>() {
                    @Override
                    public void onSuccess(List<DictionaryListDto> data) {
                        dissMissNormalDialog();
                        if (data == null || data.size() < 1) {
                            return;
                        }
                        codes = new ArrayList<String>();
                        names = new ArrayList<String>();
                        int temp = -1;
                        for (int i = 0; i < data.size(); i++) {
                            String name = data.get(i).getName();
                            String code = data.get(i).getCode();
                            codes.add(code);
                            names.add(name);
                            if (name.equals(mVolk)) {
                                temp = i;
                                check = i;
                            }
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(VolkActivity.this, android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, names);
                        mListView.setAdapter(arrayAdapter);
                        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//这里我们直接在源代码中设置选择模式
                        if (temp > -1) {
                            mListView.setItemChecked(temp, true);
                        }
                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Logger.v(TAG, position + "");
                                check = position;
                            }
                        });
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        dissMissNormalDialog();
                        showToast( message);
                    }
                });
        dissMissNormalDialog();
    }

    private void submit() {
        showNormalDialog("正在提交");
        if (check == -1) {
            showToast("请选择一项");
            dissMissNormalDialog();
            return;
        }
        personalData.setVolk(names.get(check));
//        personalData.setJobStatus(Integer.parseInt(codes.get(check)));
        if (type > -1) {
            Intent intent = new Intent();
            intent.putExtra("personal_data", personalData);
            setResult(RESULT_OK, intent);
            dissMissNormalDialog();
            finish();
        } else {
            List<VolunteerViewDto> list = new ArrayList<>();
            list.add(personalData);
            AppActionImpl.getInstance(this).volunteerUpdate(list,
                    new ActionCallbackListener<String>() {
                        @Override
                        public void onSuccess(String data) {
                            showToast("修改成功");
                            Intent intent = new Intent();
                            intent.putExtra("personal_data", personalData);
                            setResult(RESULT_OK, intent);
                            dissMissNormalDialog();
                            finish();
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            showToast("提交失败，请稍后重试");
                            dissMissNormalDialog();
                        }
                    });
        }
    }
}
