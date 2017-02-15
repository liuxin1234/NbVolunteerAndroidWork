package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
 * 居住地界面
 */
public class ResidenceActivity extends BaseActivity {
    private static final String TAG = "ResidenceActivity";

    private VolunteerViewDto personal_data;
    private String Domicile;

    private TextView tv_submit;
    private EditText edit_Domicile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residence);

        Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
        edit_Domicile = (EditText) findViewById(R.id.edit_Domicile);
        if (personal_data.getDomicile() != null) {
            Domicile = personal_data.getDomicile();
            edit_Domicile.setText(Domicile);
        }


        tv_submit = (TextView) findViewById(R.id.tv_Domicile_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog("正在提交");
                Domicile = edit_Domicile.getText().toString();
                List<VolunteerViewDto> vl_updates = new ArrayList<>();
                personal_data.setDomicile(Domicile);
                vl_updates.add(personal_data);
                AppActionImpl.getInstance(getApplicationContext()).volunteerUpdate(vl_updates, new ActionCallbackListener<String>() {
                    @Override
                    public void onSuccess(String data) {
                        dissMissNormalDialog();
                        Intent result = new Intent();
                        result.putExtra("personal_data", personal_data);
                        setResult(RESULT_OK, result);
                        showToast("修改成功");
                        ResidenceActivity.this.finish();
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        showToast("提交失败，请稍后重试");
                        dissMissNormalDialog();
                    }
                });
            }
        });

        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResidenceActivity.this.finish();
            }
        });

    }
}
