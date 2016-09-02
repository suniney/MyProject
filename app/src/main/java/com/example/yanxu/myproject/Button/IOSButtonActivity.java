package com.example.yanxu.myproject.Button;


import android.os.Bundle;

import android.widget.CompoundButton;


import com.example.yanxu.myproject.Base.BaseActivity;
import com.example.yanxu.myproject.Button.view.TextProgressBar;
import com.example.yanxu.myproject.Button.view.UISwitchButton;
import com.example.yanxu.myproject.R;

import com.example.yanxu.myproject.utils.ToastUtils;


/**
 * iosButton
 *
 * @author yanxu
 */
public class IOSButtonActivity extends BaseActivity {

    UISwitchButton push_btn;
    public TextProgressBar pb;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setTitleView(R.layout.activity_ios_button);
        bindViews();
        bindListeners();
    }

    private void bindViews() {
        push_btn = (UISwitchButton) findViewById(R.id.push_btn);
        pb = (TextProgressBar) findViewById(R.id.pb);
    }

    private void bindListeners() {
        push_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ToastUtils.shortgMsg(context, "开启");
                } else {
                    ToastUtils.shortgMsg(context, "关闭");
                }
            }
        });
        pb.setMax(100);
        pb.setProgress(5);
        pb.setSecondaryProgress(20);
        pb.setProgressDrawable(getResources().getDrawable(R.drawable.progressbar_color));
//        pb.setText("实际");
    }



}




