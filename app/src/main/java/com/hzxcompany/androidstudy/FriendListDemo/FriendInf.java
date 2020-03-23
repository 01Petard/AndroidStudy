package com.hzxcompany.androidstudy.FriendListDemo;

import java.util.ArrayList;

public class FriendInf {
    private ArrayList<Friend> friends;
    public FriendInf(){
        friends = new ArrayList<Friend>();
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                friends.add(new Friend(i,"张小" + i, "女", "66"+i));
            } else {
                friends.add(new Friend(i,"李小" + i, "男", "55"+i));
            }
        }
    }
    /**
     * 查询所有的好友
     */
    public ArrayList<Friend> searchAll(){
        return friends;
    }
}