package com.example.yanxu.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yanxu.myproject.Base.BaseActivity;
import com.example.yanxu.myproject.Calendar.CalendarActivity;
import com.example.yanxu.myproject.Button.IOSButtonActivity;
import com.example.yanxu.myproject.list.MoreListActivity;
import com.example.yanxu.myproject.linkmanList.NameListActivity;
import com.example.yanxu.myproject.toast_notifiction.NotificationActivity;
import com.example.yanxu.myproject.photo_voice.PhotoActivity;
import com.example.yanxu.myproject.toast_notifiction.ToastActivity;
import com.example.yanxu.myproject.guide.ViewPagerActivity;
import com.example.yanxu.myproject.guide.ViewPagerActivity1;
import com.example.yanxu.myproject.list.XListActivity;
import com.example.yanxu.myproject.utils.UpdateManager;
import com.example.yanxu.myproject.wheel.AddAddressActivity;

public class MainActivity extends BaseActivity {
    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_10, btn_11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setView(R.layout.activity_main);
        setTitleView(R.layout.activity_main);
        setBaseTitle("标题");
//        setRightText("保存");
        bindViews();
        getData();
        UpdateManager update = new UpdateManager(this);
        update.checkUpdate();
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
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_6.setOnClickListener(this);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_7.setOnClickListener(this);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_8.setOnClickListener(this);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_9.setOnClickListener(this);
        btn_10 = (Button) findViewById(R.id.btn_10);
        btn_10.setOnClickListener(this);
        btn_11 = (Button) findViewById(R.id.btn_11);
        btn_11.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_1:
                Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_2:
                Intent intent2 = new Intent(MainActivity.this, AddAddressActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_3:
                Intent intent3 = new Intent(MainActivity.this, MoreListActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_4:
                Intent intent4 = new Intent(MainActivity.this, XListActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_5:
                Intent intent5 = new Intent(MainActivity.this, IOSButtonActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_6:
                Intent intent6 = new Intent(MainActivity.this, ToastActivity.class);
                startActivity(intent6);
                break;
            case R.id.btn_7:
                Intent intent7 = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent7);
                break;
            case R.id.btn_8:
                Intent intent8 = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent8);
                break;
            case R.id.btn_9:
                Intent intent9 = new Intent(MainActivity.this, ViewPagerActivity1.class);
                startActivity(intent9);
                break;
            case R.id.btn_10:
                Intent intent10 = new Intent(MainActivity.this, NameListActivity.class);
                startActivity(intent10);
                break;
            case R.id.btn_11:
                Intent intent11 = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(intent11);
                break;
            default:
                break;
        }
    }

    @Override
    public void onRightTextClick() {
        super.onRightTextClick();
    }

    private void getData() {
//        Map<String, String> map = new LinkedHashMap<String, String>();
//        map.put("project_group_id", "60");
//        map.put("year", "2016");
//        map.put("month", "8");
//        MyOkHttpUtils.okhttpPost(context, "http://192.168.1.251/dhyt/api", map, new CommonCallback() {
//            @Override
//            public void onResponse(Common response, int id) {
//                boolean isSucess = response.isSuccess();
//                String s1 = String.valueOf(response.getErrors().get(0));
//                if (isSucess) {
//                } else {
//                    String a = String.valueOf(response.getErrors().get(0));
//                    Toast.makeText(context, a.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        MyOkHttpUtils.okhttpGet(context, "http://192.168.1.251/dhyt/api/personalManagement/60", new CommonCallback() {
//            @Override
//            public void onResponse(Common response, int id) {
//                String s1 = String.valueOf(response.getErrcode());
//            }
//        });
    }
}
