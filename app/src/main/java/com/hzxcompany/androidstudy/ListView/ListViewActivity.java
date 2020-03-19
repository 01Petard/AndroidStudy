package com.hzxcompany.androidstudy.ListView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.hzxcompany.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ListView listView = findViewById(R.id.lv_list_1);
        //String[] data = {"计算机导论","高等数学","高等物理","数据结构"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        CustomAdapter adapter=new CustomAdapter(this,getData());
        listView.setAdapter(adapter);
    }
    private List<Student> getData(){
        List<Student> stuList=new ArrayList<>();
        stuList.add(new Student("hzx1","18"));
        stuList.add(new Student("hzx2","19"));
        stuList.add(new Student("hzx3","20"));
        stuList.add(new Student("hzx4","21"));
        return stuList;
    }


}

