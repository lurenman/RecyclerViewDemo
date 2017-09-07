package com.example.administrator.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/9/6.
 */

public class EndStaggeredGridLayoutAdapter extends RecyclerView.Adapter<EndStaggeredGridLayoutAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mDatas;
    private List<Integer> mHeights;

    public EndStaggeredGridLayoutAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
//        mHeights=new ArrayList<>();
//        for (int i = 0; i < mDatas.size(); i++) {
//            //模拟随机高度
//            int height = new Random().nextInt(1000);
//            if(height < 100) {
//                height += 400;
//            }
//            mHeights.add(height);
//        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_item_card, parent, false);
        return new EndStaggeredGridLayoutAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      //  final View itemView = holder.mView;
        holder.tv_Title.setText(mDatas.get(position));

        //修改高度，模拟交错效果
     //   holder.card_view.getLayoutParams().height = mHeights.get(position);
    }

    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView tv_Title;
        public CardView card_view;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tv_Title = (TextView) mView.findViewById(R.id.tv_Title);
            card_view=(CardView)mView.findViewById(R.id.card_view);


        }
    }
    public void clear() {
        mDatas.clear();
       // mHeights.clear();
        notifyDataSetChanged();
    }

}
