package com.example.administrator.recyclerviewdemo;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/5.
 */

public class ItemSlideDeleteActivity extends BaseActivity {
    private TextView tv_slideitem1;
    private TextView tv_SwipeRecyclerView;
    private TextView tv_SlidingButtonView;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_itemslidedelete);
        tv_slideitem1 = (TextView) findViewById(R.id.tv_slideitem1);
        tv_SwipeRecyclerView = (TextView) findViewById(R.id.tv_SwipeRecyclerView);
        tv_SlidingButtonView = (TextView) findViewById(R.id.tv_SlidingButtonView);

    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        tv_slideitem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ItemSlideDeleteActivity.this,RecyclerSlideItem1Activity.class);
                startActivity(intent);
            }
        });
        tv_SwipeRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ItemSlideDeleteActivity.this,SwipeRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        tv_SlidingButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ItemSlideDeleteActivity.this,SlidingButtonViewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
