package com.hzxcompany.androidstudy.FriendListDemo;

import android.util.Log;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class Sort_id implements Comparator<Friend> {
    private static final String TAG = "Sort_id";
    private ArrayList<Friend> friends;

    public Sort_id(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    @Override
    public int compare(Friend f1, Friend f2) {
        Comparator comparator = Collator.getInstance(Locale.CHINESE);
        String id1 = f1.getId();
        String id2 = f2.getId();
        if (Integer.parseInt(f1.getId()) > Integer.parseInt(f2.getId())) {
            int f1_index = 0;
            int f2_index = 0;
            for (int i =0;i<friends.size();i++){
                if (friends.get(i).getId().equals(f1.getId())){
                    f1_index += 1;
                }
                if (friends.get(i).getId().equals(f2.getId())){
                    f2_index += 1;
                }
            }
            Collections.swap(friends, f1_index, f2_index);
        }

        Log.i(TAG,"f1.id()="+f1.getId()+",f2.id()="+f2.getId());

        return comparator.compare(id1, id2);
    }
}
