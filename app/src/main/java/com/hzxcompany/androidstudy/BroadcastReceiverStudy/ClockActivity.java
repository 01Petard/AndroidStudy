package com.hzxcompany.androidstudy.BroadcastReceiverStudy;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hzxcompany.androidstudy.R;

import java.util.Calendar;

public class ClockActivity extends Activity {
    private Button mBtnSet, mBtnCancel;
    private TextView mTvInfo;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.colorBlack);
        mBtnSet = findViewById(R.id.btn_set);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mTvInfo = findViewById(R.id.tv_info);
        calendar = Calendar.getInstance();
        mBtnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取系统时间
                calendar.setTimeInMillis(System.currentTimeMillis());
                int mHour = calendar.get(Calendar.HOUR_OF_DAY);
                int mMinute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(ClockActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                calendar.setTimeInMillis(System.currentTimeMillis());
                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                calendar.set(Calendar.MINUTE, minute);
                                calendar.set(Calendar.SECOND, 0);
                                calendar.set(Calendar.MILLISECOND, 0);
                                // 建立Intent和PendingIntent来调用目标组件
                                Intent intent = new Intent("com.hzxcompany.androidstudy.WAKE_UP");
                                PendingIntent pendingIntent = PendingIntent.getBroadcast(ClockActivity.this, 0, intent, 0);
                                // 获取闹钟管理的实例
                                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                                // 设置闹钟
                                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                                String tmpS = "闹钟响铃时间：" + format(hourOfDay) + ":" + format(minute);
                                mTvInfo.setText(tmpS);
                            }
                        }, mHour, mMinute, true).show();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 建立Intent和PendingIntent来调用目标组件
                Intent intent = new Intent(ClockActivity.this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ClockActivity.this, 0, intent, 0);
                // 获取闹钟管理的实例
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
                mTvInfo.setText("当前无闹钟");
            }
        });
    }

    // 格式化字符串7:3-->07:03
    private String format(int x) {
        String s = "" + x;
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }

}
