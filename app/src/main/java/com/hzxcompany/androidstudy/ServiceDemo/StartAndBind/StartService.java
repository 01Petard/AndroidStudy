package com.hzxcompany.androidstudy.ServiceDemo.StartAndBind;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class StartService extends Service {
    private static  final String TAG = "StartService";
    public StartService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"StartService--->onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"StartService--->onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"StartService--->onDestroy");
    }
}
