package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 意向志愿服务类别
 */
public class ApplyServiceCategoryActivity extends BaseActivity {
    private static final String TAG = "ApplyServiceCategoryActivity";
    @Bind(R.id.imageView)
    ImageView mBack;
    @Bind(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    @Bind(R.id.listView_service_category)
    ListView listView;
    @Bind(R.id.tv_serverCategory_submit)
    TextView mSubmit;

    private int type;

    private List<String> actions;
    private List<String> actionCodes;
    private List<Boolean> isSelect;
    private Map<String, String> selectCode;

    private ArrayAdapter<String> arrayAdapter;

    private VolunteerViewDto personalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_category);
        ButterKnife.bind(this);

        type = getIntent().getIntExtra("type", -1);

        selectCode = new HashMap<>();

        personalData = (VolunteerViewDto) getIntent().getSerializableExtra("personal_data");

        initListView();
        //获取服务类型的类别字典
        //显示到listview中
        AppActionImpl.getInstance(this).dictionaryQueryDefault("ActivityType", "",
                new ActionCallbackListener<List<DictionaryListDto>>() {
                    @Override
                    public void onSuccess(List<DictionaryListDto> data) {
                        if (data == null || data.size() < 1)
                            return;
                        arrayAdapter.clear();
                        actions = new ArrayList<String>();
                        actionCodes = new ArrayList<String>();
                        isSelect = new ArrayList<Boolean>();

                        for (int i = 0; i < data.size(); i++) {
                            isSelect.add(false);
                            actions.add(data.get(i).getName());
                            arrayAdapter.add(data.get(i).getName());
                            actionCodes.add(data.get(i).getCode());
                        }

                        arrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }

    private void initListView() {
        actions = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, actions);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);//这里我们直接在源代码中设置选择模式
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean select = isSelect.get(position);
                isSelect.set(position, !select);
            }
        });
    }

    @OnClick({R.id.imageView, R.id.tv_serverCategory_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView:
                finish();
                break;
            case R.id.tv_serverCategory_submit:
                submit();
                break;
        }
    }

    private void submit() {
        //获取选择的意向类型code
        String serviceIntention = "";
        String serviceIntentionStr = "";
        for (int i = 0; i < actionCodes.size(); i++) {
            if (isSelect.get(i)) {
                serviceIntention += actionCodes.get(i) + ",";
            }
        }
        for (int i = 0; i < actions.size(); i++) {
            if (isSelect.get(i)) {
                serviceIntentionStr += actions.get(i) + ",";
            }
        }

        if (TextUtils.isEmpty(serviceIntention)) {
            showToast("请选择");
            return;
        }
        serviceIntention = serviceIntention.substring(0, serviceIntention.length() - 1);
        serviceIntentionStr = serviceIntentionStr.substring(0, serviceIntentionStr.length() - 1);
        //提交
        personalData.setServiceIntention(serviceIntention);
        if (type > -1) {
            Intent result = new Intent();
            result.putExtra("personal_data", personalData);
            result.putExtra("serviceIntentionStr", serviceIntentionStr);
            setResult(RESULT_OK, result);
            ApplyServiceCategoryActivity.this.finish();
        }


    }


}
