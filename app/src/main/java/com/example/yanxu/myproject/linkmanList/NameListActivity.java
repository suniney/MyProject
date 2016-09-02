package com.example.yanxu.myproject.linkmanList;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yanxu.myproject.Base.BaseActivity;
import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.linkmanList.bean.PersonBean;
import com.example.yanxu.myproject.linkmanList.view.SideBar;
import com.example.yanxu.myproject.utils.PinyinComparator;
import com.example.yanxu.myproject.utils.PinyinUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by yanxu on 2016/8/30.
 */
public class NameListActivity extends BaseActivity {
    private ListView listView;
    private SortAdapter sortadapter;
    private List<PersonBean> data;
    private SideBar sidebar;
    private TextView dialog;
    String[] personName= {"张三","王五","案子","案刘","张三","王五","案子","案刘","张三","王五","案子","案刘","张三","王五","案子","案刘","张三","王五","案子","案刘","张三","王五","案子","案刘"};

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setTitleView(R.layout.activity_name_list);
        bindViews();
    }

    private void bindViews() {
        sidebar = (SideBar) findViewById(R.id.sidebar);
        listView = (ListView) findViewById(R.id.listview);
        dialog = (TextView) findViewById(R.id.dialog);
        sidebar.setTextView(dialog);
        // 设置字母导航触摸监听
        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // TODO Auto-generated method stub
                // 该字母首次出现的位置
                int position = sortadapter.getPositionForSelection(s.charAt(0));

                if (position != -1) {
                    listView.setSelection(position);
                }
            }
        });
        data = getData(personName);
        // 数据在放在adapter之前需要排序
        Collections.sort(data, new PinyinComparator());
        sortadapter = new SortAdapter(this, data);
        listView.setAdapter(sortadapter);
    }
    private List<PersonBean> getData(String[] data) {
        List<PersonBean> listarray = new ArrayList<PersonBean>();
        for (int i = 0; i < data.length; i++) {
            String pinyin = PinyinUtils.getPingYin(data[i]);
            String Fpinyin = pinyin.substring(0, 1).toUpperCase();

            PersonBean person = new PersonBean();
            person.setName(data[i]);
            person.setPinYin(pinyin);
            // 正则表达式，判断首字母是否是英文字母
            if (Fpinyin.matches("[A-Z]")) {
                person.setFirstPinYin(Fpinyin);
            } else {
                person.setFirstPinYin("#");
            }

            listarray.add(person);
        }
        return listarray;

    }
}
