package com.example.renhao.wevolunteer.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.model.volunteer.VolunteerViewDto;
import com.example.renhao.wevolunteer.R;
import com.example.renhao.wevolunteer.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务时间界面
 */
public class ApplyServiceTimeActivity extends BaseActivity {
    private static final String TAG = "ServiceTimeActivity";

    private ListView listView;
    private String time[][];
    private String TIME;
    private List<String> time_list;
    private String time_submit;
    private TextView submit;
    private ServiceTimeAdapter serviceTimeActivity;
    private VolunteerViewDto personal_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_time);
        listView = (ListView) findViewById(R.id.listView_service_time);

        //初始化二维数组
        time = new String[7][3];
        time_list = new ArrayList<>();

        Intent intent = getIntent();
        personal_data = (VolunteerViewDto) intent.getSerializableExtra("personal_data");
        TIME = personal_data.getServiceTimeIntention();
        //给二维数组赋初值
        SetTime();

        serviceTimeActivity = new ServiceTimeAdapter(this);
        listView.setAdapter(serviceTimeActivity);


        submit = (TextView) findViewById(R.id.tv_serviceTime_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (time[i][j] != null)
                            time_list.add(time[i][j]);
                    }
                }
                for (int i = 0; i < time_list.size(); i++) {
                    if (i == 0)
                        time_submit = time_list.get(i);
                    else
                        time_submit = time_submit + "," + time_list.get(i);
                }
                personal_data.setServiceTimeIntention(time_submit);
                Intent result = new Intent();
                result.putExtra("personal_data", personal_data);
                setResult(RESULT_OK, result);
                ApplyServiceTimeActivity.this.finish();

            }
        });


        //回退按钮
        ImageView btn_back = (ImageView) findViewById(R.id.imageView_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyServiceTimeActivity.this.finish();
            }
        });
    }

    private void SetTime() {
        try {
            if (TIME != null) {
                for (int i = 0; i < TIME.length(); i = i + 5) {
                    int line = Integer.parseInt(TIME.substring(i + 1, i + 2)) - 1;
                    int row = Integer.parseInt(TIME.substring(i + 3, i + 4)) - 1;
                    time[line][row] = TIME.substring(i, i + 4);
                }
            }
        } catch (Exception e) {

        }
    }


    //重写为绑定position的checkBox的专用监听器
    private class BoxChangeListener implements CompoundButton.OnCheckedChangeListener {
        int mPosition;
        String num;
        int which_box;

        public BoxChangeListener(int inPosition, int judge, String Num) {
            mPosition = inPosition;
            num = Num;
            which_box = judge;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (mPosition) {
                case 0:
                    if (isChecked)
                        time[mPosition][which_box] = "0" + (mPosition + 1) + num;
                    else {
                        if (time[mPosition][which_box] != null)
                            time[mPosition][which_box] = null;
                    }
                    break;
                case 1:
                    if (isChecked)
                        time[mPosition][which_box] = "0" + (mPosition + 1) + num;
                    else {
                        if (time[mPosition][which_box] != null)
                            time[mPosition][which_box] = null;
                    }
                    break;
                case 2:
                    if (isChecked)
                        time[mPosition][which_box] = "0" + (mPosition + 1) + num;
                    else {
                        if (time[mPosition][which_box] != null)
                            time[mPosition][which_box] = null;
                    }
                    break;
                case 3:
                    if (isChecked)
                        time[mPosition][which_box] = "0" + (mPosition + 1) + num;
                    else {
                        if (time[mPosition][which_box] != null)
                            time[mPosition][which_box] = null;
                    }
                    break;
                case 4:
                    if (isChecked)
                        time[mPosition][which_box] = "0" + (mPosition + 1) + num;
                    else {
                        if (time[mPosition][which_box] != null)
                            time[mPosition][which_box] = null;
                    }
                    break;
                case 5:
                    if (isChecked)
                        time[mPosition][which_box] = "0" + (mPosition + 1) + num;
                    else {
                        if (time[mPosition][which_box] != null)
                            time[mPosition][which_box] = null;
                    }
                    break;
                case 6:
                    if (isChecked)
                        time[mPosition][which_box] = "0" + (mPosition + 1) + num;
                    else {
                        if (time[mPosition][which_box] != null)
                            time[mPosition][which_box] = null;
                    }
                    break;

            }
        }
    }

    //重写listview的适配器，并在其中利用position绑定checkBox的监听器
    private class ServiceTimeAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public ServiceTimeAdapter(Context context) {
            this.mInflater = LayoutInflater.from(context);

        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public final class ViewHolder {
            public TextView tv_week;
            public CheckBox checkBox1;
            public CheckBox checkBox2;
            public CheckBox checkBox3;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.service_time_item, null);//根据布局产生一个view
                holder = new ViewHolder();
                holder.tv_week = (TextView) convertView.findViewById(R.id.tv_week);
                holder.checkBox1 = (CheckBox) convertView.findViewById(R.id.box_time_1);
                holder.checkBox2 = (CheckBox) convertView.findViewById(R.id.box_time_2);
                holder.checkBox3 = (CheckBox) convertView.findViewById(R.id.box_time_3);
                //初始化界面
                if (TIME != null) {
                    for (int i = 0; i < TIME.length(); i = i + 5) {
                        int line = Integer.parseInt(TIME.substring(i + 1, i + 2)) - 1;
                        int row = Integer.parseInt(TIME.substring(i + 3, i + 4));
                        if (line == position) {
                            switch (row) {
                                case 1:
                                    holder.checkBox1.setChecked(true);
                                    continue;
                                case 2:
                                    holder.checkBox2.setChecked(true);
                                    continue;
                                case 3:
                                    holder.checkBox3.setChecked(true);
                                    break;
                            }
                        }
                    }
                }
                convertView.setTag(holder);//绑定ViewHolder对象
            } else {
                holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
            }
            final String[] s = new String[]{
                    "周一",
                    "周二",
                    "周三",
                    "周四",
                    "周五",
                    "周六",
                    "周日"
            };
            holder.tv_week.setText(s[position]);


            holder.checkBox1.setOnCheckedChangeListener(new BoxChangeListener(position, 0, "01"));
            holder.checkBox2.setOnCheckedChangeListener(new BoxChangeListener(position, 1, "02"));
            holder.checkBox3.setOnCheckedChangeListener(new BoxChangeListener(position, 2, "03"));

            return convertView;
        }


    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


}
