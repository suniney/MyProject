package com.example.yanxu.myproject.Base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.yanxu.myproject.R;

/**
 * Created by yanxu on 2016/8/29.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener {
    public Context context;
    private BaseLayout baseLayout;

    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        context = this;
    }

    public void setView(int resouceId) {
        baseLayout = new BaseLayout(context, 0, resouceId);
        setContentView(baseLayout);
        bindListeners();
    }

    public void setTitleView(int resouceId) {
        baseLayout = new BaseLayout(context, 1, resouceId);
        setContentView(baseLayout);
        bindListeners();
    }

    private void bindListeners() {
        baseLayout.bt_load_restart.setOnClickListener(this);
        baseLayout.ll_base_left.setOnClickListener(this);
        baseLayout.tv_base_right1.setOnClickListener(this);
    }

    /**
     * 开始加载的转圈效果
     */
    public void loadStart() {
        baseLayout.loadStart();
    }

    /**
     * 无网络加载失败的效果
     */
    public void loadNonet() {
        baseLayout.loadNonet();
    }

    /**
     * 加载的数据为空的效果
     */
    public void loadNoData() {
        baseLayout.loadNoData();
    }

    /**
     * 成功加载之后的效果
     */
    public void loadSuccess() {
        baseLayout.loadSuccess();
    }

    /**
     * 无数据的时候的提示
     */
    public void setNoDataPrompt(String prompt) {
        baseLayout.tv_load_no_data.setText(prompt);
    }

    /**
     * 设置标题的名字
     *
     * @param title
     */
    public void setBaseTitle(String title) {
        baseLayout.setBaseTitle(title);
    }

    /**
     * 设置右侧第一个图片的内容
     *
     * @param resouceId
     */
    public void setRightImage(int resouceId) {
        baseLayout.iv_base_right1.setBackgroundResource(resouceId);
        baseLayout.iv_base_right1.setVisibility(View.VISIBLE);
    }

    /**
     * 设置右侧文字的内容
     *
     * @param text
     */
    public void setRightText(String text) {
        baseLayout.tv_base_right1.setText(text);
        baseLayout.tv_base_right1.setVisibility(View.VISIBLE);
    }

    /**
     * 左侧按钮的点击事件
     */
    public void onLeftClick() {
        onBackPressed();
    }

    /**
     * 加载失败重新加载
     */
    public void onReloadClick() {

    }

    /**
     * 右侧文字的点击事件
     */
    public void onRightTextClick() {

    }

    /**
     * 右侧图片的点击事件
     */
    public void onRightImageClick() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_load_restart:
                onReloadClick();
                break;
            case R.id.ll_base_left:
                onLeftClick();
                break;
            case R.id.iv_base_right1:
                onRightImageClick();
                break;
            case R.id.tv_base_right1:
                onRightTextClick();
                break;
        }
    }
}
