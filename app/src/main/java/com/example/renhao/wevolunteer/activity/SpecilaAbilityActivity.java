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
 *专业能力/特长
 */
public class SpecilaAbilityActivity extends BaseActivity {
    private static final String TAG = "ServiceCategoryActivity";
    @Bind(R.id.imageView)
    ImageView mBack;
    @Bind(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    @Bind(R.id.listView_service_category)
    ListView listView;
    @Bind(R.id.tv_serverCategory_submit)
    TextView mSubmit;
    @Bind(R.id.tv_service_title)
    TextView mTitle;

    private int type;

    private List<String> actions;
    private List<String> actionCodes;
    private List<Boolean> isSelect;
    private Map<String, String> selectCode;
    private boolean otherSelsec = false;
    private String other;

    private ArrayAdapter<String> arrayAdapter;

    private VolunteerViewDto personalData;

    private Map<String, String> selects = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_category);
        ButterKnife.bind(this);
        type = getIntent().getIntExtra("type", -1);
        mTitle.setText("专业特长");

        selectCode = new HashMap<>();

        personalData = (VolunteerViewDto) getIntent().getSerializableExtra("personal_data");

        initListView();
        //获取服务类型的类别字典
        //显示到listview中
        AppActionImpl.getInstance(this).dictionaryQueryDefault("SPECIALITY", "",
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
                       /* for (int i = 0; i < isSelect.size(); i++) {
                            if (isSelect.get(i)) {
                                listView.setItemChecked(i, true);
                            }
                        }*/
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
        String temp = personalData.getSpecialtyType();
        if (!TextUtils.isEmpty(temp)) {
            String[] special = temp.split(",");
            for (int i = 0; i < special.length; i++) {
                selects.put(special[i], "special");
            }
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listView.setItemChecked(position, isSelect.get(position));
               /* boolean select = isSelect.get(position);
                isSelect.set(position, !select);
                if (actionCodes.get(position).equals("QT")) {
                    //判断是否选中
                    if (select) {
                        otherSelsec = false;
                    } else {
                        otherSelsec = true;
                        //弹出dialog 输入其它选项
                        showOtherDialog();
                    }
                }*/
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
        if (actionCodes.size() < 1) {
            showToast("请选择");
            dissMissNormalDialog();
            return;
        }
        //获取选择的意向类型code
        String serviceIntention = "";
        String names = "";
        for (int i = 0; i < actionCodes.size(); i++) {
            if (isSelect.get(i)) {
                if (actionCodes.size() == 1) {
                    serviceIntention += actionCodes.get(i);
                    names += actions.get(i);
                } else {
                    serviceIntention += actionCodes.get(i) + ",";
                    names += actions.get(i) + ",";
                }
            }
        }
        try {
            serviceIntention = serviceIntention.substring(0, serviceIntention.length() - 1);
            names = names.substring(0, names.length() - 1);
        } catch (Exception e) {
            showToast("请选择至少一项");
            dissMissNormalDialog();
            return;
        }


        //提交
        personalData.setSpecialtyType(serviceIntention);
        personalData.setSpecialty(names);
     /*   if (otherSelsec)
        {
            personalData.setsp
        }*/
        if (type > -1) {
            Intent intent = new Intent();
            intent.putExtra("personal_data", personalData);
            setResult(RESULT_OK, intent);
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
                            dissMissNormalDialog();
                            showToast("提交失败，请稍后重试");
                        }
                    });
        }
        dissMissNormalDialog();
    }
}
