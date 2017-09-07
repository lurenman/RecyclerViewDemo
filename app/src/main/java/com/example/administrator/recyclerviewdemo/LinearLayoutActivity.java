package com.example.administrator.recyclerviewdemo;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.administrator.recyclerviewdemo.adapter.RecyclerViewAdapter;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */

public class LinearLayoutActivity extends BaseActivity {
    private LRecyclerView lr_recyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<String> mDatas = new ArrayList<String>();
    private View mHeaderView;
    private View mFootView;

    @Override
    protected void initVariables() {
        for (int i = 0; i < 15; i++) {
            mDatas.add("item" + i);
        }
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_linearlayoutactivity);
        lr_recyclerView = (LRecyclerView) findViewById(R.id.lr_recyclerView);

        mRecyclerViewAdapter = new RecyclerViewAdapter(mContext, mDatas);
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
       // mLRecyclerViewAdapter.addFooterView(mFootView);

        //禁用下拉刷新功能
       // lr_recyclerView.setPullRefreshEnabled(false);

        //禁用自动加载更多功能
      //  lr_recyclerView.setLoadMoreEnabled(false);

        lr_recyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        lr_recyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        lr_recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        lr_recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                //执行刷新的操作
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        lr_recyclerView.refreshComplete(10);
                    }
                }, 2000);
            }
        });
        //设置头部加载颜色
        lr_recyclerView.setHeaderViewColor(R.color.colorAccent, R.color.colorPrimaryDark ,android.R.color.white);
        //设置底部加载颜色
        lr_recyclerView.setFooterViewColor(R.color.colorAccent, R.color.colorPrimaryDark ,android.R.color.white);
        //设置底部加载文字提示
        lr_recyclerView.setFooterViewHint("拼命加载中","已经全部为你呈现了","网络不给力啊，点击再试一次吧");

        lr_recyclerView.refresh();
        lr_recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                lr_recyclerView.refreshComplete(16);
            }
        }, 2000);



    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
         mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
             @Override
             public void onItemClick(View view, int position) {
                // mRecyclerViewAdapter.removeItem(position);
                 //这块发现如果删除够快的话就会出现数组越界的情况，因为recyclerView没有及时更新数据
                 //网上有开启线程延迟解决方案
                 mRecyclerViewAdapter.remove(position);
             }
         });
    }

    @Override
    protected void loadData() {

    }
}
