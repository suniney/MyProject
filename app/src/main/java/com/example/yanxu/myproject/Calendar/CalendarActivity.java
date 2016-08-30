package com.example.yanxu.myproject.Calendar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.yanxu.myproject.Base.BaseActivity;
import com.example.yanxu.myproject.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CalendarActivity extends BaseActivity {
    private CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setView(R.layout.activity_main);
        setTitleView(R.layout.activity_calendar);
        setBaseTitle("标题");
        setRightText("保存");
        findView();
        listener();
    }

    private void findView() {
        calendar = (CalendarView) findViewById(R.id.calendar);
    }

    private void listener() {
        calendar.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {

            @Override
            public void onDateUnselected(Date date) {
            }

            @Override
            public void onDateSelected(Date date) {

            }
        });

        calendar.setOnMonthChangedListener(new CalendarView.OnMonthChangedListener() {
            @Override
            public void onChangedToPreMonth(Date dateOfMonth) {
                new GetCalendarsOfMonthTask(dateOfMonth).execute();
                Toast.makeText(getApplicationContext(), "当前月份" + (dateOfMonth.getMonth() + 1), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onChangedToNextMonth(Date dateOfMonth) {
                new GetCalendarsOfMonthTask(dateOfMonth).execute();
                Toast.makeText(getApplicationContext(), "当前月份" + (dateOfMonth.getMonth() + 1), Toast.LENGTH_LONG).show();
            }
        });
        new GetCalendarsOfMonthTask(Calendar.getInstance().getTime()).execute();
    }

    class GetCalendarsOfMonthTask extends AsyncTask<Object, Object, String> {
        Date dateOfMonth;
        List<List<Calendar>> calsList;

        public GetCalendarsOfMonthTask(Date dateOfMonth) {
            this.dateOfMonth = dateOfMonth;
        }

        @Override
        protected String doInBackground(Object... params) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateOfMonth);
            calsList = getCalendarsOfMonth(cal.get(Calendar.YEAR) + "",
                    (cal.get(Calendar.MONTH) + 1) + "");
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (calsList != null && calsList.size() > 1) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateOfMonth);
//添加标记
//				calendar.markDatesOfMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
//						false, true, calsList.get(0));
//				calendar.markDatesOfMonth(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
//						true, false, calsList.get(1));
            }
        }


    }

    private List<List<Calendar>> getCalendarsOfMonth(String year, String month) {

        List<List<Calendar>> data = new ArrayList<List<Calendar>>();

        int yearI = Integer.parseInt(year);
        int monthI = Integer.parseInt(month) - 1;

        List<Calendar> feeds = new ArrayList<Calendar>();
        for (int i = 0; i < 15; i++) {
            int day = new Random().nextInt(30) + 1;
            Calendar cal = Calendar.getInstance();
            cal.set(yearI, monthI, day, 0, 0, 0);
            feeds.add(cal);
        }
        data.add(feeds);

        List<Calendar> calendars = new ArrayList<Calendar>();
        for (int i = 0; i < 21; i++) {
            int day = new Random().nextInt(30) + 1;
            Calendar cal = Calendar.getInstance();
            cal.set(yearI, monthI, day, 0, 0, 0);
            calendars.add(cal);
        }
        data.add(calendars);

        return data;
    }

    @Override
    public void onRightTextClick() {
        super.onRightTextClick();
        Toast.makeText(context, "djksdj", Toast.LENGTH_SHORT).show();
    }
}
