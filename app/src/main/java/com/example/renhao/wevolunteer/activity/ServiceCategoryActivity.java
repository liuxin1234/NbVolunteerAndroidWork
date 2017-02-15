package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;

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
public class ServiceCategoryActivity extends BaseActivity {
    private static final String TAG = "ServiceCategoryActivity";
    @Bind(R.id.imageView)
    ImageView mBack;
    @Bind(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    @Bind(R.id.listView_service_category)
    ListView listView;
    @Bind(R.id.tv_serverCategory_submit)
    TextView mSubmit;

    private List<String> actions;
    private List<String> actionCodes;
    private List<Boolean> isSelect;
    private Map<String, String> selectCode;
    private boolean otherSelsec = false;
    private String other = "";
    private int type;

    private ArrayAdapter<String> arrayAdapter;

    private VolunteerViewDto personalData;

    private Map<String, String> selects = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_category);
        ButterKnife.bind(this);
        selectCode = new HashMap<>();

        personalData = (VolunteerViewDto) getIntent().getSerializableExtra("personal_data");
        type = getIntent().getIntExtra("type", -1);
        initListView();
        //获取专业特长的类别字典
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
                            if (selects.containsKey(data.get(i).getCode())) {
                                isSelect.add(true);
                                listView.setItemChecked(i, true);
                            } else {
                                isSelect.add(false);
                            }
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

        String temp = personalData.getServiceIntention();
        if (!TextUtils.isEmpty(temp)) {
            String[] intentions = temp.split(",");
            for (int i = 0; i < intentions.length; i++) {
                selects.put(intentions[i], "intention");
                if (intentions[i].equals("00025")) {
                    otherSelsec = true;
                    other = personalData.getServiceIntentionOther();
                }
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                boolean select = isSelect.get(position);
                isSelect.set(position, !select);
                if (actionCodes.get(position).equals("00025")) {
                    //判断是否选中
                    if (select) {
                        otherSelsec = false;
                    } else {
                        otherSelsec = true;
                        //弹出dialog 输入其它选项
                        showOtherDialog();
                    }
                }
            }
        });
    }

    private void showOtherDialog() {
        Holder holder = new ViewHolder(R.layout.dialog_edittext);
        final DialogPlus dialogPlus = DialogPlus.newDialog(this)
                .setContentHolder(holder)
                .setCancelable(true)
                .setGravity(Gravity.CENTER)
                .create();
        Button submitBtn = (Button) dialogPlus.getHolderView().findViewById(R.id.btn_dialog_submit);
        Button cancelBtn = (Button) dialogPlus.getHolderView().findViewById(R.id.btn_dialog_cancel);
        final EditText otherEt = (EditText) dialogPlus.getHolderView().findViewById(R.id.et_dialog);

        if (!TextUtils.isEmpty(other)) {
            otherEt.setText(other);
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = otherEt.getText().toString();
                if (TextUtils.isEmpty(msg)) {
                    showToast("请输入");
                    return;
                }
                other = msg;
                dialogPlus.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogPlus.dismiss();
            }
        });
        dialogPlus.show();
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
        showNormalDialog("正在提交");
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
        //提交 actionCodes
        personalData.setServiceIntention(serviceIntention);
        if (otherSelsec) {
            personalData.setServiceIntentionOther(other);
        }

        if (type > -1) {
            Intent intent = new Intent();
            intent.putExtra("personal_data", personalData);
            intent.putExtra("serviceIntentionStr", serviceIntentionStr);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            List<VolunteerViewDto> list = new ArrayList<>();
            list.add(personalData);
            final String finalServiceIntentionStr = serviceIntentionStr;
            AppActionImpl.getInstance(this).volunteerUpdate(list,
                    new ActionCallbackListener<String>() {
                        @Override
                        public void onSuccess(String data) {
                            showToast("修改成功");
                            Intent intent = new Intent();
                            intent.putExtra("personal_data", personalData);
                            intent.putExtra("serviceIntentionStr", finalServiceIntentionStr);
                            setResult(RESULT_OK,intent);
                            dissMissNormalDialog();
                            finish();
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            dissMissNormalDialog();
                            showToast("提交失败，请稍后重试");
                        }
                    });
        }
        dissMissNormalDialog();
    }


}
