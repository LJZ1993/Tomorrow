package com.example.administrator.tomorrow.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/10/15.
 */
public abstract class BaseMvpActivity<V,T extends BasePresenter<V>> extends AppCompatActivity{
    public T mPresenter;
    public String ssss;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.dettach();
    }

    public abstract T initPresenter();
}
