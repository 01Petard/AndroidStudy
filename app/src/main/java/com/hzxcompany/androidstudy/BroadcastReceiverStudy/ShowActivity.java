package com.hzxcompany.androidstudy.BroadcastReceiverStudy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.hzxcompany.androidstudy.R;

public class ShowActivity extends Activity {
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        StatusBarUtils.setWindowStatusBarColor(this,R.color.colorBlack);
        mediaPlayer = MediaPlayer.create(this, R.raw.rightfully);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
        AlertDialog alertDialog = new AlertDialog.Builder(ShowActivity.this)
                .setTitle("闹钟响了")
                .setIcon(R.drawable.clock)
                .setMessage("赶快起床！")
                .setPositiveButton("关掉它", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mediaPlayer.stop();
                        ShowActivity.this.finish();
                    }
                })
                .setCancelable(true)
                .show();
    }

}
