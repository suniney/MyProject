package com.example.yanxu.myproject.wheel.time.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.utils.TimeTools;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间选择器
 * time
 * data
 * time_and_data
 *
 * @author yanxu
 */
public class TimePickerView extends LinearLayout {

    private View conentView;
    private Context context;
    private LayoutInflater inflater;
    private LinearLayout ll_timepicker;
    private JournalPickerView jp_year;
    private JournalPickerView jp_month;
    private JournalPickerView jp_day;
    private JournalPickerView jp_hour;
    private JournalPickerView jp_minute;
    private TextView tv_time;
    private TextView tv_year;
    private TextView tv_month;
    private TextView tv_day;


    //没有年   只有时分秒    只有年月日   全有
    private static final int TIME = 1;
    private static final int DATA = 2;
    private static final int DATA_AND_TIME = 3;

    private List<String> years = new ArrayList<String>();
    private List<String> months = new ArrayList<String>();
    private List<String> days = new ArrayList<String>();
    private List<String> hours = new ArrayList<String>();
    private List<String> minutes = new ArrayList<String>();
    private String month = "1";
    private String day;
    private String hour;
    private String minute;
    private String year;
    String time = 1 + "";
    private int type;
    private OnTimeChangeListener onTimeChangeListener;

    public TimePickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TimePicker);
        type = a.getInteger(R.styleable.TimePicker_type, DATA_AND_TIME);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.time_picker, this);
        getCurTime();//获取当前时间，给年月日赋值
        bindView();//帮顶视图
        initDataPicker();//给picker创建数据
        initTimeData();//把创建好的数据给picker赋值
        initDataPickerSelecter();//判断滚动时的情况
        setPickerType(type);//设置picker的类型
        a.recycle();
    }

    public void setPickerType(int type) {
        this.type = type;
        switch (type) {
            case TIME:
                jp_year.setVisibility(View.GONE);
                jp_month.setVisibility(View.GONE);
                jp_day.setVisibility(View.GONE);
                tv_year.setVisibility(View.GONE);
                tv_month.setVisibility(View.GONE);
                tv_day.setVisibility(View.GONE);
                break;
            case DATA:
                jp_hour.setVisibility(View.GONE);
                jp_minute.setVisibility(View.GONE);
                tv_time.setVisibility(View.GONE);
                break;
            case DATA_AND_TIME:
                break;
        }
    }

    public void setOnTimeChangeListener(OnTimeChangeListener onTimeChangeListener) {
        this.onTimeChangeListener = onTimeChangeListener;
    }

    public interface OnTimeChangeListener {
        void getTime(String time);
    }

    private void initTimeData() {
        // TODO Auto-generated method stub
        jp_year.setData(years);
        jp_day.setData(days);
        jp_month.setData(months);
        jp_hour.setData(hours);
        jp_minute.setData(minutes);
        for (int i = 0; i < years.size(); i++) {
            if (year.equals(years.get(i))) {
                jp_year.setSelected(i);
            }
        }
        for (int i = 0; i < months.size(); i++) {
            if (month.equals(months.get(i))) {
                jp_month.setSelected(i);
            }
        }
        for (int i = 0; i < days.size(); i++) {
            if (day.equals(days.get(i))) {
                jp_day.setSelected(i);
            }
        }
        for (int i = 0; i < hours.size(); i++) {
            if (hour.equals(hours.get(i))) {
                jp_hour.setSelected(i);
            }
        }
        for (int i = 0; i < minutes.size(); i++) {
            if (minute.equals(minutes.get(i))) {
                jp_minute.setSelected(i);
            }
        }
    }

    private void getCurTime() {
        // TODO Auto-generated method stub
        String curTime = TimeTools.getCurTime();
        time = curTime;
        year = curTime.substring(0, 4);
        month = curTime.substring(5, 7);
        day = curTime.substring(8, 10);
        hour = curTime.substring(12, 14);
        minute = curTime.substring(15, 17);
        Log.i("curTime", curTime);
        Log.i("hour", hour);
        Log.i("minute", minute);
    }

    public String getTime() {
        time = year + "年" + month + "月" + day + "日 " + hour + ":" + minute + ":" + "00";
        return time;
    }

    public String getParseTime() {
        time = TimeTools.createTime(getTime());
        return time;
    }

    private void initDataPickerSelecter() {
        // TODO Auto-generated method stub
        jp_year.setOnSelectListener(new JournalPickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                // TODO Auto-generated method stub
                year = text;
                checkYear();
                setNewTime();
            }
        });
        jp_month.setOnSelectListener(new JournalPickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                // TODO Auto-generated method stub
                month = text;
                checkYear();
                setNewTime();
            }
        });
        jp_day.setOnSelectListener(new JournalPickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                // TODO Auto-generated method stub
                day = text;
                setNewTime();
            }
        });
        jp_hour.setOnSelectListener(new JournalPickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                // TODO Auto-generated method stub
                hour = text;
                setNewTime();
            }
        });
        jp_minute.setOnSelectListener(new JournalPickerView.onSelectListener() {

            @Override
            public void onSelect(String text) {
                // TODO Auto-generated method stub
                minute = text;
                setNewTime();
            }
        });
    }

    //判断年月并给日起设置
    protected void checkYear() {
        // TODO Auto-generated method stub
        int newYear = Integer.parseInt(year);
        int oldMonth = Integer.parseInt(month);
        int oldDay = Integer.parseInt(day);
        if (oldMonth == 2) {
            addDay();
            jp_day.setData(days);
            if (oldDay == Integer.parseInt("29")) {
                Log.i("29的月份", day + "--" + days.size());
                if (days.size() == 29) {
                    jp_day.setSelected(28);
                    day = 29 + "";
                } else {
                    jp_day.setSelected(27);
                    day = 28 + "";
                }
            } else if (oldDay < Integer.parseInt("29")) {
                for (int i = 0; i < days.size(); i++) {
                    if (oldDay == Integer.parseInt(days.get(i))) {
                        jp_day.setSelected(i);
                        if (oldDay < 10) {
                            day = 0 + "" + oldDay;
                        } else {
                            day = oldDay + "";
                        }
                    }
                }
                Log.i("28的月份", day);
            }
        } else {
            addDay();
            jp_day.setData(days);
            if (oldDay == Integer.parseInt("31")) {
                Log.i("29的月份", day + "--" + days.size());
                if (days.size() == 31) {
                    jp_day.setSelected(30);
                    day = 31 + "";
                } else {
                    jp_day.setSelected(29);
                    day = 30 + "";
                }
            } else if (oldDay < Integer.parseInt("31")) {
                for (int i = 0; i < days.size(); i++) {
                    if (oldDay == Integer.parseInt(days.get(i))) {
                        jp_day.setSelected(i);
                        if (oldDay < 10) {
                            day = 0 + "" + oldDay;
                        } else {
                            day = oldDay + "";
                        }
                    }
                }
                Log.i("28的月份", day);
            }
        }
    }

    //设置新的时间
    protected void setNewTime() {
        // TODO Auto-generated method stub
        if (type == DATA) {
            time = year + "年" + month + "月" + day + "日 " + 00 + ":" + 00 + ":" + "00";
        } else if (type == TIME) {
            time = year + "年" + month + "月" + day + "日 " + hour + ":" + minute + ":" + "00";
        } else {
            time = year + "年" + month + "月" + day + "日 " + hour + ":" + minute + ":" + "00";
        }
        onTimeChangeListener.getTime(time);
    }

    private void initDataPicker() {
        // TODO Auto-generated method stub
        addYear();
        addMonth();
        addDay();
        addHour();
        addMinute();
    }

    private void addDay() {
        // TODO Auto-generated method stub
        int curMonth = Integer.parseInt(month);

        if (curMonth == 2) {
            days.clear();
            if ((Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 != 0) || Integer.parseInt(year) % 400 == 0) {
                for (int i = 1; i < 30; i++) {
                    if (i < 10) {
                        days.add(0 + "" + i);
                    } else {
                        days.add("" + i);
                    }
                }
            } else {
                for (int i = 1; i < 29; i++) {
                    if (i < 10) {
                        days.add(0 + "" + i);
                    } else {
                        days.add("" + i);
                    }
                }
            }
        } else if (curMonth == 1 || curMonth == 3 || curMonth == 5 || curMonth == 7
                || curMonth == 8 || curMonth == 10 || curMonth == 12) {
            days.clear();
            for (int i = 1; i < 32; i++) {
                if (i < 10) {
                    days.add(0 + "" + i);
                } else {
                    days.add("" + i);
                }
            }
        } else if (curMonth == 4 || curMonth == 6 || curMonth == 9
                || curMonth == 11) {
            days.clear();
            for (int i = 1; i < 31; i++) {
                if (i < 10) {
                    days.add(0 + "" + i);
                } else {
                    days.add("" + i);
                }
            }
        }
    }

    private void addMonth() {
        // TODO Auto-generated method stub
        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                months.add(0 + "" + i);
            } else {
                months.add("" + i);
            }
        }
    }

    private void addMinute() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                minutes.add("0" + i);
            } else {
                minutes.add(i + "");
            }
        }
    }

    private void addHour() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                hours.add("0" + i);
            } else {
                hours.add(i + "");
            }
        }
    }

    private void addYear() {
        for (int i = 2000; i < 2050; i++) {
            years.add(i + "");
        }
    }

    private void bindView() {
        // TODO Auto-generated method stub
        ll_timepicker = (LinearLayout) findViewById(R.id.ll_timepicker);
        jp_year = (JournalPickerView) findViewById(R.id.jp_year);
        jp_month = (JournalPickerView) findViewById(R.id.jp_month);
        jp_day = (JournalPickerView) findViewById(R.id.jp_day);
        jp_hour = (JournalPickerView) findViewById(R.id.jp_hour);
        jp_minute = (JournalPickerView) findViewById(R.id.jp_minute);

        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_day = (TextView) findViewById(R.id.tv_day);
        tv_year = (TextView) findViewById(R.id.tv_year);
    }
}
