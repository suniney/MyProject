package com.example.yanxu.myproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanxu.myproject.Base.BaseActivity;
import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.utils.ToastUtils;
import com.example.yanxu.myproject.view.MyGridView;
import com.example.yanxu.myproject.view.TextProgressBar;
import com.example.yanxu.myproject.view.UISwitchButton;
import com.example.yanxu.myproject.view.XListView;

import java.util.ArrayList;


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




