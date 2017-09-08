package com.example.administrator.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv_recyclerView;
    private TextView tv_recyclerViewHeaderFoot;
    private TextView tv_itemdelete;
    private TextView tv_LRecyclerView;
    private TextView tv_ListDragMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();

    }

    private void initViews() {
        tv_recyclerView = (TextView) findViewById(R.id.tv_recyclerView);
        tv_recyclerViewHeaderFoot = (TextView) findViewById(R.id.tv_recyclerViewHeaderFoot);
        tv_itemdelete = (TextView) findViewById(R.id.tv_itemdelete);
        tv_LRecyclerView = (TextView) findViewById(R.id.tv_LRecyclerView);
        tv_ListDragMenu = (TextView) findViewById(R.id.tv_ListDragMenu);
    }

    private void initEvents() {
        tv_recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        tv_recyclerViewHeaderFoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HeaderAndFootActivity.class);
                startActivity(intent);
            }
        });
        tv_itemdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ItemSlideDeleteActivity.class);
                startActivity(intent);
            }
        });
        tv_LRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        tv_ListDragMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListDragMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
