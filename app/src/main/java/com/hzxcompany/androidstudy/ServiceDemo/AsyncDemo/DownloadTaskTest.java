package com.hzxcompany.androidstudy.ServiceDemo.AsyncDemo;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadTaskTest extends AsyncTask<Void,Integer,Boolean> {
    private static final String TAG = "DownloadTask";


    @Override
    protected void onPreExecute() {
        Log.i(TAG,"我是onPreExecute方法");
//        onProgressUpdate();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Log.i(TAG,"我是doInBackground方法");
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
//        dialog.setMessage("正在下载"+values[0]+"%");
        Log.i(TAG,"我是onProgressUpdate方法");
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
//        dialog.dismiss();
        Log.i(TAG,"我是onPostExecute方法");

    }
}
