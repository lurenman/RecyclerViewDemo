package com.example.administrator.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.recyclerviewdemo.R;
import com.example.administrator.recyclerviewdemo.views.SlidingMenu;

import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 * http://blog.csdn.net/wangkai0681080/article/details/50082825RecyclerView里notifyItemRemoved的坑
 */

public class SlideMenuAdapter1 extends RecyclerView.Adapter<SlideMenuAdapter1.ViewHolder> {
    private Context mContext;
    private List<String> mDatas;
    private SlidingMenu mOpenMenu;
    private RecyclerViewAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(RecyclerViewAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void holdOpenMenu(SlidingMenu slidingMenu) {
        mOpenMenu = slidingMenu;
    }

    public void closeOpenMenu() {
        if (mOpenMenu != null && mOpenMenu.isOpen()) {
            mOpenMenu.closeMenu();
        }
    }

    public SlideMenuAdapter1(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public SlideMenuAdapter1.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slidemenu1, parent, false);
        return new SlideMenuAdapter1.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final View itemView = holder.mView;
        holder.tv_Title.setText(mDatas.get(position));
        if (position==getItemCount()-1)//如果是最后一个就不让它显示
        {
            holder.v_divider.setVisibility(View.GONE);
        }else {
            holder.v_divider.setVisibility(View.VISIBLE);
        }

        //这块也可以setTag这种数据和View的绑定
        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnItemClickListener) {
                    mOnItemClickListener.onItemClick(itemView, mDatas.get(position));
                }
            }
        });
        holder.ll_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnMenuClickListener) {
                    closeOpenMenu();
                    mOnMenuClickListener.onClick(holder.ll_menu,position);
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
        public TextView menuText;
        public LinearLayout ll_content;
        public LinearLayout ll_menu;
        public View v_divider;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_Title = (TextView) mView.findViewById(R.id.tv_Title);
            menuText = (TextView) mView.findViewById(R.id.menuText);
            ll_content = (LinearLayout) mView.findViewById(R.id.content);
            ll_menu = (LinearLayout) mView.findViewById(R.id.menu);
            v_divider=(View)mView.findViewById(R.id.v_divider);

        }
    }

    //添加item事件点击接口
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String datas);
    }

    public interface OnMenuClickListener {
        void onClick(View view, int position);
    }

    private OnMenuClickListener mOnMenuClickListener;

    public void setOnMenuClickListener(OnMenuClickListener onMenuClickListener) {
        this.mOnMenuClickListener = onMenuClickListener;
    }
    //添加数据：
    public void addItem(String content, int position) {
        mDatas.add(position, content);
        notifyItemInserted(position); //Attention!
        notifyItemRangeChanged(position,getItemCount());
    }

    //删除数据：
//    先remove,再notifyItemRemoved， 最后再notifyItemRangeChanged
//    remove：把数据从list中remove掉，
//    notifyItemRemoved：显示动画效果
//    notifyItemRangeChanged：对于被删掉的位置及其后range大小范围内的view进行重新onBindViewHolder
    public void removeItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);//Attention!
        //notifyDataSetChanged();
        notifyItemRangeChanged(position,getItemCount());
    }


}
