package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 政治面貌
 */
public class PoliticsstatusActivity extends BaseActivity {
    private static final String TAG = "ResidenceActivity";
    @Bind(R.id.imageView_btn_back)
    ImageView mBack;
    @Bind(R.id.textView)
    TextView mTitle;
    @Bind(R.id.tv_myPolity_submit)
    TextView mSubmit;
    @Bind(R.id.rg_politicsstatus_selsec)
    ListView mRgSelsec;

    private VolunteerViewDto personalData;

    private String myPolity;

    private int check = -1;

    private List<String> codes;
    private List<String> names;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicsstatus);
        ButterKnife.bind(this);

        type = getIntent().getIntExtra("type", -1);

        Intent intent = getIntent();
        personalData = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
        if (personalData.getPolity() != null)
            myPolity = personalData.getPolity();
        initView();

    }

    private void initView() {
        showNormalDialog("正在加载数据...");
        //获取字典选项
        AppActionImpl.getInstance(this).dictionaryQueryDefault("PoliticalAttribute", "",
                new ActionCallbackListener<List<DictionaryListDto>>() {
                    @Override
                    public void onSuccess(List<DictionaryListDto> data) {
                        dissMissNormalDialog();
                        if (data == null || data.size() < 1)
                            return;
                        codes = new ArrayList<String>();
                        names = new ArrayList<String>();
                        int temp = -1;
                        for (int i = 0; i < data.size(); i++) {
                            String name = data.get(i).getName();
                            String code = data.get(i).getCode();
                            codes.add(code);
                            names.add(name);
                            if (code.equals(myPolity)) {
                                temp = i;
                                check = i;
                            }
                        }

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(PoliticsstatusActivity.this, android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, names);
                        mRgSelsec.setAdapter(arrayAdapter);
                        mRgSelsec.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//这里我们直接在源代码中设置选择模式
                        if (temp > -1)
                            mRgSelsec.setItemChecked(temp, true);
                        mRgSelsec.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                        showToast(message);
                    }
                });

    }


    @OnClick({R.id.imageView_btn_back, R.id.tv_myPolity_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_btn_back:
                finish();
                break;
            case R.id.tv_myPolity_submit:
                submit();
                break;
        }
    }

    private void submit() {
        showNormalDialog("正在提交");
        if (check == -1) {
            showToast("请选择一项");
            dissMissNormalDialog();
            return;
        }
        personalData.setPolity(codes.get(check));
        if (type > -1) {
            Intent intent = new Intent();
            intent.putExtra("personal_data", personalData);
            intent.putExtra("polity_name", names.get(check));
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
                            intent.putExtra("polity_name", names.get(check));
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
        dissMissNormalDialog();
    }
}
