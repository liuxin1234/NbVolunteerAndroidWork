package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.PagedListEntityDto;
import com.example.model.content.ContentListDto;
import com.example.model.content.ContentQueryOptionDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 常见问题界面
 */
public class FAQActivity extends BaseActivity {
    private static final String TAG = "FAQActivity";

    private List<String> actions;
    private List<String> desc;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        listView = (ListView) findViewById(R.id.listView_FAQ);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FAQActivity.this, FAQDescActivity.class);
                intent.putExtra("desc", desc.get(position));
                intent.putExtra("title", actions.get(position));
                startActivity(intent);
            }
        });

        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAQActivity.this.finish();
            }
        });

        getFAQMsg();
    }

    private void getFAQMsg() {
        showNormalDialog("正在加载数据，请稍后...");
        ContentQueryOptionDto queryOptionDto = new ContentQueryOptionDto();
        queryOptionDto.setCategoryId("90757ac9-2178-4fd9-a964-25e8f83a774a");
        queryOptionDto.setPageSize(50);
        AppActionImpl.getInstance(this).contentQuery(queryOptionDto,
                new ActionCallbackListener<PagedListEntityDto<ContentListDto>>() {
                    @Override
                    public void onSuccess(PagedListEntityDto<ContentListDto> data) {
                        dissMissNormalDialog();
                        if (data == null)
                            return;
                        List<ContentListDto> listDtos = data.getRows();
                        if (listDtos == null || listDtos.size() < 1)
                            return;
                        actions = new ArrayList<String>();
                        desc = new ArrayList<String>();
                        for (int i = 0; i < listDtos.size(); i++) {
                            ContentListDto dto = listDtos.get(i);
                            actions.add(dto.getContentName());
                            desc.add(dto.getDescription());
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                getApplicationContext(), R.layout.faq_item, R.id.common_problem_item, actions);
                        listView.setAdapter(arrayAdapter);
                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {
                        dissMissNormalDialog();
                        showToast(message);
                    }
                });
    }
}
