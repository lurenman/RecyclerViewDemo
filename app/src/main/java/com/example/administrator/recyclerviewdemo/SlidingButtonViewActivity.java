package com.example.administrator.recyclerviewdemo;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.recyclerviewdemo.adapter.SlidingButtonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 */

public class SlidingButtonViewActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private SlidingButtonAdapter mSlidingButtonAdapter;
    private List<String> mDatas = new ArrayList<String>();
    @Override
    protected void initVariables() {
        for (int i = 0; i < 15; i++) {
            mDatas.add("item" + i);
        }
    }

    @Override
    protected void initViews() {
       setContentView(R.layout.activity_slidingbutton);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mSlidingButtonAdapter=new SlidingButtonAdapter(this,mDatas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSlidingButtonAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        mSlidingButtonAdapter.setmIDeleteBtnClickListener(new SlidingButtonAdapter.IonSlidingViewClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onDeleteBtnCilck(View view, int position) {

            }
        });
    }

    @Override
    protected void loadData() {

    }
}
