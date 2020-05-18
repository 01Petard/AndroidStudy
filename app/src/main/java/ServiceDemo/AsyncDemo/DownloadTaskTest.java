package ServiceDemo.AsyncDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hzxcompany.androidstudy.R;

import ServiceDemo.ServiceGuanli;

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
