package com.hzxcompany.androidstudy.FriendListDemo;

import com.hzxcompany.androidstudy.R;

import java.util.ArrayList;
import java.util.Random;

public class FriendDao {
    private ArrayList<Friend> friends;

    public FriendDao(){
        friends = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            String phone1 = String.valueOf(r.nextInt(100)+100);
            String phone2 = String.valueOf(r.nextInt(1000000000));
            String phone = phone1+phone2;
            if (i % 2 == 0) {
                friends.add(new Friend(R.mipmap.friend_icon,String.valueOf(r.nextInt(10000)),"张" + r.nextInt(100),"男",phone));
            } else {
                friends.add(new Friend(R.mipmap.friend_icon,String.valueOf(r.nextInt(10000)),"李" + r.nextInt(100),"女",phone));
            }
        }
        friends.set(2,new Friend(String.valueOf(r.nextInt(10000)),"黄泽校","男","13248684099"));
    }
    /**
     * 查询所有的好友
     */
    public ArrayList<Friend> searchAll(){
        return friends;
    }
    public void addFriend(String id,String name,String sex,String phone){
        friends.add(new Friend(R.mipmap.friend_icon,id,name,sex,phone));
    }
    public void updateFriend(int id,Friend friend){
        friends.set(id,friend);
    }
    public void removeFriend(int position){
        friends.remove(position); //删除集合中的对象
    }

}