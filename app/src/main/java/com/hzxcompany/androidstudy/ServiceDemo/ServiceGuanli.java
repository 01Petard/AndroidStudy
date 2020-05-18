package com.hzxcompany.androidstudy.ServiceDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hzxcompany.androidstudy.R;

import com.hzxcompany.androidstudy.ServiceDemo.AsyncDemo.DownloadTaskTest;
import com.hzxcompany.androidstudy.ServiceDemo.AsyncDemo.TestHandlerActivity;
import com.hzxcompany.androidstudy.ServiceDemo.FullDownload.DownloadActivity;
import com.hzxcompany.androidstudy.ServiceDemo.StartAndBind.BindActivity;
import com.hzxcompany.androidstudy.ServiceDemo.StartAndBind.StartActivity;

public class ServiceGuanli extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_guanli);
        Button btn1 = findViewById(R.id.btn_service_1);
        Button btn2 = findViewById(R.id.btn_service_2);
        Button btn3 = findViewById(R.id.btn_service_3);
        Button btn4 = findViewById(R.id.btn_service_4);
        Button btn5 = findViewById(R.id.btn_service_5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceGuanli.this, StartActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceGuanli.this, BindActivity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceGuanli.this, TestHandlerActivity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadTaskTest task = new DownloadTaskTest();
                task.execute();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceGuanli.this, DownloadActivity.class);
                startActivity(intent);
            }
        });
    }
}
