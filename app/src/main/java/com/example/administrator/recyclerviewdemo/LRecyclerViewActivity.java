package com.example.administrator.recyclerviewdemo;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/5.
 */

public class LRecyclerViewActivity extends BaseActivity {
    private TextView tv_LinearLayoutActivity;
    private TextView tv_EndlessStaggeredGridLayoutActivity;
    private TextView tv_swipedelete;

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_lrecyclerview);
        tv_LinearLayoutActivity = (TextView) findViewById(R.id.tv_LinearLayoutActivity);
        tv_EndlessStaggeredGridLayoutActivity = (TextView) findViewById(R.id.tv_EndlessStaggeredGridLayoutActivity);
        tv_swipedelete = (TextView) findViewById(R.id.tv_swipedelete);

    }

    @Override
    protected void initEnvent() {
        super.initEnvent();
        tv_LinearLayoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LRecyclerViewActivity.this,LinearLayoutActivity.class);
                startActivity(intent);
            }
        });
        tv_EndlessStaggeredGridLayoutActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LRecyclerViewActivity.this,EndlessStaggeredGridLayoutActivity.class);
                startActivity(intent);
            }
        });
        tv_swipedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LRecyclerViewActivity.this,SwipeDeleteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void loadData() {

    }
}
