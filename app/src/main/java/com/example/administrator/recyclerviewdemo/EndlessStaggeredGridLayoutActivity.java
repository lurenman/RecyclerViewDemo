package com.example.administrator.recyclerviewdemo;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.recyclerviewdemo.adapter.EndStaggeredGridLayoutAdapter;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/6.
 */

public class EndlessStaggeredGridLayoutActivity extends BaseActivity {
    private static final String TAG = "EndlessStaggeredGridLay";
    private LRecyclerView lr_recyclerView;
    private EndStaggeredGridLayoutAdapter mRecyclerViewAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter = null;
    private List<String> mDatas = new ArrayList<String>();
    private View mHeaderView;
    private View mFootView;
    /**
     * 服务器端一共多少条数据
     */
    private static final int TOTAL_COUNTER = 34;
    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;

    /**
     * 已经获取到多少条数据了
     */
    private static int mCurrentCounter = 0;
    private TextView tv_Localrefresh;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_endlessstaggered);
        tv_Localrefresh = (TextView) findViewById(R.id.tv_Localrefresh);
        lr_recyclerView = (LRecyclerView) findViewById(R.id.lr_recyclerView);

        mRecyclerViewAdapter = new EndStaggeredGridLayoutAdapter(mContext, mDatas);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mRecyclerViewAdapter);
        lr_recyclerView.setAdapter(mLRecyclerViewAdapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //防止item位置互换
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        lr_recyclerView.setLayoutManager(layoutManager);
        mHeaderView = View.inflate(getApplicationContext(), R.layout.lr_recycle_head, null);
        mLRecyclerViewAdapter.addHeaderView(mHeaderView);
    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        lr_recyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

                //mRecyclerViewAdapter.clear();
                mDatas.clear();
                Toast.makeText(getApplicationContext(), "Refresh", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < REQUEST_COUNT; i++) {
                            mDatas.add("item" + i);
                        }
                        mCurrentCounter = mDatas.size();
                        lr_recyclerView.refreshComplete(REQUEST_COUNT);
                    }
                }, 2000);

            }
        });

        lr_recyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Toast.makeText(getApplicationContext(), "OnLoad", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> arrayList = new ArrayList<String>();
                        if (mCurrentCounter < TOTAL_COUNTER) {
                            // loading more
                            int count = mDatas.size() + REQUEST_COUNT;
                            if (count >= TOTAL_COUNTER)
                                count = TOTAL_COUNTER;
                            for (int i = mDatas.size(); i < count; i++) {
                                arrayList.add("item" + i);
                            }
                        } else {
                            //the end
                            lr_recyclerView.setNoMore(true);
                        }
                        if (arrayList.size() != 0) {
                            mDatas.addAll(arrayList);
                            mCurrentCounter += arrayList.size();
                        }
                        lr_recyclerView.refreshComplete(REQUEST_COUNT);
                    }
                }, 1000);
            }
        });
        lr_recyclerView.refresh();
        lr_recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                lr_recyclerView.refreshComplete(16);
            }
        }, 2000);

        tv_Localrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatas.size() != 0 && null != mDatas) {
                    mDatas.set(2, "局部刷新");
//                    int position = mLRecyclerViewAdapter.getAdapterPosition(false, 2);
//                    Logger.e(TAG,position+"");
                    //mLRecyclerViewAdapter.notifyItemChanged(position, "局部刷新");
                    // notifyItemChanged(int position, Object payload) 其中的payload相当于一个标记，类型不限
                    mRecyclerViewAdapter.notifyItemChanged(2, "局部刷新");
                }
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
