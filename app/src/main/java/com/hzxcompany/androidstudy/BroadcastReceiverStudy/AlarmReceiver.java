package com.hzxcompany.androidstudy.BroadcastReceiverStudy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "时间到了！", Toast.LENGTH_SHORT).show();
        intent = new Intent(context,ShowActivity.class);
        context.startActivity(intent);
    }
}
