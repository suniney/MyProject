package com.example.yanxu.myproject.list;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yanxu.myproject.Base.BaseActivity;
import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.list.view.XListView;

import java.util.ArrayList;


/**
 * xlistview列表
 *
 * @author yanxu
 */
public class XListActivity extends BaseActivity implements XListView.IXListViewListener{
    private XListView xlv_department;
    private DepartmentAdapter departmentAdapter;
    ArrayList data = new ArrayList();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setTitleView(R.layout.activity_xlist);
        bindViews();
    }

    private void bindViews() {
        xlv_department = (XListView) findViewById(R.id.xlv_department);

        xlv_department.setPullLoadEnable(true);
        xlv_department.setPullRefreshEnable(true);
        xlv_department.setXListViewListener(this);
        for (int i = 0; i < 5; i++) {
            data.add(i);
        }
        departmentAdapter = new DepartmentAdapter();
        xlv_department.setAdapter(departmentAdapter);
    }

    @Override
    public void onRefresh() {
        data.clear();
        for (int i = 10; i < 15; i++) {
            data.add(i);
        }
        xlv_department.stopRefresh();
        departmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        for (int i = 5; i < 10; i++) {
            data.add(i);
        }

        xlv_department.stopLoadMore();
        departmentAdapter.notifyDataSetChanged();
    }


    class DepartmentAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data.size();
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
                convertView = View.inflate(context, R.layout.item_list, null);
                holder.user_pic = (ImageView) convertView.findViewById(R.id.user_pic);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText(data.get(position).toString());
            holder.user_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            return convertView;
        }
    }

    static class ViewHolder {
        public ImageView user_pic;
        public TextView name;
    }
}
