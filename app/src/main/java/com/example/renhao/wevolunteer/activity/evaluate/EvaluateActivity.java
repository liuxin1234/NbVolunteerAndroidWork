package com.example.renhao.wevolunteer.activity.evaluate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.blankj.utilcode.util.IntentUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.core.AppActionImpl;
import com.example.core.local.LocalDate;
import com.example.model.ActionCallbackListener;
import com.example.model.appraiseCompany.VolunteerAppraiseCompanyDto;
import com.example.model.dictionary.DictionaryListDto;
import com.example.model.evaluate.EvaluateBean;
import com.example.model.evaluate.StarTypeBean;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.activity.show.SendShowActivity;
import com.example.renhao.wevolunteer.adapter.EvaluateAdapter;
import com.example.renhao.wevolunteer.adapter.StarTypeAdapter;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.common.Constants;
import com.example.renhao.wevolunteer.utils.ActionBarUtils;
import com.example.renhao.wevolunteer.utils.DeviceUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.activity.evaluate
 * 项目日期：2019/5/5
 * 作者：liux
 * 功能：评价功能
 *
 * @author 750954283(qq)
 */

public class EvaluateActivity extends BaseActivity {


    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.et_feed_back)
    EditText etFeedBack;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.rv_star)
    RecyclerView rvStar;
    @Bind(R.id.rb_evaluate)
    RatingBar rbEvaluate;


    private String volunteerId;
    private Integer Rating = 0;

    private EvaluateAdapter mEvaluateAdapter;
    private StarTypeAdapter mStarTypeAdapter;

    private List<DictionaryListDto> mStarTypeList = new ArrayList<>();
    private List<EvaluateBean> mEvaluateBeanList = new ArrayList<>();
    private List<StarTypeBean> mStarTypeBeanList = new ArrayList<>();
    private LinkedHashMap<String, StarTypeBean> selectStarTypes = new LinkedHashMap<>();
    private LinkedHashMap<String, EvaluateBean> selectEvaluates = new LinkedHashMap<>();
    private VolunteerAppraiseCompanyDto mCodeAndScore;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
        ButterKnife.bind(this);
        View actionBar = setActionBar();
        ActionBarUtils.setImgBack(actionBar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ActionBarUtils.setTvTitlet(actionBar, getResources().getString(R.string.tv_evaluate));

//        initView();
        initAdapter();
        getDate();
        rbEvaluate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Rating = (int) rating;
            }
        });

        volunteerId = LocalDate.getInstance(this).getLocalDate(Constants.VOLUNTEER_ID,"");
    }


    private void initAdapter() {

        mEvaluateAdapter = new EvaluateAdapter(this, R.layout.item_evaluate_layout, mEvaluateBeanList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mEvaluateAdapter);
        mEvaluateAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Logger.e("点击了;onItemChildClick");
                Boolean isCheck = mEvaluateBeanList.get(position).getCheck();
                if (isCheck) {
                    mEvaluateBeanList.get(position).setCheck(false);
                    selectEvaluates.remove(String.valueOf(position));
                } else {
                    mEvaluateBeanList.get(position).setCheck(true);
                    selectEvaluates.put(String.valueOf(position), mEvaluateBeanList.get(position));
                }
//                GsonUtils.toJson(selectEvaluates);
                mEvaluateAdapter.notifyDataSetChanged();
            }
        });

        mStarTypeAdapter = new StarTypeAdapter(R.layout.item_star_type_layout, mStarTypeList);
        rvStar.setLayoutManager(new LinearLayoutManager(this));
        rvStar.setNestedScrollingEnabled(false);
        rvStar.setAdapter(mStarTypeAdapter);
        mStarTypeAdapter.setOnStarClickListener(new StarTypeAdapter.OnStarClickListener() {
            @Override
            public void onItemClick(View view, DictionaryListDto listDto, float rating, int position) {
//                showToast("选中了第" + position + "个，值为：" + rating);
                StarTypeBean starTypeBean = new StarTypeBean();
                starTypeBean.setCode(mStarTypeList.get(position).getCode());
                starTypeBean.setName(mStarTypeList.get(position).getName());
                starTypeBean.setRating((int) rating);
                selectStarTypes.put(String.valueOf(position), starTypeBean);
//                GsonUtils.toJson(selectStarTypes);
            }
        });
    }


    @OnClick(R.id.btn_submit)
    public void onViewClicked() {
        submitData();
//        showToast("提交成功");
//        Intent intent = new Intent(this, ShowActivity.class);
//        startActivity(intent);

    }

    public void getDate() {
        //TotalFeelings  STRATYPE  Feelings
        //匿名反馈 星星
        AppActionImpl.getInstance(this).dictionaryQueryDefault("STRATYPE", "", new ActionCallbackListener<List<DictionaryListDto>>() {
            @Override
            public void onSuccess(List<DictionaryListDto> data) {
//                showToast("获取数据成功"+data.toString());
                if (data != null && data.size() > 0) {
                    mStarTypeList.addAll(data);
                    mStarTypeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
        //活动感受
        AppActionImpl.getInstance(this).dictionaryQueryDefault("Feelings", "", new ActionCallbackListener<List<DictionaryListDto>>() {
            @Override
            public void onSuccess(List<DictionaryListDto> data) {
//                showToast("获取数据成功"+data.toString());
                for (DictionaryListDto dto : data) {
                    EvaluateBean bean = new EvaluateBean();
                    bean.setCode(dto.getCode());
                    bean.setName(dto.getName());
                    bean.setCheck(false);
                    mEvaluateBeanList.add(bean);
                }
                mEvaluateAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }

    public void submitData() {
        List<VolunteerAppraiseCompanyDto> mAppraiseCompanyDtoList = new ArrayList<>();
        VolunteerAppraiseCompanyDto appraiseCompanyDto = new VolunteerAppraiseCompanyDto();
        appraiseCompanyDto.setActivityId("ccce225f-6daa-448c-b820-20bc63ea94c7");
        appraiseCompanyDto.setVolunteerId(volunteerId);
        appraiseCompanyDto.setTotalScore(Rating);
        setCodeAndScore(appraiseCompanyDto);
        setFeel(appraiseCompanyDto);
        appraiseCompanyDto.setOpinion(etFeedBack.getText().toString());
        String model = DeviceUtils.getModel();
        if (model != null && !model.isEmpty()) {
            appraiseCompanyDto.setMobileType(model);
        } else {
            appraiseCompanyDto.setMobileType("Android");
        }
        mAppraiseCompanyDtoList.add(appraiseCompanyDto);
        AppActionImpl.getInstance(this).postAppraiseCompanyCreate(mAppraiseCompanyDtoList, new ActionCallbackListener<List<String>>() {
            @Override
            public void onSuccess(List<String> data) {
                showToast("提交成功");
                Intent intent = new Intent(EvaluateActivity.this, SendShowActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }

    public void setCodeAndScore(VolunteerAppraiseCompanyDto dto) {
        if (selectStarTypes.size() == 0){
            showToast("请评价完整后再提交");
            return;
        }
        String code = "";
        String rating = "";
        for (String key : selectStarTypes.keySet()) {
            code +=         selectStarTypes.get(key).getCode() + ",";
            rating += selectStarTypes.get(key).getRating() + ",";
        }
        code = code.substring(0, code.length() - 1);
        rating = rating.substring(0, rating.length() - 1);
        dto.setCode1(code);
        dto.setScore1(rating);
    }

    public void setFeel(VolunteerAppraiseCompanyDto dto) {
        if (selectEvaluates.size() == 0){
            showToast("请评价完整后再提交");
            return;
        }
        String feel = "";
        for (String key : selectEvaluates.keySet()) {
            feel += selectEvaluates.get(key).getName() + ",";
        }
        feel = feel.substring(0, feel.length() - 1);
        dto.setFeeling(feel);
    }

}
