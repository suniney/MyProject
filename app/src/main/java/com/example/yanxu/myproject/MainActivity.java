package com.example.yanxu.myproject;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yanxu.myproject.Base.BaseActivity;
import com.example.yanxu.myproject.Calendar.CalendarActivity;
import com.example.yanxu.myproject.wheel.AddAddressActivity;

public class MainActivity extends BaseActivity {
    private Button btn_1,btn_2,btn_3,btn_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setView(R.layout.activity_main);
        setTitleView(R.layout.activity_main);
        setBaseTitle("标题");
//        setRightText("保存");
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
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
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
                Intent intent3= new Intent(MainActivity.this, MoreListActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_4:
                Intent intent4= new Intent(MainActivity.this, XListActivity.class);
                startActivity(intent4);
                break;
        }
    }

    @Override
    public void onRightTextClick() {
        super.onRightTextClick();
    }
}
