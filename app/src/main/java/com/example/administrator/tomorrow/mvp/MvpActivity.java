package com.example.administrator.tomorrow.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2016/10/15.
 */
public class MvpActivity extends BaseMvpActivity<MvpView,MvpPresenter> implements MvpView{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public MvpPresenter initPresenter() {
        return null;
    }

    @Override
    public void showMessage() {

    }
}
