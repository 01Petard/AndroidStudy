package com.hzxcompany.androidstudy.FriendListDemo;

import android.util.Log;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class Sort_sex implements Comparator<Friend> {
    private static final String TAG = "Sort_id";
    private ArrayList<Friend> friends;

    public Sort_sex(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public int compare(Friend f1, Friend f2) {
        Comparator comparator = Collator.getInstance(Locale.CHINESE);
        String sex1 = f1.getSex();
        String sex2 = f2.getSex();
        int f1_index = 0;//f1在好友列表中的下标
        int f2_index = 0;//f2在好友列表中的下标
        for (int i =0;i<sex1.length();i++){
            int c = (sex1.equals("女") &&sex2.equals("男"))?1:-1;
            Log.i(TAG,c+"    "+f1.getSex()+"    "+f2.getSex());
            //c = o1.getXX - o2.getXX，结果是升序的，也就是从小到大，c>0说明两者需要交换顺序，c=0说明两者可交换可不交换，c<0说明不需要交换
            if(c>0){//将f1和f2交换位置
                for (int j =0;j<friends.size();j++){
                    if (friends.get(j).getSex().equals(sex1)){
                        f1_index += 1;
                    }
                    if (friends.get(j).getSex().equals(sex2)){
                        f2_index += 1;
                    }
                }
                Collections.swap(friends,f1_index,f2_index);
            }
        }
        Log.i(TAG,"f1_index="+f1_index+",f1_sex="+f1.getSex()+",f2_index="+f2_index+",f2_sex="+f2.getSex());
        return  comparator.compare(sex1,sex2);
    }
}