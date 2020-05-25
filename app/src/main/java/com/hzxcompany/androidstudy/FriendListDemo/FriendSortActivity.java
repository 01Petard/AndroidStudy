package com.hzxcompany.androidstudy.FriendListDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.hzxcompany.androidstudy.R;

public class FriendSortActivity extends AppCompatActivity {

    private static final String TAG = "FriendSortActivity";
    static int TYPE_SORT = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_sort);
        RadioGroup rg = findViewById(R.id.rg_friend_sort);
        Button btn = findViewById(R.id.btn_friend_sort);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                radioButton.setChecked(true);
                switch (checkedId){
                    case R.id.rb_sort_id:
                        TYPE_SORT=1;
                        break;
                    case R.id.rb_sort_name:
                        TYPE_SORT=2;
                        break;
                    case R.id.rb_sort_phone:
                        TYPE_SORT=3;
                        break;
                    case R.id.rb_sort_sex:
                        TYPE_SORT=4;
                        break;
                    default:
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FriendSortActivity.this,FriendListTest.class);
                SharedPreferences.Editor editor = getSharedPreferences("mysp",MODE_PRIVATE).edit();
                editor.putInt("type",TYPE_SORT);
                editor.apply();
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}
