package com.example.administrator.tomorrow.ui.activity;

import android.app.Activity;
import android.content.Intent;

import com.example.administrator.tomorrow.R;
import com.example.administrator.tomorrow.ui.BaseActivity;

/**
 * 文章界面
 */
public class NewsActivity extends BaseActivity {
    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }

    public static void jumpToActivity(Activity activity) {
        activity.startActivity(new Intent(activity, NewsActivity.class));
    }
}
