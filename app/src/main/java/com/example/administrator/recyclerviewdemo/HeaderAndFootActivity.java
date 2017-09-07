package com.example.administrator.recyclerviewdemo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.recyclerviewdemo.adapter.HeaderAndFooterRecyclerViewAdapter;
import com.example.administrator.recyclerviewdemo.adapter.RecyclerViewAdapter;
import com.example.administrator.recyclerviewdemo.adapter.RecyclerWrapAdapter;
import com.example.administrator.recyclerviewdemo.divider.RecycleViewDivider;
import com.example.administrator.recyclerviewdemo.views.WrapRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class HeaderAndFootActivity extends BaseActivity {
    private WrapRecyclerView wr_recyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
   // private RecyclerWrapAdapter mWrapRecyclerViewAdapter;
    private List<String> mDatas = new ArrayList<String>();
    private View mHeaderView;
    private View mFootView;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private RecyclerView rv_recyclerView;

    @Override
    protected void initVariables() {
        for (int i = 0; i < 15; i++) {
            mDatas.add("item" + i);
        }
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_headerfoot);
//        wr_recyclerView = (WrapRecyclerView) findViewById(R.id.wr_recyclerView);
//        wr_recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        rv_recyclerView = (RecyclerView) findViewById(R.id.rv_recyclerView);
        rv_recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerViewAdapter = new RecyclerViewAdapter(mContext, mDatas);
        mHeaderView = View.inflate(getApplicationContext(), R.layout.recycle_head, null);
        mFootView = View.inflate(getApplicationContext(), R.layout.recycle_foot, null);
//        wr_recyclerView.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,20,getResources().getColor(R.color.blue)));
//        wr_recyclerView.addHeaderView(mHeaderView);
//       // wr_recyclerView.addFootView(mFootView);
//        wr_recyclerView.setAdapter(mRecyclerViewAdapter);
        mHeaderAndFooterRecyclerViewAdapter=new HeaderAndFooterRecyclerViewAdapter(mRecyclerViewAdapter);
        mHeaderAndFooterRecyclerViewAdapter.addHeaderView(mHeaderView);
        mHeaderAndFooterRecyclerViewAdapter.addFooterView(mFootView);
        rv_recyclerView.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,20,getResources().getColor(R.color.blue)));
        rv_recyclerView.setAdapter(mHeaderAndFooterRecyclerViewAdapter);







    }

    @Override
    protected void initEnvent() {
        super.initEnvent();

    }

    @Override
    protected void loadData() {

    }
}
