package ServiceDemo.StartAndBind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hzxcompany.androidstudy.R;

public class BindActivity extends AppCompatActivity {
    private static final String TAG = "BindActivity";
    private static Boolean isConnected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind);
        Button btn11 = findViewById(R.id.button11);
        Button btn22 = findViewById(R.id.button22);
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BindActivity.this,BindService.class);
                bindService(intent,conn,BIND_AUTO_CREATE);
            }
        });
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected)//只有在服务开启的时候才能断开服务，不然是没有关闭的必要的
                {
                    try{
                        unbindService(conn);
                        Log.i(TAG, "BindActivity--->服务绑定已断开");
                    }catch (Exception e){
                        Log.i(TAG, "BindActivity--->服务未开启或已断开！请不要重复操作！");
                    }

                }
            }
        });
    }

    //ServiceConnection是一个时刻在监控service的一个接口，在这里你最好在服务开启/关闭改变的时候相应改变自定义的服务状态！
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG,"BindActivity--->服务绑定已开启");
            BindService.MyBind myBind = (BindService.MyBind) service;
            BindService bindService = myBind.getservice();//获得BindService的对象
            myBind.printInfo2();
            bindService.printInfo1();
            //bindservice服务一被创建，就会做里面的onCreate、onBind一些基本的方法，然后才能被人为地去做其他方法
            isConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnected = false;

        }
    };
}
