package com.example.yanxu.myproject.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.yanxu.myproject.R;

/**
 * Created by yanxu on 2016/8/30.
 */
public class CommonUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 创建通用的dialog 无标题
     * @param context
     * @param message 提示
     * @param leftText 左侧的button显示的
     * @param rightText 右侧不同显示的
     * @param leftOnClick 左侧的点击事件
     * @param righOnClick 右侧的点击事件
     * @return
     */
    public static void showDialog(Context context, String message, String leftText, String rightText, final DialogButtonOnClickListener leftOnClick, final DialogButtonOnClickListener righOnClick){
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        WindowManager.LayoutParams params =
                alertDialog.getWindow().getAttributes();
        params.width = screenWidth-CommonUtils.dip2px(context, 60);
        alertDialog.getWindow().setAttributes(params);
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.base_dialog);
        TextView tv_message = (TextView) window.findViewById(R.id.tv_base_dialog_message);
        TextView tv_left = (TextView) window.findViewById(R.id.tv_base_dialog_left);
        TextView tv_right = (TextView) window.findViewById(R.id.tv_base_dialog_right);
        TextView tv_title=(TextView)window.findViewById(R.id.tv_base_dialog_title);
        tv_title.setVisibility(View.GONE);
        tv_message.setText(message);
        tv_left.setText(leftText);
        tv_right.setText(rightText);
        tv_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                if(leftOnClick!=null){
                    leftOnClick.onClick(v);
                }
            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                if(righOnClick!=null){
                    righOnClick.onClick(v);
                }
            }
        });

    }
    /**
     * 创建通用的dialog 有标题
     * @param context
     * @param message 提示
     * @param leftText 左侧的button显示的
     * @param rightText 右侧不同显示的
     * @param leftOnClick 左侧的点击事件
     * @param righOnClick 右侧的点击事件
     * @param title 标题
     * @return
     */
    public static void showDialog(Context context,String title,String message,String leftText,String rightText,final DialogButtonOnClickListener leftOnClick,final DialogButtonOnClickListener righOnClick){
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        WindowManager.LayoutParams params =
                alertDialog.getWindow().getAttributes();
        params.width = screenWidth-CommonUtils.dip2px(context, 60);
        alertDialog.getWindow().setAttributes(params);
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.base_dialog);
        TextView tv_message = (TextView) window.findViewById(R.id.tv_base_dialog_message);
        TextView tv_left = (TextView) window.findViewById(R.id.tv_base_dialog_left);
        TextView tv_right = (TextView) window.findViewById(R.id.tv_base_dialog_right);
        TextView tv_title = (TextView) window.findViewById(R.id.tv_base_dialog_title);
        tv_title.setText(title);
        tv_message.setText(message);
        tv_left.setText(leftText);
        tv_right.setText(rightText);
        tv_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                if(leftOnClick!=null){
                    leftOnClick.onClick(v);
                }
            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                if(righOnClick!=null){
                    righOnClick.onClick(v);
                }
            }
        });

    }

    public interface DialogButtonOnClickListener {
        public void onClick(View v) ;
    }
}
