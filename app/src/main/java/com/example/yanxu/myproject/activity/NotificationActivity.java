package com.example.yanxu.myproject.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yanxu.myproject.Base.BaseActivity;
import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.utils.CommonUtils;
import com.example.yanxu.myproject.utils.ToastUtils;
import com.example.yanxu.myproject.view.CustomDialog;

public class NotificationActivity extends BaseActivity {
    private Button btn_1, btn_2, btn_3, btn_4, btn_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setView(R.layout.activity_main);
        setTitleView(R.layout.activity_toast);
        setBaseTitle("标题");
        bindViews();
    }

    private void bindViews() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_1:
                Notification.Builder builder = new Notification.Builder(context);
                builder.setSmallIcon(R.drawable.duigou);
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.addgroupfriend));
                builder.setAutoCancel(true);
                builder.setContentTitle("普通通知");
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0);
                builder.setContentText("shfddfhdf").setFullScreenIntent(pendingIntent, true);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
                break;
            case R.id.btn_2:

                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
            case R.id.btn_5:
                break;
            default:
                break;
        }
    }

    @Override
    public void onRightTextClick() {
        super.onRightTextClick();
    }
}
