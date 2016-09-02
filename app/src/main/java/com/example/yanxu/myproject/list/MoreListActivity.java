package com.example.yanxu.myproject.list;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanxu.myproject.Base.BaseActivity;
import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.Base.view.MyGridView;

import java.util.ArrayList;


/**
 * 仿微信添加减少人员
 *
 * @author yanxu
 */
public class MoreListActivity extends BaseActivity {
    private MyGridView gview;
    private DepartmentListAdapter departmentListAdapter;
    ArrayList data = new ArrayList();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setTitleView(R.layout.activity_morelist);
        bindViews();
        bindListeners();
    }

    private void bindViews() {
        gview = (MyGridView) findViewById(R.id.gview);
        for (int i = 0; i < 5; i++) {
            data.add(i);
        }
        departmentListAdapter = new DepartmentListAdapter();
        gview.setAdapter(departmentListAdapter);
    }

    private void bindListeners() {

    }


    class DepartmentListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data == null ? 1 : data.size() + 2;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(getApplicationContext(), R.layout.item_list, null);
                holder.user_pic = (ImageView) convertView.findViewById(R.id.user_pic);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position == data.size() + 1) {
                holder.user_pic.setBackground(getResources().getDrawable(R.drawable.addgroupfriend));
                holder.user_pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                return convertView;
            } else if (position == data.size()) {
                holder.user_pic.setBackground(getResources().getDrawable(R.drawable.delete));
                holder.user_pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                return convertView;
            } else {
                holder.name.setText(data.get(position).toString());
                holder.user_pic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                return convertView;
            }


        }
    }

    static class ViewHolder {
        public ImageView user_pic;
        public TextView name;
    }
}
