package com.hzxcompany.androidstudy.ServiceDemo.StartAndBind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BindService extends Service {
    private static  final String TAG = "BindService";
    public BindService() {
    }

    class MyBind extends Binder{//在这个内部类里应该存了大量的方法，用来实现各种后台的方法，比如子线程...
        BindService getservice(){
            return BindService.this;
        }
        public void printInfo2(){
            Log.i(TAG,"MyBind--->我是MyBind的方法");
        }
    }
    public void printInfo1(){
        Log.i(TAG,"BindService--->我是BindService的方法");
    }
    MyBind myBind = new MyBind();//开启绑定类型的服务，目标是为了返回服务里定义的对象，然后使用这个类里面的方法，这便是Service存在的意义！
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"BindService--->onCreate");
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"BindService--->onBind");
        return myBind;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"BindService--->onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"BindService--->onDestroy");
    }
}
