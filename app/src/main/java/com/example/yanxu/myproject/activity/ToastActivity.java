package com.example.yanxu.myproject.activity;


import android.content.Context;
import android.content.DialogInterface;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.utils.CommonUtils;
import com.example.yanxu.myproject.utils.ToastUtils;
import com.example.yanxu.myproject.view.CustomDialog;


public class ToastActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_1, btn_2, btn_3, btn_4, btn_5;
    Context context;
    private Snackbar mSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        context = ToastActivity.this;
//        setView(R.layout.activity_main);
//        setTitleView(R.layout.activity_toast);
//        setBaseTitle("标题");
        bindViews();
    }

    private void bindViews() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                final CustomDialog customDialog = new CustomDialog(context, "sdsa", "左面", "右面");
                customDialog.setClicklistener(new CustomDialog.ClickListenerInterface() {
                    @Override
                    public void doConfirm() {
                        ToastUtils.imgMsg(context, "是", true);
                    }

                    @Override
                    public void doCancel() {
                        customDialog.dismiss();
                    }
                });
                customDialog.show();
                break;
            case R.id.btn_2:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Title")
                        .setMessage("Dialog content.")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface d,
                                                        int which) {
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface d,
                                                        int which) {
                                    }
                                })
                        .show();
                break;
            case R.id.btn_3:
                Snackbar.make(v, "data deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
                break;
            case R.id.btn_4:
                mSnackBar = Snackbar.make(v, "sjds", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                Resources resource = (Resources) getBaseContext().getResources();
                ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.material_blue_grey_800);
                mSnackBar.setActionTextColor(csl);
                mSnackBar.setCallback(new Snackbar.Callback() {
                    @Override
                    public void onDismissed(Snackbar snackbar, int event) {
                        super.onDismissed(snackbar, event);
                        Toast.makeText(context, "SnackBar Dismiss", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onShown(Snackbar snackbar) {
                        super.onShown(snackbar);
                        Toast.makeText(context, "SnackBar Show", Toast.LENGTH_SHORT).show();
                    }
                });
                mSnackBar.show();
                break;
            case R.id.btn_5:

//                CommonUtils.showDialog(context, "信息", "左面", "右面", new CommonUtils.DialogButtonOnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ToastUtils.customMsg(context, "左面", 1);
//                    }
//                }, null);
                CommonUtils.showDialog(context, " 标题", "信息", "左面", "右面", new CommonUtils.DialogButtonOnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.customMsg(context, "左面", 1);
                    }
                }, null);
                break;
            default:
                break;
        }
    }
}

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_1:
//                final CustomDialog customDialog = new CustomDialog(context, "sdsa", "左面", "右面");
//                customDialog.setClicklistener(new CustomDialog.ClickListenerInterface() {
//                    @Override
//                    public void doConfirm() {
//                        ToastUtils.imgMsg(context, "是", true);
//                    }
//
//                    @Override
//                    public void doCancel() {
//                        customDialog.dismiss();
//                    }
//                });
//                customDialog.show();
//                break;
//            case R.id.btn_2:
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                builder.setTitle("Title")
//                        .setMessage("Dialog content.")
//                        .setPositiveButton("OK",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface d,
//                                                        int which) {
//                                    }
//                                })
//                        .setNegativeButton("Cancel",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface d,
//                                                        int which) {
//                                    }
//                                })
//                        .show();
//                break;
//            case R.id.btn_3:
//                ToastUtils.imgMsg(context, "dhfsak", false);
//                break;
//            case R.id.btn_4:
//                CommonUtils.showDialog(context, "信息", "左面", "右面", new CommonUtils.DialogButtonOnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ToastUtils.customMsg(context, "左面", 1);
//                    }
//                }, null);
//                break;
//            case R.id.btn_5:
//                CommonUtils.showDialog(context, " 标题", "信息", "左面", "右面", new CommonUtils.DialogButtonOnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ToastUtils.customMsg(context, "左面", 1);
//                    }
//                }, null);
//                break;
//            default:
//                break;
//        }
//    }



