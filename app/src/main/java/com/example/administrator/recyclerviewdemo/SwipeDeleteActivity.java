package com.example.administrator.recyclerviewdemo;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.administrator.recyclerviewdemo.adapter.RecyclerViewAdapter;
import com.example.administrator.recyclerviewdemo.adapter.SwipDeleteAdapter;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/7.
 */

public class SwipeDeleteActivity extends BaseActivity {
    private LRecyclerView lr_recyclerView;
    private SwipDeleteAdapter mRecyclerViewAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<String> mDatas = new ArrayList<String>();
    private View mHeaderView;
    private View mFootView;

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_swipedelete);
        lr_recyclerView = (LRecyclerView) findViewById(R.id.lr_recyclerView);

        mRecyclerViewAdapter = new SwipDeleteAdapter(mContext, mDatas);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mRecyclerViewAdapter);
        lr_recyclerView.setAdapter(mLRecyclerViewAdapter);
        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.bule_overlay)
                .build();

        lr_recyclerView.addItemDecoration(divider);

        lr_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mHeaderView = View.inflate(getApplicationContext(), R.layout.lr_recycle_head, null);
        mFootView = View.inflate(getApplicationContext(), R.layout.lr_recycle_foot, null);
        mLRecyclerViewAdapter.addHeaderView(mHeaderView);
        lr_recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

                //执行刷新的操作
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDatas.clear();
                        for (int i = 0; i < 15; i++) {
                            mDatas.add("item" + i);
                        }
                        lr_recyclerView.refreshComplete(15);
                        //数据是变了这块里通知一下更新
                        mRecyclerViewAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });
        //设置头部加载颜色
        lr_recyclerView.setHeaderViewColor(R.color.colorAccent, R.color.colorPrimaryDark, android.R.color.white);
        //设置底部加载颜色
        lr_recyclerView.setFooterViewColor(R.color.colorAccent, R.color.colorPrimaryDark, android.R.color.white);
        //设置底部加载文字提示
        lr_recyclerView.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");

        lr_recyclerView.refresh();
        lr_recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                lr_recyclerView.refreshComplete(15);
            }
        }, 2000);

    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        mRecyclerViewAdapter.setOnDelListener(new SwipDeleteAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                mRecyclerViewAdapter.remove(pos);
            }

            @Override
            public void onTop(int pos) {

            }
        });

    }

    @Override
    protected void loadData() {

    }
}
