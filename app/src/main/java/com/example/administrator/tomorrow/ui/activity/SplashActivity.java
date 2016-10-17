package com.example.administrator.tomorrow.ui.activity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.tomorrow.R;
import com.example.administrator.tomorrow.ui.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * splash 界面
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.m_splash_iv_outer)
    ImageView mSplashIvOuter;
    @Bind(R.id.m_splash_iv_innner)
    ImageView mSplashIvInnner;
    @Bind(R.id.m_splash_tv)
    TextView mSplashTv;

    @Override
    public void initView() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_splash);
        mSplashIvOuter.setAnimation(animation);
        mSplashIvInnner.setAnimation(animation);
        MainActivity.jumpToActivity(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
}
