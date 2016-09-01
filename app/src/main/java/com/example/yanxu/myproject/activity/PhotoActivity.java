package com.example.yanxu.myproject.activity;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanxu.myproject.R;
import com.example.yanxu.myproject.utils.CommonUtils;
import com.example.yanxu.myproject.utils.ToastUtils;
import com.example.yanxu.myproject.utils.VoiceRecordUtils;
import com.example.yanxu.myproject.view.CustomDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PhotoActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_1,btn_2;
    Context context;
    private String picturePath;
    private Uri selectedImage;
    private ImageView user_pic;

    //录音相关
    private VoiceRecordUtils voiceRecordUtils;
    private Dialog luyinDialog;
    private String luyinFileName;
    private Handler timeHandler = new Handler();
    private TextView tv_yuyin_time;
    private int luyinTime = 0;//录音记录的时间
    private Runnable timeRunnable = new Runnable() {
        @Override
        public void run() {
            tv_yuyin_time.setText(CommonUtils.secondToHMS(luyinTime));
            timeHandler.postDelayed(this, 1000);
            luyinTime++;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        context = PhotoActivity.this;
        bindViews();
    }

    private void bindViews() {
        user_pic = (ImageView) findViewById(R.id.user_pic);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                ShowImagePicker();
                break;
            case R.id.btn_2:
                voiceRecordUtils = new VoiceRecordUtils();
                initLuyinDialog();
                luyinDialog.show();
                break;
            default:
                break;
        }
    }

    //选择图片
    private void ShowImagePicker() {
        new android.app.AlertDialog.Builder(context)
                .setTitle("选择图片方式")
                .setNegativeButton("相册", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                "image/*");
                        startActivityForResult(intent, 1);
                    }
                })
                .setPositiveButton("拍照", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();

                        String sdStatus = Environment.getExternalStorageState();
                        /* 检测sd是否可用 */
                        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
                            Toast.makeText(context, "SD卡不可用！",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent camera = new Intent(
                                "android.media.action.IMAGE_CAPTURE");
                        startActivityForResult(camera, 2);
                    }
                }).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = context.getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();
                upImage(picturePath);
                break;
            case 2:
                Toast.makeText(context, "拍摄成功", Toast.LENGTH_SHORT).show();
                Bitmap photo = null;
                Uri uri = data.getData();
                if (uri != null) {
                    photo = BitmapFactory.decodeFile(uri.getPath());
                }
                if (photo == null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        photo = (Bitmap) bundle.get("data");
                    } else {
                        Toast.makeText(context, "拍照失败",
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }
                FileOutputStream fileOutputStream = null;
                try {
                    // 获取 SD 卡根目录
                    String saveDir = Environment.getExternalStorageDirectory() + "/meitian_photos";
                    File dir = new File(saveDir);
                    if (!dir.exists()) dir.mkdir();
                    SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");
                    String filename = "MT" + (t.format(new Date())) + ".jpg";
                    File file = new File(saveDir, filename);
                    fileOutputStream = new FileOutputStream(file);
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    picturePath = file.getPath();
                    upImage(picturePath);
                } catch (Exception e) {
                }
                break;
        }
    }

    //显示图片
    private void upImage(String picturePath) {
        ToastUtils.shortgMsg(context, picturePath);
        user_pic.setBackground(new BitmapDrawable(CommonUtils.convertStringToIcon(picturePath)));
    }



    private void initLuyinDialog() {
        luyinDialog = new android.app.AlertDialog.Builder(context).create();
        luyinDialog.setCanceledOnTouchOutside(false);
        luyinDialog.show();
        Window window = luyinDialog.getWindow();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = wm.getDefaultDisplay().getWidth();
        WindowManager.LayoutParams params =
                luyinDialog.getWindow().getAttributes();
        params.width = screenWidth - CommonUtils.dip2px(context, 60);
        luyinDialog.getWindow().setAttributes(params);
        window.setContentView(R.layout.dialog_luyin);

        tv_yuyin_time = (TextView) window.findViewById(R.id.tv_yuyin_time);
        final ImageView iv_yuyin_play = (ImageView) window.findViewById(R.id.iv_yuyin_play);
        final TextView tv_luyin_state = (TextView) window.findViewById(R.id.tv_luyin_state);
        LinearLayout ll_luyin_operate = (LinearLayout) window.findViewById(R.id.ll_luyin_operate);
        final ImageView iv_luyin_point = (ImageView) window.findViewById(R.id.iv_luyin_point);
        final TextView tv_luyin_restart = (TextView) window.findViewById(R.id.tv_luyin_restart);
        final TextView tv_luyin_finish = (TextView) window.findViewById(R.id.tv_luyin_finish);
        tv_luyin_finish.setVisibility(View.INVISIBLE);
        tv_luyin_restart.setVisibility(View.INVISIBLE);
        iv_yuyin_play.setVisibility(View.INVISIBLE);
        iv_luyin_point.setBackgroundResource(R.drawable.luyin_start_point);
        tv_luyin_state.setText("开始录音");
        ll_luyin_operate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luyinState = tv_luyin_state.getText().toString();
                if ("开始录音".equals(luyinState)) {
                    long time = System.currentTimeMillis();
                    luyinFileName = time + ".mp3";
                    voiceRecordUtils.initvoiceRecord(luyinFileName);
                    luyinTime = 0;
                    timeHandler.removeCallbacks(timeRunnable);
                    timeHandler.post(timeRunnable);
                    voiceRecordUtils.startRecord();
                    tv_luyin_state.setText("暂停录音");
                    iv_luyin_point.setBackgroundResource(R.drawable.luyin_pause_point);
                    tv_luyin_restart.setVisibility(View.VISIBLE);
                    tv_luyin_finish.setVisibility(View.INVISIBLE);
                    iv_yuyin_play.setVisibility(View.INVISIBLE);
                } else if ("暂停录音".equals(luyinState)) {
                    timeHandler.removeCallbacks(timeRunnable);
                    voiceRecordUtils.pauseRecord();
                    tv_luyin_state.setText("继续录音");
                    iv_luyin_point.setBackgroundResource(R.drawable.luyin_continue_point);
                    tv_luyin_restart.setVisibility(View.VISIBLE);
                    tv_luyin_finish.setVisibility(View.VISIBLE);
                    iv_yuyin_play.setVisibility(View.INVISIBLE);
                } else {
                    timeHandler.removeCallbacks(timeRunnable);
                    timeHandler.post(timeRunnable);
                    voiceRecordUtils.continueRecord();
                    tv_luyin_state.setText("暂停录音");
                    iv_luyin_point.setBackgroundResource(R.drawable.luyin_pause_point);
                    tv_luyin_restart.setVisibility(View.VISIBLE);
                    tv_luyin_finish.setVisibility(View.INVISIBLE);
                    iv_yuyin_play.setVisibility(View.INVISIBLE);
                }
            }
        });
        tv_luyin_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceRecordUtils.endRecord();
                voiceRecordUtils.delete();
                luyinTime = 0;
                tv_yuyin_time.setText("00:00:00");
                timeHandler.removeCallbacks(timeRunnable);
                long time = System.currentTimeMillis();
                luyinFileName = time + ".mp3";
                voiceRecordUtils.initvoiceRecord(luyinFileName);
                tv_luyin_finish.setVisibility(View.INVISIBLE);
                tv_luyin_restart.setVisibility(View.INVISIBLE);
                iv_yuyin_play.setVisibility(View.INVISIBLE);
                iv_luyin_point.setBackgroundResource(R.drawable.luyin_start_point);
                tv_luyin_state.setText("开始录音");
            }
        });

        tv_luyin_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceRecordUtils.endRecord();
                luyinDialog.dismiss();
                ToastUtils.shortgMsg(context,voiceRecordUtils.getSaveAbsolutePath());
//                yinpinFileList.add(voiceRecordUtils.getSaveAbsolutePath());
//                addYinpinImageView(voiceRecordUtils.getSaveAbsolutePath());
            }
        });
        iv_yuyin_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                luyinTime = 0;
                timeHandler.removeCallbacks(timeRunnable);
                timeHandler.post(timeRunnable);
                voiceRecordUtils.playRecord();
            }
        });
        luyinDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                voiceRecordUtils.endRecord();
                /**
                 * 恢复原样
                 */
                luyinTime = 0;
                timeHandler.removeCallbacks(timeRunnable);
                tv_yuyin_time.setText("00:00:00");
                tv_luyin_finish.setVisibility(View.INVISIBLE);
                tv_luyin_restart.setVisibility(View.INVISIBLE);
                iv_yuyin_play.setVisibility(View.INVISIBLE);
                iv_luyin_point.setBackgroundResource(R.drawable.luyin_start_point);
                tv_luyin_state.setText("开始录音");
            }
        });
        luyinDialog.dismiss();

    }
}





