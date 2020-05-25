package com.hzxcompany.androidstudy.FriendListDemo;

import android.util.Log;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class Sort_phone implements Comparator<Friend> {
    private static final String TAG = "Sort_phone";
    private ArrayList<Friend> friends;

    public Sort_phone(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public int compare(Friend f1, Friend f2) {
        Comparator comparator = Collator.getInstance(Locale.CHINESE);
        int f1_index = 0;//f1在好友列表中的下标
        int f2_index = 0;//f2在好友列表中的下标
        long c = 0;//int类型不够用了，因为11位的电话号码轻松突破了65535，所以改用long类型
        try {//不过最好还是防范于未然吧
            c = Long.parseLong(f1.getPhone())-Long.parseLong(f2.getPhone());
        }catch (Exception e){
            Log.i(TAG,"可能是你的电话号码太长了！");
        }
        Log.i(TAG,c+"    "+f1.getId()+"    "+f2.getId());
        //c = o1.getXX - o2.getXX，结果是升序的，也就是从小到大，c>0说明两者需要交换顺序，c=0说明两者可交换可不交换，c<0说明不需要交换
        if(c>0){//将f1和f2交换位置
            for (int i =0;i<friends.size();i++){
                if (friends.get(i).getPhone().equals(f1.getPhone())){
                    f1_index += 1;
                }
                if (friends.get(i).getPhone().equals(f2.getPhone())){
                    f2_index += 1;
                }
            }
            Collections.swap(friends,f1_index,f2_index);
        }
        Log.i(TAG,"f1_index="+f1_index+",f1_phone="+f1.getPhone()+",f2_index="+f2_index+",f2_phone="+f2.getPhone());
        return  comparator.compare(f1.getPhone(),f2.getPhone());
    }
}
