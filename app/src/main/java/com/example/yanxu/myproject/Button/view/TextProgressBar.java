package com.example.yanxu.myproject.Button.view;

/**
 * Created by yanxu on 2016/9/2.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.yanxu.myproject.utils.CommonUtils;

/**
 * 带文字的自定义进度条
 * Created by yanxu on 2016/8/30.
 */
public  class TextProgressBar extends ProgressBar {
    String text;
    Paint mPaint;

    public TextProgressBar(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        initText();
    }

    public TextProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        initText();
    }


    public TextProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        initText();
    }

    @Override
    public synchronized void setProgress(int progress) {
        // TODO Auto-generated method stub
        setText(progress);
        super.setProgress(progress);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        //this.setText();
        Rect rect = new Rect();
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);
        int x = (getWidth() / 2) - rect.centerX();
        int y = (getHeight() / 2) - rect.centerY();
        canvas.drawText(this.text, x, y, this.mPaint);
    }

    //初始化，画笔
    private void initText() {
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.BLACK);
        this.mPaint.setTextSize(CommonUtils.dip2px(getContext(), 10));
    }

    private void setText() {
        setText(this.getProgress());
    }

    //设置文字内容
    private void setText(int progress) {
        int i = (progress * 100) / this.getMax();
        this.text = String.valueOf(i) + "%";
    }

    public void setText(String text) {
        this.text = text;
    }
}

