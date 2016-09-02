package com.example.yanxu.myproject.wheel;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.wheel.time.view.TimePickerView;
import com.example.yanxu.myproject.utils.TimeTools;
import com.example.yanxu.myproject.wheel.wheel.adpter.ArrayWheelAdapter;
import com.example.yanxu.myproject.wheel.wheel.listener.OnWheelChangedListener;
import com.example.yanxu.myproject.wheel.wheel.view.AddressActivity;
import com.example.yanxu.myproject.wheel.wheel.view.WheelView;

public class AddAddressActivity extends AddressActivity implements OnWheelChangedListener {
    WheelView mViewProvince;
    WheelView mViewCity;
    WheelView mViewDistrict;
    private Context mContext;
    private TextView address,mtime;
    private Button btn_ok;
    private TimePickerView tpv_start_work;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        mContext = AddAddressActivity.this;
        findView();
        listener();
        setUpData();
    }

    private void findView() {
        mViewProvince = (WheelView) findViewById(R.id.mViewProvince);
        mViewCity = (WheelView) findViewById(R.id.mViewCity);
        mViewDistrict = (WheelView) findViewById(R.id.mViewDistrict);
        address= (TextView) findViewById(R.id.address);
        mtime= (TextView) findViewById(R.id.time);
        btn_ok= (Button) findViewById(R.id.btn_ok);
        tpv_start_work = (TimePickerView) findViewById(R.id.tpv_start_work);
    }

    private void listener() {
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address.setText(mCurrentProviceName + mCurrentCityName + mCurrentDistrictName);
            }
        });
        tpv_start_work.setOnTimeChangeListener(new TimePickerView.OnTimeChangeListener() {

            @Override
            public void getTime(String t) {
                String time = TimeTools.parseTime(TimeTools.createTime(t));
                String curTime = TimeTools.getCurTime();
                if (time != null) {
                    mtime.setText(time);
                } else {

                }

            }
        });
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mDistrictCode = mDistrictMap.get(mCurrentDistrictName);
        }
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        mProviceCode = mProviceMap.get(mCurrentProviceName);
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);
        if (areas == null) {
            areas = new String[]{""};
        }
        mCurrentDistrictName = areas[0];
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);


    }
}
