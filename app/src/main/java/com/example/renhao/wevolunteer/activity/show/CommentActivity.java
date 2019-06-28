package com.example.renhao.wevolunteer.activity.show;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.shareMessage.SharemessageDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.ActionBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by on
 * 项目名称：com.example.renhao.wevolunteer.activity.show
 * 项目日期：2019/5/7
 * 作者：liux
 * 邮箱：750954283@qq.com
 * 公司：nbzhongjing
 * 功能：秀一秀 ——> 评论
 */

public class CommentActivity extends BaseActivity {

    @Bind(R.id.et_comment)
    EditText etComment;

    private List<SharemessageDto> mList = new ArrayList<>();

    private String shareId;
    private String volunteerId;
    private String volunteerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        View actionBar = setActionBar();
        ActionBarUtils.setImgBack(actionBar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBarUtils.setTvTitlet(actionBar, getResources().getString(R.string.tv_comment));
        ActionBarUtils.setTvSubmit(actionBar, "提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        Intent intent = getIntent();
        if (intent != null){
          shareId = intent.getStringExtra("ShareId");
          volunteerId = intent.getStringExtra("VolunteerId");
          volunteerName = intent.getStringExtra("VolunteerName");
        }
    }


    public void submit(){
        SharemessageDto sharemessageDto = new SharemessageDto();
        sharemessageDto.setShareId(shareId);
        sharemessageDto.setVolunteerId(volunteerId);
        sharemessageDto.setVolunteerName(volunteerName);
        sharemessageDto.setDescription(etComment.getText().toString());
        mList.add(sharemessageDto);
        AppActionImpl.getInstance(this).postShareMessageCreate(mList, new ActionCallbackListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                if (data != null){
                    showToast("提交成功");
                    finish();
                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {
                showToast("提交失败："+message);
            }
        });
    }
}
