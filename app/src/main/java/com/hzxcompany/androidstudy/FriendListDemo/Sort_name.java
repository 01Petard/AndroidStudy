package com.hzxcompany.androidstudy.FriendListDemo;

import android.util.Log;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class Sort_name implements Comparator<Friend> {
    private static final String TAG = "Sort_name";
    private ArrayList<Friend> friends;

    public Sort_name(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public int compare(Friend f1, Friend f2) {
        Comparator comparator = Collator.getInstance(Locale.CHINESE);
        String name1 = f1.getName();
        String name2 = f2.getName();
        int f1_index = 0;//f1在好友列表中的下标
        int f2_index = 0;//f2在好友列表中的下标
        for (int i =0;i<Math.max(name1.length(),name2.length());i++){
            int c = (int)name1.charAt(i)-(int)name2.charAt(i);
            Log.i(TAG,"name1.charAt("+i+")="+name1.charAt(i)+"   "+"name2.charAt("+i+")="+name2.charAt(i));
            Log.i(TAG,c+"    "+f1.getName()+"    "+f2.getName());
            //c = o1.getXX - o2.getXX，结果是升序的，也就是从小到大，c>0说明两者需要交换顺序，c=0说明两者可交换可不交换，c<0说明不需要交换
            if(c>0){//将f1和f2交换位置
                for (int j =0;j<friends.size();j++){
                    if (friends.get(j).getName().equals(name1)){
                        f1_index += 1;
                    }
                    if (friends.get(j).getName().equals(name2)){
                        f2_index += 1;
                    }
                }
                Collections.swap(friends,f1_index,f2_index);
            }
        }
        Log.i(TAG,"f1_index="+f1_index+",f1_name="+f1.getName()+",f2_index="+f2_index+",f2_name="+f2.getName());
        return  comparator.compare(name1,name2);
    }
}
