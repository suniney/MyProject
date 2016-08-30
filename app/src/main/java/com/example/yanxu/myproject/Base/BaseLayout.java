package com.example.yanxu.myproject.Base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yanxu.myproject.R;

/**
 * Created by yanxu on 2016/8/29.
 */
public class BaseLayout extends RelativeLayout {
    private int type;//0 无标题 1 有标题
    private ViewGroup content;//用户自己的内容 (包括标题)
    private View base_load;//通用加载的效果的内容
    private View base_title;//通用的title
    private LayoutInflater inflater;
    private Context context;
    /***
     * base 标题部分的控件
     */
    public ImageView iv_base_title_left;
    public TextView tv_base_title;
    public LinearLayout ll_base_left;
    public TextView tv_base_right1;
    public ImageView iv_base_right1;
    /***
     * base 加载数据部分
     */
    private ProgressBar pb_load;
    public TextView tv_load_no_data;
    private LinearLayout ll_load_err;
    public Button bt_load_restart;

    public BaseLayout(Context context, int type, int resouceId) {
        super(context);
        this.context = context;
        this.type = type;
        inflater = LayoutInflater.from(context);
        content = (ViewGroup) inflater.inflate(resouceId, null);
        init();
        view(type);
    }

    private void view(int type) {
        if (type == 0) {
            LayoutParams fillParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            LinearLayout.LayoutParams llFillParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            content.addView(base_load, 0, llFillParams);
            this.addView(content, fillParams);
        } else if (type == 1) {
            LayoutParams baseTitleParams=new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT);
            this.addView(base_title,baseTitleParams);
            LayoutParams baseLoadParams=new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
            baseLoadParams.addRule(RelativeLayout.BELOW,R.id.ll_base_title);
            addView(base_load,baseLoadParams);
            LayoutParams personalContentParams=new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
            personalContentParams.addRule(RelativeLayout.BELOW,R.id.rl_base_load);
            this.addView(content,personalContentParams);

        }

    }

    private void init() {
        base_title = inflater.inflate(R.layout.base_title, null);
        tv_base_title = (TextView) base_title.findViewById(R.id.tv_base_title);
        ll_base_left = (LinearLayout) base_title.findViewById(R.id.ll_base_left);
        tv_base_right1 = (TextView) base_title.findViewById(R.id.tv_base_right1);
        iv_base_right1 = (ImageView) base_title.findViewById(R.id.iv_base_right1);

        base_load = inflater.inflate(R.layout.base_load, null);
        pb_load = (ProgressBar) base_load.findViewById(R.id.pb_load);
        tv_load_no_data = (TextView) base_load.findViewById(R.id.tv_load_no_data);
        ll_load_err = (LinearLayout) base_load.findViewById(R.id.ll_load_err);
        base_load.setVisibility(View.GONE);
        bt_load_restart = (Button) base_load.findViewById(R.id.bt_load_restart);

    }

    public void setBaseTitle(String title) {
        tv_base_title.setText(title);
    }

    public void loadStart() {
        base_load.setVisibility(View.VISIBLE);
        pb_load.setVisibility(View.VISIBLE);
        tv_load_no_data.setVisibility(View.GONE);
        ll_load_err.setVisibility(View.GONE);

    }

    /**
     * 加载无网络
     */
    public void loadNonet() {
        base_load.setVisibility(View.VISIBLE);
        pb_load.setVisibility(View.GONE);
        tv_load_no_data.setVisibility(View.GONE);
        ll_load_err.setVisibility(View.VISIBLE);
    }

    public void loadNoData() {
        base_load.setVisibility(View.VISIBLE);
        pb_load.setVisibility(View.GONE);
        tv_load_no_data.setVisibility(View.VISIBLE);
        ll_load_err.setVisibility(View.GONE);
    }

    public void loadSuccess() {
        base_load.setVisibility(View.GONE);
    }
}
