package ServiceDemo.StartAndBind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hzxcompany.androidstudy.R;

public class StartActivity extends AppCompatActivity {

    private static  final String TAG = "StartActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button btn1 = findViewById(R.id.button1);
        Button btn2 = findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"StartActivity--->服务已开启");
                Intent intent = new Intent(StartActivity.this,StartService.class);
                startService(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"StartActivity--->服务已断开");
                Intent intent = new Intent(StartActivity.this,StartService.class);
                stopService(intent);
            }
        });
    }
}
