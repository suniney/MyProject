package com.example.yanxu.myproject.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.yanxu.myproject.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by yanxu on 2016/8/30.
 */
public class CommonUtils {
    //秒转成时分秒
    public static String secondToHMS(int second){
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second%3600;
        if(second>3600){
            h= second/3600;
            if(temp!=0){
                if(temp>60){
                    d = temp/60;
                    if(temp%60!=0){
                        s = temp%60;
                    }
                }else{
                    s = temp;
                }
            }
        }else{
            d = second/60;
            if(second%60!=0){
                s = second%60;
            }
        }
        String hourStr=h>9?h+"":"0"+h;
        String minuteStr=d>9?d+"":"0"+d;
        String secondStr=s>9?s+"":"0"+s;
        return hourStr+":"+minuteStr+":"+secondStr;
    }
    /**
     * 图片转成string
     *
     * @param bitmap
     * @return
     */
    public static String convertIconToString(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] appicon = baos.toByteArray();// 转为byte数组
        return Base64.encodeToString(appicon, Base64.DEFAULT);

    }

    /**
     * string转成bitmap
     *
     * @param st
     */
    public static Bitmap convertStringToIcon(String st)
    {
        // OutputStream out;
        Bitmap bitmap = null;
        try
        {
            // out = new FileOutputStream("/sdcard/aa.jpg");
            byte[] bitmapArray;
            bitmapArray = Base64.decode(st, Base64.DEFAULT);
            bitmap =
                    BitmapFactory.decodeByteArray(bitmapArray, 0,
                            bitmapArray.length);
            // bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            return bitmap;
        }
        catch (Exception e)
        {
            return null;
        }
    }
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
