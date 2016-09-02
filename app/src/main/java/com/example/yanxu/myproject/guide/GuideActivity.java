package com.example.yanxu.myproject.guide;

/**
 * Created by yd on 2016/3/30.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.yanxu.myproject.MainActivity;
import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * 引导页面，第一次安装程序进入，其他情况直接进入Splash
 *
 * @author yanxu
 */

public class GuideActivity extends Activity {

    private ViewPager mViewPager;
    private List<View> views;
    private MyPagerAdapter myPagerAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.guide);
        context = GuideActivity.this;
        SharedPreferencesUtil.writeValueByKey(GuideActivity.this, "isFirstIn", true);
        SharedPreferencesUtil.writeValueByKey(GuideActivity.this, "isFirstLogin", false);
        initView();
    }

    /**
     * 进入首页
     */
    private void initLog() {
        Intent intent = new Intent(GuideActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 进入引导界面
     */
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.guide_viewPager);
        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.pager_layout1, null));
        views.add(inflater.inflate(R.layout.pager_layout2, null));
        views.add(inflater.inflate(R.layout.pager_layout3, null));
        views.add(inflater.inflate(R.layout.pager_layout4, null));
        myPagerAdapter = new MyPagerAdapter(this, views);

        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setOnPageChangeListener(new GuidePageChangeListener());
    }

    /**
     * @author Harry 页面改变监听事件
     */
    private class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        public void onPageScrollStateChanged(int arg0) {
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        /**
         * 页面有所改变，如果是当前页面，将小圆圈改为icon_carousel_02，其他页面则改为icon_carousel_01
         */
        public void onPageSelected(int arg0) {

        }
    }

    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViews;
        private Activity mContext;

        public MyPagerAdapter(Activity context, List<View> views) {
            this.mViews = views;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mViews.get(arg1));
        }

        /**
         * 实例化页卡，则获取它的button并且添加点击事件
         */
        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mViews.get(arg1), 0);
//            Button skip = (Button) arg0.findViewById(R.id.skip);
//            skip.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    initLog();
//                }
//            });
            if (arg1 == mViews.size() - 1) {
                Button enterBtn = (Button) arg0
                        .findViewById(R.id.guide_enter);

                enterBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 将isShow保存为true，并进入登录界面
//                        PreferenceUtil.setBoolean(mContext,
//                                PreferenceUtil.SHOW_GUIDE, true);
                        initLog();
                    }
                });
            }
            return mViews.get(arg1);
        }
    }

}
