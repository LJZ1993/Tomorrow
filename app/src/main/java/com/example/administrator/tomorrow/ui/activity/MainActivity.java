package com.example.administrator.tomorrow.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.tomorrow.R;
import com.example.administrator.tomorrow.ui.BaseActivity;
import com.example.administrator.tomorrow.ui.fragment.ArticleFragment;
import com.example.administrator.tomorrow.ui.fragment.MovieFragment;
import com.example.administrator.tomorrow.ui.fragment.PhotoFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity {


    @Bind(R.id.m_main_ll)
    LinearLayout mMainLl;
    @Bind(R.id.m_main_navi)
    NavigationView mMainNavi;
    @Bind(R.id.m_main_dl)
    DrawerLayout mMainDl;
    @Bind(R.id.m_main_tb)
    Toolbar mMainTb;
    @Bind(R.id.m_main_fl)

    FrameLayout mMainFl;
    ImageView mNaviHeaderIv;

    @Override
    public void initView() {
        //获取Navid最上部的图片控件
        View headerView = mMainNavi.getHeaderView(0);
        mNaviHeaderIv = (ImageView) headerView.findViewById(R.id.m_navi_header_iv);
        mNaviHeaderIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserLoginActivity.class));
            }
        });
        mMainTb.setNavigationIcon(R.drawable.ic_add);
        mMainTb.setTitle("点击");
        setSupportActionBar(this.mMainTb);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, this.mMainDl, this.mMainTb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();//初始化
        mMainDl.setDrawerListener(toggle);
        final DrawerLayout mMainDl1 = MainActivity.this.mMainDl;

        mMainNavi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_news:
                        getSupportFragmentManager().beginTransaction().replace(R.id.m_main_fl, new ArticleFragment()).commit();
                        break;
                    case R.id.nav_photo:
                        getSupportFragmentManager().beginTransaction().replace(R.id.m_main_fl, new PhotoFragment()).commit();
                        break;
                    case R.id.nav_movie:
                        getSupportFragmentManager().beginTransaction().replace(R.id.m_main_fl, new MovieFragment()).commit();
                        break;
                    default:
                        break;
                }
                item.setCheckable(true);
                mMainDl1.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    public static void jumpToActivity(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }
}
