package com.example.administrator.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.recyclerviewdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2017/9/8.
 */

public class ListDragMenuAdapter extends RecyclerView.Adapter<ListDragMenuAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mDatas;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    /**
     * Item拖拽滑动帮助
     */
    private ItemTouchHelper itemTouchHelper;

    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setItemTouchHelper(ItemTouchHelper itemTouchHelper) {
        this.itemTouchHelper = itemTouchHelper;
    }

    public ListDragMenuAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
        return new ListDragMenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final View itemView = holder.mView;
        holder.tv_Title.setText(mDatas.get(position));
        //这块也可以setTag这种数据和View的绑定
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onItemClick(itemView, mDatas.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        public View mView;
        public TextView tv_Title;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_Title = (TextView) mView.findViewById(R.id.tv_Title);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //   if (view == mIvTouch)
            itemTouchHelper.startDrag(this);
            return false;
        }
    }

    //添加item事件点击接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String datas);
    }

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

    public void remove(int position) {
        this.mDatas.remove(position);
        notifyItemRemoved(position);

        if (position != (mDatas.size())) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, this.mDatas.size() - position);
        }
    }
}
