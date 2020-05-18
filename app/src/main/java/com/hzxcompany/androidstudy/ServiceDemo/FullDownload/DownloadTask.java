package com.hzxcompany.androidstudy.ServiceDemo.FullDownload;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask extends AsyncTask<String,Integer,Integer> {

    private static final int TYPE_SUCCESS = 0;
    private static final int TYPE_FAILED = 1;
    private static final int TYPE_PAUSED = 2;
    private static final int TYPE_CANCLED = 3;

    private DownloadListener listener;

    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProress;
    public DownloadTask(DownloadListener listener){
        this.listener = listener;
    }
    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try{
            long downloadLength = 0;
            String downloadUrl = params[0];
            String fileNmae = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            Log.i("DownloadTask",fileNmae);
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            Log.i("DownloadTask",directory);
            file = new File(directory+fileNmae);
            if(file.exists()){
                downloadLength = file.length();
            }
            Log.i("DownloadTask","文件是否存在："+String.valueOf(file.exists()));
            long contntlength = getContnetLength(downloadUrl);
            Log.i("DownloadTask","contntlength="+String.valueOf(contntlength));
            Log.i("DownloadTask","getContnetLength(downloadUrl)="+String.valueOf(getContnetLength(downloadUrl)));
            if(contntlength == 0){
                Log.i("DownloadTask","failed");
                return TYPE_FAILED;
            }else if(contntlength == getContnetLength(downloadUrl)){
                Log.i("DownloadTask","success");
                return TYPE_SUCCESS;
            }
            Log.i("DownloadTask","contntlength："+String.valueOf(contntlength)+"\t"+"getContnetLength(downloadUrl)："+String.valueOf(getContnetLength(downloadUrl)));
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("RANGE","bytes="+downloadLength+"-")
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if(response != null){
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file,"rw");
                savedFile.seek(downloadLength);
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len=is.read())!=-1){
                    if(isCanceled){
                        return TYPE_CANCLED;
                    }else if(isPaused){
                        return TYPE_PAUSED;
                    }else{
                        total +=len;
                        savedFile.write(b,0,len);
                        int progerss = (int) ((total+downloadLength)/contntlength);
                        publishProgress(progerss);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(is != null){
                    is.close();
                }
                if(savedFile != null){
                    savedFile.close();
                }
                if(isCanceled && file !=null){
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if(progress > lastProress){
            listener.onProgress(progress);
            lastProress = progress;
        }
    }


    @Override
    protected void onPostExecute(Integer status) {
        switch (status){
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCLED:
                listener.onCanceled();
                break;
            default:
                break;
        }
    }


    public void pauseDownload(){
        isPaused = true;
    }

    public void cancelDowload(){
        isCanceled = true;
    }

    private long getContnetLength(String downloadUrl) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if(response!=null && response.isSuccessful()){
            long contentLength = response.body().contentLength();
            response.body().close();
            return contentLength;
        }
        return 0;
    }
}
