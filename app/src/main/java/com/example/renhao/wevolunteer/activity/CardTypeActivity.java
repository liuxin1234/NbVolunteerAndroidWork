package com.example.renhao.wevolunteer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.core.AppActionImpl;
import com.example.model.ActionCallbackListener;
import com.example.model.dictionary.DictionaryListDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class CardTypeActivity extends BaseActivity implements View.OnClickListener {

    ListView listView;
    ImageView imageView;
    TextView textView;
    private List<String> card_type;
    private List<String> cardCode;
    private String type_text;
    private String typeCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_type);
        initView();
        initmListener();
        getCardType();

    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView_card_type);
        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.tv_submit);
    }

    private void initmListener() {
        imageView.setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    //证件类型
    private void getCardType() {
        AppActionImpl.getInstance(this).dictionaryQueryDefault("CardType", "",
                new ActionCallbackListener<List<DictionaryListDto>>() {
                    @Override
                    public void onSuccess(List<DictionaryListDto> data) {
                        cardCode = new ArrayList<String>();
                        card_type = new ArrayList<String>();
                        for (int i = 0; i < data.size(); i++) {
                            card_type.add(data.get(i).getName());
                            cardCode.add(data.get(i).getCode());
                        }
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                CardTypeActivity.this, android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, card_type);
                        listView.setAdapter(arrayAdapter);
                        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//这里我们直接在源代码中设置选择模式
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                type_text = ((TextView) view).getText().toString();
                                typeCode = cardCode.get(position);
                                Logger.v("--", typeCode);
                            }
                        });

                    }

                    @Override
                    public void onFailure(String errorEvent, String message) {

                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                finish();
                break;
            case R.id.tv_submit:
                String text = type_text;
                if (!TextUtils.isEmpty(text)) {
                    Intent intent = new Intent();
                    intent.setClass(CardTypeActivity.this, RegisterActivity.class);
                    intent.putExtra("type_text", text);
                    intent.putExtra("typeCode", typeCode);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(CardTypeActivity.this, "请选择证件类型", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
