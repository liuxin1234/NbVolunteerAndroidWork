package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.organization.OrganizationListDto;
import com.example.model.organization.OrganizationQueryOptionDto;
import com.example.model.user.UserDepartmentViewDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.SimpleAdapter1;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.utils.ActionBarUtils;
import com.example.renhao.wevolunteer.utils.SoftKeyBoardUtils;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/29 23:22
 * 修改备注：所属机构的选择界面
 */
public class OrgSelectActivity extends BaseActivity {
    private static final String TAG = "OrgSelectActivity";
    private static final String parentId = "ae14862e-6383-4d23-9a5d-cc3caaad7e99";
//    @Bind(R.id.iv_ab_sp1_back)
//    ImageView mBack;
//    @Bind(R.id.tv_ab_sp1_title)
//    TextView mTitle;
//    @Bind(R.id.tv_ab_sp1_submit)
//    TextView mSubmit;
    @Bind(R.id.lv_chose_1)
    ListView mListView;
    @Bind(R.id.et_View)
    EditText mEtSearchView;
    @Bind(R.id.tv_Search)
    TextView tvSearch;

    private int choseModel = SimpleAdapter1.MULTIPLY;//单选多选改这里

    private SimpleAdapter1 mAdapter;
    private List<SimpleAdapter1.ItemDate> items = new ArrayList<>();

    private VolunteerViewDto mVolunteerViewDto;
    private int type;

    private String[] lastParentId = new String[20];
    private int hierarchy = -1;//层级

    private Map<String, List<OrganizationListDto>> maps = new HashMap<>();
    private LinkedHashMap<String, String> selects = new LinkedHashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_select_chose);
        ButterKnife.bind(this);

        View actionBar = setActionBar();
        ActionBarUtils.setImgBack(actionBar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBack();
            }
        });
        ActionBarUtils.setTvTitlet(actionBar,"所属机构");
        ActionBarUtils.setTvSubmit(actionBar,"提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        type = getIntent().getIntExtra("type", -1);
        mVolunteerViewDto = (VolunteerViewDto) getIntent().getSerializableExtra("personal_data");

        init();
        getDate(parentId, true);
    }



    private void init() {
//        mTitle.setText("所属机构");
        try {
            //获取默认选中的
            if (choseModel == SimpleAdapter1.SINGLE) {
                //单选
                selects = new LinkedHashMap<>();
                String id = mVolunteerViewDto.getOrganizationId();
                if (TextUtils.isEmpty(id))
                    return;
                selects.put(mVolunteerViewDto.getOrganizationId(), mVolunteerViewDto.getOrganizationName());
            } else {
                //多选
//                List<UserDepartmentViewDto> temp;
                String userID = mVolunteerViewDto.getId();
                if (type <= -1) {
                    //这里是个人修改界面进入
                    AppActionImpl.getInstance(this).volunteer_departmentQuery(userID,
                            new ActionCallbackListener<List<UserDepartmentViewDto>>() {
                                @Override
                                public void onSuccess(List<UserDepartmentViewDto> data) {
                                    if (data == null)
                                        return;
                                    if (data.size() > 0)
                                        for (int i = 0; i < data.size(); i++) {
                                            selects.put(data.get(i).getOrganizationId(), data.get(i).getOrganizationName());
                                        }
                                }

                                @Override
                                public void onFailure(String errorEvent, String message) {

                                }
                            });
                } else {
                    //这里是完善个人资料界面进入 其实这里感觉没必要写以下代码，进入肯定是空的 直接走了catch
                    try {
                        String orgids = mVolunteerViewDto.getOrgIds();
                        String orgnames = getIntent().getStringExtra("orgnames");
                        String[] ids = orgids.split(",");
                        String[] names = orgnames.split(",");
                        if (ids.length > 0)
                            for (int i = 0; i < ids.length; i++) {
                                selects.put(ids[i], names[i]);
                            }
                    } catch (Exception e) {

                    }
                }

            }
        } catch (Exception e) {
        }

        mAdapter = new SimpleAdapter1(this, items);
        mAdapter.setChoseModle(choseModel);
        mAdapter.setOnCheckedChangeListener(new SimpleAdapter1.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SimpleAdapter1.ItemDate date, boolean isChecked) {
                Logger.v(TAG, isChecked + "  ");
                OrganizationListDto dto = (OrganizationListDto) date.getValue();
                if (choseModel == SimpleAdapter1.MULTIPLY) {
                    if (isChecked) {
                        selects.put(dto.getId(), dto.getName());
                    } else {
                        selects.remove(dto.getId());
                    }
                } else {
                    if (isChecked) {
                        selects = new LinkedHashMap<String, String>();
                        selects.put(dto.getId(), dto.getName());
                    } else {
                        selects.remove(dto.getId());
                    }
                }
            }
        });
        mAdapter.setOnItemClickListener(new SimpleAdapter1.OnItemClickListener() {
            @Override
            public void onItemClickListener(SimpleAdapter1.ItemDate date, int position) {
                OrganizationListDto dto = (OrganizationListDto) date.getValue();
                getDate(dto.getId(), true);
            }
        });
        mListView.setAdapter(mAdapter);
    }

    private void getDate(final String parentId, final boolean next) {
        showNormalDialog("正在加载数据...");

        List<OrganizationListDto> list = maps.get(parentId);
        if (list == null) {
            AppActionImpl.getInstance(this).organizationQueryChild(parentId,
                    new ActionCallbackListener<List<OrganizationListDto>>() {
                        @Override
                        public void onSuccess(List<OrganizationListDto> data) {
                            dissMissNormalDialog();
                            if (data == null || data.size() < 1) {
                                showToast("已经是最后一层");
                                return;
                            }
                            maps.put(parentId, data);
                            if (next)
                                hierarchy++;
                            else
                                hierarchy--;
                            lastParentId[hierarchy] = parentId;
                            setDateListView(data,false);
                        }

                        @Override
                        public void onFailure(String errorEvent, String message) {
                            dissMissNormalDialog();
                            showToast(message);
                        }
                    });
        } else {
            dissMissNormalDialog();
            if (next)
                hierarchy++;
            else
                hierarchy--;
            lastParentId[hierarchy] = parentId;
            setDateListView(list,false);
        }
    }


    private void setDateListView(List<OrganizationListDto> date,boolean isSearch) {
        items = new ArrayList<>();
        for (int i = 0; i < date.size(); i++) {
            OrganizationListDto listDto = date.get(i);
            SimpleAdapter1.ItemDate itemDate = new SimpleAdapter1.ItemDate();
            if (!selects.containsKey(listDto.getId())) {
                itemDate.setChecked(false);
            } else {
                itemDate.setChecked(true);
                selects.put(listDto.getId(), listDto.getName());
            }
            itemDate.setTitle(listDto.getName());
            itemDate.setValue(listDto);
            items.add(itemDate);
        }
        if (hierarchy == 0 || hierarchy == -1){
            if (isSearch){
                mAdapter.setDate(items, true);
            }else {
                mAdapter.setDate(items, false);
            }
        }
        else{
            mAdapter.setDate(items, true);
        }
    }

    @OnClick({R.id.tv_Search})
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.iv_ab_sp1_back:
//
//                break;
//            case R.id.tv_ab_sp1_submit:
//                submit();
//                break;
            case R.id.tv_Search:
                String keyWord = mEtSearchView.getText().toString();
                getSearchData(keyWord);
                mEtSearchView.clearFocus();
                SoftKeyBoardUtils.closeKeybord(mEtSearchView,this);
                break;
        }
    }

    /**
     *  模糊搜索机构方法
     * @param keyWord
     */
    private void getSearchData(String keyWord) {
        OrganizationQueryOptionDto queryOptionDto = new OrganizationQueryOptionDto();
        queryOptionDto.setKeyWord(keyWord);
        AppActionImpl.getInstance(this).organizationQuery(queryOptionDto, new ActionCallbackListener<PagedListEntityDto<OrganizationListDto>>() {
            @Override
            public void onSuccess(PagedListEntityDto<OrganizationListDto> data) {
                if (data == null){
                    return;
                }
                List<OrganizationListDto> rows = data.getRows();
                if (rows == null || rows.size() < 0){
                    return;
                }
                setDateListView(rows,true);
            }

            @Override
            public void onFailure(String errorEvent, String message) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (hierarchy == 0 || hierarchy == -1) {
            finish();
        } else {
            getDate(lastParentId[hierarchy - 1], false);
        }
    }

    private void initBack() {
        if (hierarchy == 0 || hierarchy == -1) {
            if(mEtSearchView.hasFocus()){
                mEtSearchView.clearFocus();
                SoftKeyBoardUtils.closeKeybord(mEtSearchView,this);
            }else {
                finish();
            }
        } else {
            if(mEtSearchView.hasFocus()){
                mEtSearchView.clearFocus();
                SoftKeyBoardUtils.closeKeybord(mEtSearchView,this);
            }else {
                getDate(lastParentId[hierarchy - 1], false);
            }
        }
    }

    private void submit() {
        if (selects.size() == 0) {
            showToast("请选择");
            dissMissNormalDialog();
            return;
        }
        if (selects.size() > 5){
            showToast("所选机构不能超过5个");
            dissMissNormalDialog();
            return;
        }
        showNormalDialog("正在提交");
        String orgName = "";
        String orgId = "";
        for (String key : selects.keySet()) {
            orgId += key + ",";
            orgName += selects.get(key) + ",";
        }
        orgId = orgId.substring(0, orgId.length() - 1);
        orgName = orgName.substring(0, orgName.length() - 1);
//        System.out.println(orgName);
        String[] main_orgName = orgName.split(",");
//        System.out.println(main_orgName[0]);
        if (type > -1) {
            Intent intent = new Intent();
            mVolunteerViewDto.setOrgIds(orgId);
            intent.putExtra("personal_data", mVolunteerViewDto);
            intent.putExtra("orgName", orgName);
            intent.putExtra("orgId", orgId);
            setResult(RESULT_OK, intent);
            dissMissNormalDialog();
            finish();
        } else {
            mVolunteerViewDto.setOrgIds(orgId);
            mVolunteerViewDto.setOrganizationName(main_orgName[0]);
            List<VolunteerViewDto> list = new ArrayList<>();
            list.add(mVolunteerViewDto);
            AppActionImpl.getInstance(this).volunteerUpdate(list, new ActionCallbackListener<String>() {
                @Override
                public void onSuccess(String data) {
                    dissMissNormalDialog();
                    showToast("提交成功");
                    Intent intent = new Intent();
                    intent.putExtra("personal_data", mVolunteerViewDto);
                    setResult(RESULT_OK, intent);
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
