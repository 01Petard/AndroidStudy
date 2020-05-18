package com.hzxcompany.androidstudy.ServiceDemo.FullDownload;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.hzxcompany.androidstudy.R;

import java.io.File;

public class DowloadService extends Service {

    private DownloadTask downloadTask;

    private String downloadUrl;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("下载中……",progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("下载成功",-1));
            Toast.makeText(DowloadService.this, "下载成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("下载失败",-1));
            Toast.makeText(DowloadService.this, "下载失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DowloadService.this, "暂停", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DowloadService.this, "取消", Toast.LENGTH_SHORT).show();
        }
    };

    private DownloadBind mBinder = new DownloadBind();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class DownloadBind extends Binder{
        public void startDownload(String url){
            if(downloadTask == null){
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                startForeground(1,getNotification("下载……",0));
                Toast.makeText(DowloadService.this, "下载中……", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload(){
            if(downloadTask != null){
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload(){
            if(downloadTask != null){
                downloadTask.cancelDowload();
            }
            if(downloadUrl != null){
                String fileNmae = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                File file = new File(directory+fileNmae);
                if(file.exists()){
                    file.delete();
                }
                getNotificationManager().cancel(1);
                stopForeground(true);
                Toast.makeText(DowloadService.this, "取消", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private NotificationManager getNotificationManager(){
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
    private Notification getNotification(String title,int progress){
        Intent intent = new Intent(DowloadService.this,DownloadActivity.class);
        PendingIntent pi = PendingIntent.getActivities(this,0, new Intent[]{intent},0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if(progress >= 0){
            builder.setContentText(progress+"%");
            builder.setProgress(100,progress,false);
        }
        return builder.build();
    }


}
