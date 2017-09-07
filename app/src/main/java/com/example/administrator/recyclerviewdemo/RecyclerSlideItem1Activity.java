package com.example.administrator.recyclerviewdemo;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.recyclerviewdemo.adapter.SlideMenuAdapter1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 * 参考
 * http://blog.csdn.net/yhaolpz/article/details/77366154
 * 这边文章目前有分发事件的bug
 */

public class RecyclerSlideItem1Activity extends BaseActivity {
    private RecyclerView recycler_view;
    private SlideMenuAdapter1 mRecyclerViewAdapter;
    private List<String> mDatas = new ArrayList<String>();

    @Override
    protected void initVariables() {
        for (int i = 0; i < 15; i++) {
            mDatas.add("item" + i);
        }
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.slideitem1);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        //添加动画效果
        recycler_view.setItemAnimator(new DefaultItemAnimator());
     //   recycler_view.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 20, getResources().getColor(R.color.blue)));
        mRecyclerViewAdapter = new SlideMenuAdapter1(mContext, mDatas);
        recycler_view.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        //由于删除操作分割线会出问题，所以先在布局中试一下
        mRecyclerViewAdapter.setOnMenuClickListener(new SlideMenuAdapter1.OnMenuClickListener() {
            @Override
            public void onClick(View view, int position) {
             mRecyclerViewAdapter.removeItem(position);
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
