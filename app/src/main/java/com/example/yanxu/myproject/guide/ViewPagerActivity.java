package com.example.yanxu.myproject.guide;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.guide.animation.RotateDownPageTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanxu on 2016/8/30.
 */

public class ViewPagerActivity extends Activity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {
    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private List<View> views;
    // 引导图片资源
    private static final int[] pics = {R.drawable.baymax01, R.drawable.baymax02,
            R.drawable.baymax03, R.drawable.baymax07};
    // 底部小点图片
    private ImageView[] dots;
    // 记录当前选中位置
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        views = new ArrayList<View>();
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        // 初始化引导图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            //setBackGroundResource而不是setImageResource
            iv.setBackgroundResource(pics[i]);
            views.add(iv);
        }
        vp = (ViewPager) findViewById(R.id.viewPager);
        // 初始化Adapter
        vpAdapter = new ViewPagerAdapter(views);
        vp.setAdapter(vpAdapter);
        // 绑定回调
        vp.setOnPageChangeListener(this);
        vp.setPageTransformer(true, new RotateDownPageTransformer());
//        vp.setPageTransformer(true, new ZoomOutPageTransformer());
//        vp.setPageTransformer(false, new ViewPager.PageTransformer() {
//
//            @Override
//            public void transformPage(View page, float position) {
//                final float normalizedposition = Math.abs(Math.abs(position) - 1);
//                page.setScaleX(normalizedposition / 2 + 0.5f);
//                page.setScaleY(normalizedposition / 2 + 0.5f);
//            }
//        });
        // 初始化底部小点
        initDots();
    }

    private void initDots() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayout);
        dots = new ImageView[pics.length];
        // 循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            dots[i] = (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(true);// 都设为灰色
            dots[i].setOnClickListener(this);
            dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
    }

    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        vp.setCurrentItem(position);
    }

    private void setCurDot(int positon) {
        if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
            return;
        }
        dots[positon].setEnabled(false);
        dots[currentIndex].setEnabled(true);
        currentIndex = positon;
    }

    // 当滑动状态改变时调用
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub
    }

    // 当当前页面被滑动时调用
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub
    }

    // 当新的页面被选中时调用
    public void onPageSelected(int arg0) {
        setCurDot(arg0);
    }

    public void onClick(View v) {
        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    class ViewPagerAdapter extends PagerAdapter {
        // 界面列表
        private List<View> views;

        public ViewPagerAdapter(List<View> views) {
            this.views = views;
        }

        // 销毁arg1位置的界面
        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(views.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub
        }

        // 获得当前界面数
        @Override
        public int getCount() {
            if (views != null) {
                return views.size();
            }
            return 0;
        }

        // 初始化arg1位置的界面
        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(views.get(arg1), 0);
            return views.get(arg1);
        }

        // 判断是否由对象生成界面
        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return (arg0 == arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub
        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub
        }
    }

}
