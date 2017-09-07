package com.example.administrator.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.recyclerviewdemo.R;
import com.example.administrator.recyclerviewdemo.views.SwipeMenuView;

import java.util.List;

/**
 * Created by Administrator on 2017/9/7.
 */

public class SwipDeleteAdapter extends RecyclerView.Adapter<SwipDeleteAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mDatas;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public SwipDeleteAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_swipe, parent, false);
        return new SwipDeleteAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_Title.setText(mDatas.get(position));
        //这句话关掉IOS阻塞式交互效果 并依次打开左滑右滑
        ((SwipeMenuView)holder.itemView).setIos(false).setLeftSwipe(true);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=mOnSwipeListener)
                {
                    mOnSwipeListener.onDel(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView tv_Title;
        public LinearLayout swipe_content;
        public Button btnDelete;
        public Button btnUnRead;
        public Button btnTop;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_Title = (TextView) mView.findViewById(R.id.tv_Title);
            swipe_content = (LinearLayout) mView.findViewById(R.id.swipe_content);
            btnDelete = (Button) mView.findViewById(R.id.btnDelete);
            btnUnRead = (Button) mView.findViewById(R.id.btnUnRead);
            btnTop = (Button) mView.findViewById(R.id.btnTop);
        }
    }

    //添加item事件点击接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String datas);
    }
    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);

        void onTop(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    /* 我们只要修改后数据用Adapter的notifyDatasetChange一下就可以更新界面。
   然而在RecyclerView中还有一些更高级的用法*/
    //添加数据：
    public void addItem(String content, int position) {
        mDatas.add(position, content);
        notifyItemInserted(position); //Attention!
        notifyItemRangeChanged(position, getItemCount());
    }

    public void removeItem(String model) {
        int position = mDatas.indexOf(model);
        mDatas.remove(position);
        notifyItemRemoved(position);//Attention!
        //notifyDataSetChanged();
        notifyItemRangeChanged(position, getItemCount());
    }

    public void removeItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);//Attention!
        //notifyDataSetChanged();
        notifyItemRangeChanged(position, getItemCount());
    }

    //LRRecycleerView 中的
//    public void addAll(Collection<T> list) {
//        int lastIndex = this.mDataList.size();
//        if (this.mDataList.addAll(list)) {
//            notifyItemRangeInserted(lastIndex, list.size());
//        }
//    }
//
    public void remove(int position) {
        this.mDatas.remove(position);
        notifyItemRemoved(position);
        if (position != (mDatas.size())) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, this.mDatas.size() - position);
        }
    }
}
