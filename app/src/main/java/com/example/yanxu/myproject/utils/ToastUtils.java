package com.example.yanxu.myproject.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanxu.myproject.R;


/**
 * tostutils
 *
 * @author yanxu
 *         <p/>
 *         2016年4月21日上午10:01:37
 */
public class ToastUtils {
    private static Toast toast;

    /**
     * 长时间显示
     *
     * @param context
     * @param msg
     */
    public static void longMsg(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 短时间显示
     *
     * @param context
     * @param msg
     */
    public static void shortgMsg(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 自定义时间显示
     *
     * @param context
     * @param msg
     * @param time
     */
    public static void customMsg(Context context, String msg, int time) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, time);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 中心提示信息
     *
     * @param context
     * @param msg
     */
    public static void centerMsg(Context context, String msg) {
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 带图标的toast(仿IOS图标的)
     *
     * @param context
     * @param msg         消息，可以为null
     * @param trueOrFalse true是显示对勾 false 显示查查
     */
    public static void imgMsg(Context context, String msg, boolean trueOrFalse) {
        Toast toast;
        if (msg == null) {
            msg = "";
        }
        View layout = View.inflate(context, R.layout.toast_custom, null);
        ImageView image = (ImageView) layout.findViewById(R.id.iv_toast);
        TextView title = (TextView) layout.findViewById(R.id.tv_toast);
        if (msg.equals("")) {
            title.setVisibility(View.GONE);
        } else {
            title.setText(msg);
        }
        if (trueOrFalse) {
            image.setImageResource(R.drawable.duigou);
        } else {
            image.setImageResource(R.drawable.chacha);
        }
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
