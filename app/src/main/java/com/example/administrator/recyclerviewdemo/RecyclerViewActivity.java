package com.example.administrator.recyclerviewdemo;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.recyclerviewdemo.adapter.RecyclerViewAdapter;
import com.example.administrator.recyclerviewdemo.divider.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/29.
 */

public class RecyclerViewActivity extends BaseActivity {
    private RecyclerView recycler_view;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private List<String> mDatas = new ArrayList<String>();
    @Override
    protected void initVariables() {
        for (int i = 0; i < 15; i++) {
            mDatas.add("item" + i);
        }
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_recyclerview);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        //添加动画效果
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
//        recycler_view.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));
        recycler_view.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,20,getResources().getColor(R.color.blue)));
        mRecyclerViewAdapter = new RecyclerViewAdapter(mContext, mDatas);
        recycler_view.setAdapter(mRecyclerViewAdapter);

    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        //添加事件监听
        mRecyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, String datas) {
                Toast.makeText(getApplicationContext(),datas,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
