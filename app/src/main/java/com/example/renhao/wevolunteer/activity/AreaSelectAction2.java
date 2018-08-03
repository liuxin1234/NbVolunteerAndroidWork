package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.area.AreaListDto;
import com.example.model.area.AreaQueryOptionDto;
import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.adapter.SimpleAdapter1;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.example.renhao.wevolunteer.event.SearchHistoryEvent;
import com.example.renhao.wevolunteer.utils.ActionBarUtils;
import com.example.renhao.wevolunteer.utils.SoftKeyBoardUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

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
 * 创建时间：2016/8/30 18:01
 * 修改备注：区域选择界面
 */
public class AreaSelectAction2 extends BaseActivity {
    private static final String TAG = "AreaSelectAction2";
    public static final String parentId = "ac689592-5a3e-4015-8609-cdeed42df6ab";
    @Bind(R.id.lv_chose_1)
    ListView mListView;

    private int choseModel = SimpleAdapter1.MULTIPLY;//单选多选改这里 SINGLE：单选  MULTIPLY：多选

    private SimpleAdapter1 mAdapter;
    private List<SimpleAdapter1.ItemDate> items = new ArrayList<>();

    private VolunteerViewDto mVolunteerViewDto;
    private int type;

    private String[] lastParentId = new String[20];
    private int hierarchy = -1;//层级

    private Map<String, List<AreaListDto>> maps = new HashMap<>();
    private LinkedHashMap<String, String> selects = new LinkedHashMap<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chose_1);
        ButterKnife.bind(this);

        View actionBar = setActionBar();
        ActionBarUtils.setImgBack(actionBar, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBack();
            }
        });
        ActionBarUtils.setTvTitlet(actionBar,"居住区域");
        ActionBarUtils.setTvSubmit(actionBar,"提交", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        //type: 1 时为注册界面的传递过来的值  没有name的值 默认为-1  为个人资料界面点击区域直接进入该界面，并未传值。
        type = getIntent().getIntExtra("type", -1);//第二个参数是defaultValue 是当你没有设置name的值，没有找到name的时候，返回的默认值。
        mVolunteerViewDto = (VolunteerViewDto) getIntent().getSerializableExtra("personal_data");
        init();
        getDate(parentId, true);
    }

    private void init() {
        //获取默认选中的
        if (choseModel == SimpleAdapter1.SINGLE) {
            //单选模式
            String id = "";
            String name = "";
            selects = new LinkedHashMap<>();
            if (mVolunteerViewDto.getAreaCode() != null){
                id = mVolunteerViewDto.getAreaCode();
                name = mVolunteerViewDto.getAreaName();
            }
            if (!TextUtils.isEmpty(id)){
                selects.put(id, name);
            }
        } else {
            //多选模式
            String tempId = mVolunteerViewDto.getAreaCode();
            String tempName = mVolunteerViewDto.getAreaName();
            if (!TextUtils.isEmpty(tempId)) {
                try {
                    String[] ids = tempId.split(",");
                    String[] names = tempName.split(",");
                    if (ids.length > 0)
                        for (int i = 0; i < ids.length; i++) {
                            selects.put(ids[i], names[i]);
                        }
                }catch (Exception e){

                }
            }
        }

        mAdapter = new SimpleAdapter1(this, items);
        mAdapter.setChoseModle(choseModel);
        mAdapter.setOnCheckedChangeListener(new SimpleAdapter1.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SimpleAdapter1.ItemDate date, boolean isChecked) {
//                Logger.v(TAG, isChecked + "  ");
                AreaListDto dto = (AreaListDto) date.getValue();
                if (choseModel == SimpleAdapter1.MULTIPLY) {
                    if (isChecked) {
                        selects.put(dto.getCode(), dto.getName());
                    } else {
                        selects.remove(dto.getCode());
                    }
                } else {
                    if (isChecked) {
                        selects = new LinkedHashMap<>();
                        selects.put(dto.getCode(), dto.getName());
                    } else {
                        selects.remove(dto.getCode());
                    }
                }
            }
        });
        mAdapter.setOnItemClickListener(new SimpleAdapter1.OnItemClickListener() {
            @Override
            public void onItemClickListener(SimpleAdapter1.ItemDate date, int position) {
                AreaListDto dto = (AreaListDto) date.getValue();
                getDate(dto.getId(), true);
            }
        });
        mListView.setAdapter(mAdapter);
    }

    private void getDate(final String parentId, final boolean next) {
        showNormalDialog("正在加载数据...");

        List<AreaListDto> list = maps.get(parentId);
        if (list == null) {
            AppActionImpl.getInstance(this).AreaQuery(parentId,
                    new ActionCallbackListener<List<AreaListDto>>() {
                        @Override
                        public void onSuccess(List<AreaListDto> data) {
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
                            setDateListView(data);
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
            setDateListView(list);
        }
        dissMissNormalDialog();
    }

    private void setDateListView(List<AreaListDto> date) {
        items = new ArrayList<>();
        for (int i = 0; i < date.size(); i++) {
            AreaListDto listDto = date.get(i);
            SimpleAdapter1.ItemDate itemDate = new SimpleAdapter1.ItemDate();
            //此处containsKey(Object key) 方法是用来检查此映射是否包含指定键的映射关系。
            //该方法调用返回true，如果此映射包含指定键的映射关系。如果点击区域上的名字没有下一级，则为false.
            if (!selects.containsKey(listDto.getCode())) {
                itemDate.setChecked(false);
            } else {
                itemDate.setChecked(true);
                selects.put(listDto.getCode(), listDto.getName());
            }
            itemDate.setTitle(listDto.getName());
            itemDate.setValue(listDto);
            items.add(itemDate);
        }
        mAdapter.setDate(items);
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
            finish();
        } else {
            getDate(lastParentId[hierarchy - 1], false);
        }
    }

    private void submit() {
        if (selects.size() == 0) {
            showToast("请选择");
            return;
        }
        if (selects.size() > 5){
            showToast("居住区域不能超过5个");
            dissMissNormalDialog();
            return;
        }
        showNormalDialog("正在提交");
        String areaCode = "";
        String areaName = "";
//        Logger.e(selects.toString());
        for (String key : selects.keySet()) {
            areaCode += key + ",";
            areaName += selects.get(key) + ",";
        }
        areaCode = areaCode.substring(0, areaCode.length() - 1);
        areaName = areaName.substring(0, areaName.length() - 1);

        mVolunteerViewDto.setAreaCodes(areaCode);
        mVolunteerViewDto.setAreaName(areaName);
        if (type > -1) { //注册界面
            Intent intent = new Intent();
            intent.putExtra("areaName", areaName);
            intent.putExtra("areaCode", areaCode);
            intent.putExtra("personal_data", mVolunteerViewDto);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            List<VolunteerViewDto> list = new ArrayList<>();
            list.add(mVolunteerViewDto);
            AppActionImpl.getInstance(this).volunteerUpdate(list, new ActionCallbackListener<String>() {
                @Override
                public void onSuccess(String data) {
                    dissMissNormalDialog();
                    Intent intent = new Intent();
                    intent.putExtra("personal_data", mVolunteerViewDto);
                    intent.putExtra("AreaCodes",mVolunteerViewDto.getAreaCodes());
                    setResult(RESULT_OK, intent);
                    finish();
                }

                @Override
                public void onFailure(String errorEvent, String message) {
                    dissMissNormalDialog();
                    showToast("提交失败，请稍后重试");
                }
            });
        }
    }
}
