package com.example.renhao.wevolunteer.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

/**
 * 专业选择界面
 */
public class ProfessionalSelectionActivity extends BaseActivity {
    private static final String TAG = "ProfessionalSelectionActivity";

    private List<String> actions;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_selection);
        listView = (ListView) findViewById(R.id.listView_professional_selection);

        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String[] s = new String[]{
                "法律援助",
                "助老助残",
                "文体活动",
                "儿童关怀",
                "科普宣传",
                "动物保护",
                "生态环保",
                "医疗保卫",
                "社区服务",
                "文化教育",
                "扶贫帮困",
                "安全生产",
                "支教助学",
                "邻里守望",
                "应急救援",
        };
        actions = Arrays.asList(s);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_multiple_choice,android.R.id.text1,actions);
        listView.setAdapter(arrayAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);//这里我们直接在代码中设置选择模式
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
