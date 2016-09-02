package com.example.yanxu.myproject.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;

import com.example.yanxu.myproject.MainActivity;
import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.guide.GuideActivity;
import com.example.yanxu.myproject.utils.SharedPreferencesUtil;


/**
 * Created by yanxu 2016/4/1.
 */

public class SplashActivity extends Activity {


    RelativeLayout layout_splash;
    private Handler handler;
    private Context context;
    private boolean isFirstIn = false;
    private boolean isFirstLogin = false;
    public static final int GO_HOME = 1000;                 //主页状态
    public static final int GO_GUIDE = 1001;                //引导页状态
    public static final long SPLASH_DELAY_MILLIS = 1000;    //延迟2秒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        initView();
    }

    public void initView() {
        context = SplashActivity.this;
        layout_splash= (RelativeLayout) findViewById(R.id.layout_splash);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case GO_HOME:
                        goHome();
                        break;
                    case GO_GUIDE:
                        goGuide();
                        break;
                }
                super.handleMessage(msg);
            }
        };
        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(2000);
        layout_splash.startAnimation(aa);
        aa.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                isFirstRun();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });

    }

    /**
     * 判断当前版本与服务器版本是否一致
     *
     * @return true 一致 false 不一致进行下载
     */
    private void isFirstRun() {
        // 取得相应的值，如果没有该值，说明还未写入，用false作为默认值
        isFirstIn = SharedPreferencesUtil.getValueByKey(context, "isFirstIn", false);
        // 判断程序第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (isFirstIn) {
            handler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
        } else {
            handler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
        }

    }

    /**
     * 进入首页
     */
    private void goHome() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
//        isFirstLogin = SharedPreferencesUtil.getValueByKey(context, "isFirstLogin", false);
//
//        if (isFirstLogin) {
//            Intent intent = new Intent(SplashActivity.this, .class);
//            startActivity(intent);
//            finish();
//        } else {
//            MainActivity_.intent(this).start();
//            finish();
//        }

    }

    /**
     * 进入欢迎页
     */
    private void goGuide() {
        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

}
