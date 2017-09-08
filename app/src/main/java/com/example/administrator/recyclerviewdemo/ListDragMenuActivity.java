package com.example.administrator.recyclerviewdemo;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.recyclerviewdemo.adapter.ListDragMenuAdapter;
import com.example.administrator.recyclerviewdemo.divider.RecycleViewDivider;
import com.example.administrator.recyclerviewdemo.itemtouch.DefaultItemTouchHelpCallback;
import com.example.administrator.recyclerviewdemo.itemtouch.DefaultItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 * 参考文章
 * http://blog.csdn.net/yanzhenjie1003/article/details/51935982
 */

public class ListDragMenuActivity extends BaseActivity {
    private RecyclerView recycler_view;
    private ListDragMenuAdapter mListDragMenuAdapter;
    private List<String> mDatas = new ArrayList<String>();
    /**
     * 滑动拖拽的帮助类
     */
    private DefaultItemTouchHelper itemTouchHelper;
    @Override
    protected void initVariables() {
        for (int i = 0; i < 15; i++) {
            mDatas.add("item" + i);
        }
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_listdragmenu);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setLayoutManager(new LinearLayoutManager(mContext));
        //添加动画效果
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL,20,getResources().getColor(R.color.blue)));
        mListDragMenuAdapter=new ListDragMenuAdapter(mContext,mDatas);
        recycler_view.setAdapter(mListDragMenuAdapter);
        // 把ItemTouchHelper和itemTouchHelper绑定
        itemTouchHelper = new DefaultItemTouchHelper(onItemTouchCallbackListener);
        itemTouchHelper.attachToRecyclerView(recycler_view);

        mListDragMenuAdapter.setItemTouchHelper(itemTouchHelper);

        itemTouchHelper.setDragEnable(true);//这个设置左右是否可以滑动的
        itemTouchHelper.setSwipeEnable(true);//这个设置上下是否可以拖拽的
    }

    @Override
    protected void loadData() {

    }
    private DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener = new DefaultItemTouchHelpCallback.OnItemTouchCallbackListener() {
        @Override
        public void onSwiped(int adapterPosition) {
            if (mDatas != null) {
                mDatas.remove(adapterPosition);
                mListDragMenuAdapter.notifyItemRemoved(adapterPosition);
            }
        }

        @Override
        public boolean onMove(int srcPosition, int targetPosition) {
            if (mDatas != null) {
                // 更换数据源中的数据Item的位置
                Collections.swap(mDatas, srcPosition, targetPosition);

                // 更新UI中的Item的位置，主要是给用户看到交互效果
                mListDragMenuAdapter.notifyItemMoved(srcPosition, targetPosition);
                return true;
            }
            return false;
        }
    };
}
