package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 昵称界面
 */
public class NickNameActivity extends BaseActivity implements View.OnFocusChangeListener {
    private static final String TAG = "NickNameActivity";


    private VolunteerViewDto personal_data;
    private String MyNickName;
    private boolean IsShowTrueName;

    private EditText et_nickname;
    private CheckBox isShow;
    private TextView update_submit;
    private ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick_name);

        Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
        MyNickName = personal_data.getNickName();
        IsShowTrueName = personal_data.getShowTrueName() == null ? false : personal_data.getShowTrueName();

        et_nickname = (EditText) findViewById(R.id.edit_NickName_name);
        isShow = (CheckBox) findViewById(R.id.checkBox_NickName_isShow);
        if (MyNickName != null)
            et_nickname.setText(MyNickName);

        if (isShow != null) {
            isShow.setChecked(IsShowTrueName);
        }


        update_submit = (TextView) findViewById(R.id.tv_nickname_update);
        update_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog("正在提交数据");
                MyNickName = et_nickname.getText().toString();
                IsShowTrueName = isShow.isChecked();
                //修改项
                personal_data.setNickName(MyNickName);
                personal_data.setShowTrueName(IsShowTrueName);


                List<VolunteerViewDto> vl_updates = new ArrayList<>();
                vl_updates.add(personal_data);
                AppActionImpl.getInstance(getApplicationContext()).volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        dissMissNormalDialog();
                        Intent result = new Intent();
                        result.putExtra("personal_data", personal_data);
                        setResult(RESULT_OK, result);
                        showToast("修改成功");
                        NickNameActivity.this.finish();

                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("网络异常，请检查后重试");
                        dissMissNormalDialog();
                    }
                });
                dissMissNormalDialog();
            }
        });

        //回退按钮
        btn_back = (ImageView) findViewById(R.id.imageView_nickname_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NickNameActivity.this.finish();
            }
        });
        et_nickname.setOnFocusChangeListener(this);
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.edit_NickName_name:
                setHintEt(et_nickname,hasFocus);
                break;
            default:
                break;
        }
    }

    private void setHintEt(EditText et,boolean hasFocus){
        String hint;
        if(hasFocus){
            hint = et.getHint().toString();
            et.setTag(hint);
            et.setHint("");
        }else{
            hint = et.getTag().toString();
            et.setHint(hint);
        }
    }
}
