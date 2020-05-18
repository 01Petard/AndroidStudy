package ServiceDemo.FullDownload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContentResolverCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hzxcompany.androidstudy.MainActivity;
import com.hzxcompany.androidstudy.R;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener{
    private DowloadService.DownloadBind downloadBind;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBind = (DowloadService.DownloadBind) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        Button btn1 = findViewById(R.id.btn_download_1);
        Button btn2 = findViewById(R.id.btn_download_2);
        Button btn3 = findViewById(R.id.btn_download_3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        Intent intent = new Intent(this,DowloadService.class);
        startService(intent);
        bindService(intent,conn,BIND_AUTO_CREATE);
        if(ContextCompat.checkSelfPermission(DownloadActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(DownloadActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }


    @Override
    public void onClick(View v) {
        if(downloadBind == null){
            return;
        }
        switch (v.getId()){
            case R.id.btn_download_1:
//                String url = "http://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inset-win64.exe";
//                String url = "https://dldir1.qq.com/qqfile/QQIntl/QQi_PC/QQIntl2.11.exe";
                String url = "https://down.qq.com/qqweb/QQlite/Android_apk/qqlite_4.0.1.1060_537064364.apk";
//                String url = "https://www.baidu.com/s?wd=QQ%E5%A5%BD%E5%8F%8B&rsv_idx=2&tn=78000241_11_hao_pg&usm=3&ie=utf-8&rsv_cq=&rsv_dl=0_right_recommends_merge_21102&euri=0361252b50d348ceab585b12e0fca61f";
                downloadBind.startDownload(url);

                break;
            case R.id.btn_download_2:
                downloadBind.pauseDownload();
                break;
            case R.id.btn_download_3:
                downloadBind.cancelDownload();
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length> 0&& grantResults[0]!= PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
