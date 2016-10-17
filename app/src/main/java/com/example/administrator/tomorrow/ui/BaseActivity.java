package com.example.administrator.tomorrow.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/10.
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initData();
        initView();
    }

    /**
     * 界面
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 获取布局
     *
     * @return
     */
    public abstract int getLayoutId();

}
