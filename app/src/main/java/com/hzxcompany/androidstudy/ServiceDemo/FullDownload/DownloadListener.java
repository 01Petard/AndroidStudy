package com.hzxcompany.androidstudy.ServiceDemo.FullDownload;

public interface DownloadListener {

    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();







}