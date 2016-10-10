package com.example.administrator.diffutildemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * DiffUtil 数据发生变化时 只对数据发生变化的UI进行刷新，不会全部刷新RecycleView  也可以刷新指定位置的UI，提高了性能
 */

public class MainActivity extends AppCompatActivity {

    List<String> datas = new ArrayList<>();
    List<String> newDatas = new ArrayList<>();
    private RecyclerView rl;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 10; i++) {
            datas.add("老数据"+i);
        }

        newDatas.addAll(datas);
        for (int i='a';i<'z';i++){
            newDatas.add("新数据"+i);
        }
        mAdapter = new MyAdapter(this,datas);
        rl = (RecyclerView) findViewById(R.id.id_rycle);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rl.setLayoutManager(manager);
        rl.setAdapter(mAdapter);
    }

    public void refreshData(View view){
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallBack(datas,newDatas),false);
        result.dispatchUpdatesTo(mAdapter);
        datas = newDatas;
        mAdapter.setDatas(datas);
    }

    public void webView(View view){
        startActivity(new Intent(this,WebViewActivitys.class));
    }
}
