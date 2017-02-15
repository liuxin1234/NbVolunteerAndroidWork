package com.example.renhao.wevolunteer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导界面
 */
public class GuideActivity extends Activity implements ViewPager.OnClickListener, ViewPager.OnPageChangeListener {


    private ViewPager vp;
    private ArrayList<View> views = new ArrayList<View>();
    private LinearLayout ll_point;
    private ImageView[] imageViews;
    private int currentIndex; //当前正在显示的卡页
    private Button bt_start;
    private int[] imageIdArray;//图片资源的数组
    private List<View> viewList;//图片资源的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initviews();
        initpoint();
    }


    public void initView() {
        vp = (ViewPager) findViewById(R.id.id_vp);
        ll_point = (LinearLayout) findViewById(R.id.id_LL_point);
        bt_start = (Button) findViewById(R.id.bt_start);
    }

    //设置小圆点的改变
    private void setCurrentPoint(int position) {
        if (currentIndex < 0 || currentIndex == position || currentIndex > imageViews.length - 1) {
            return;
        }
        imageViews[currentIndex].setImageResource(R.drawable.vp_false);
        imageViews[position].setImageResource(R.drawable.vp_true);
        currentIndex = position;
        if (position == 2) {
            bt_start.setVisibility(View.VISIBLE);
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    ViewPropertyAnimator.animate(bt_start).translationY(0);
                    bt_start.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(GuideActivity.this, IndexActivity.class));
                            GuideActivity.this.finish();
                        }
                    });
                }
            }, 200);

        } else {
            bt_start.setVisibility(View.GONE);
        }
    }

    //设置圆点图片的数量
    private void initpoint() {
        imageViews = new ImageView[3];
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i] = (ImageView) ll_point.getChildAt(i);
        }
        currentIndex = 0;
        imageViews[currentIndex].setImageResource(R.drawable.vp_true);
    }

    private void initviews() {
        //实例化图片资源
        imageIdArray = new int[]{R.mipmap.bg_page1, R.mipmap.bg_page2, R.mipmap.bg_page3};
        viewList = new ArrayList<>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0; i < len; i++) {
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }

        //View集合初始化好后，设置Adapter
        vp.setAdapter(new MyViewPagerAdapter(viewList));
        //设置滑动监听
        vp.setOnPageChangeListener(this);

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentPoint(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class MyViewPagerAdapter extends PagerAdapter {

        private List<View> viewList;

        public MyViewPagerAdapter(List<View> viewList) {
            this.viewList = viewList;
        }

        /**
         * @return 返回页面的个数
         */
        @Override
        public int getCount() {
            if (viewList != null) {
                return viewList.size();
            }
            return 0;
        }

        /**
         * 判断对象是否生成界面
         *
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /**
         * 初始化position位置的界面
         *
         * @param container
         * @param position
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    }
}
