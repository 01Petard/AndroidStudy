package com.hzxcompany.androidstudy.FriendListDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.hzxcompany.androidstudy.R;

import java.util.ArrayList;

public class FriendListTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list_test);
        FriendBiz biz = new FriendBiz();
        ArrayList<Friend> friends = biz.getAllFriends();
        //实例化listview
        ListView lv = findViewById(R.id.lvfriend);
        //创建adapter对象
        FriendAdapter adapter = new FriendAdapter(this, friends);
        //设置adapter
        lv.setAdapter(adapter);
    }
}
