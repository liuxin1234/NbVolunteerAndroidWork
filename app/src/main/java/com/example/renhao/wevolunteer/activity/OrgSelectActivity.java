package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.organization.OrganizationListDto;
import com.example.model.user.UserDepartmentViewDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.SimpleAdapter1;
import com.example.renhao.wevolunteer.base.BaseActivity;
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
 * 修改备注：
 */
public class OrgSelectActivity extends BaseActivity {
    private static final String TAG = "OrgSelectActivity";
    private static final String parentId = "ae14862e-6383-4d23-9a5d-cc3caaad7e99";
    @Bind(R.id.iv_ab_sp1_back)
    ImageView mBack;
    @Bind(R.id.tv_ab_sp1_title)
    TextView mTitle;
    @Bind(R.id.tv_ab_sp1_submit)
    TextView mSubmit;
    @Bind(R.id.lv_chose_1)
    ListView mListView;

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
        setContentView(R.layout.layout_chose_1);
        ButterKnife.bind(this);

        type = getIntent().getIntExtra("type", -1);
        mVolunteerViewDto = (VolunteerViewDto) getIntent().getSerializableExtra("personal_data");

        init();
        getDate(parentId, true);
    }

    private void init() {
        mTitle.setText("所属机构");
        try {
            //获取默认选中的
            if (choseModel == SimpleAdapter1.SINGLE) {
                selects = new LinkedHashMap<>();
                String id = mVolunteerViewDto.getOrganizationId();
                if (TextUtils.isEmpty(id))
                    return;
                selects.put(mVolunteerViewDto.getOrganizationId(), mVolunteerViewDto.getOrganizationName());
            } else {
                List<UserDepartmentViewDto> temp;
                String userID = mVolunteerViewDto.getId();
                if (type <= -1) {
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
                            setDate2ListView(data);
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
            setDate2ListView(list);
        }
    }


    private void setDate2ListView(List<OrganizationListDto> date) {
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
        if (hierarchy == 0 || hierarchy == -1)
            mAdapter.setDate(items, false);
        else
            mAdapter.setDate(items, true);
    }

    @OnClick({R.id.iv_ab_sp1_back, R.id.tv_ab_sp1_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_ab_sp1_back:
                if (hierarchy == 0 || hierarchy == -1) {
                    finish();
                } else {
                    getDate(lastParentId[hierarchy - 1], false);
                }
                break;
            case R.id.tv_ab_sp1_submit:
                submit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (hierarchy == 0 || hierarchy == -1) {
            finish();
        } else {
            getDate(lastParentId[hierarchy - 1], false);
        }
    }

    private void submit() {
        showNormalDialog("正在提交");
        if (selects.size() == 0) {
            showToast("请选择");
            dissMissNormalDialog();
            return;
        }
        String orgName = "";
        String orgId = "";
        for (String key : selects.keySet()) {
            orgId += key + ",";
            orgName += selects.get(key) + ",";
        }
        orgId = orgId.substring(0, orgId.length() - 1);
        orgName = orgName.substring(0, orgName.length() - 1);
        System.out.println(orgName);
        String[] main_orgName = orgName.split(",");
        System.out.println(main_orgName[0]);
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
