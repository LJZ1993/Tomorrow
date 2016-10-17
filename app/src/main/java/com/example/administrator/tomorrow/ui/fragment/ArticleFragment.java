package com.example.administrator.tomorrow.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.tomorrow.R;
import com.example.administrator.tomorrow.adapter.ArticleAdapter;
import com.example.administrator.tomorrow.bean.ArticleListBean;
import com.example.administrator.tomorrow.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/10/11.
 */
public class ArticleFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ArticleFragment";

    @Bind(R.id.m_fa_recyclerview)
    RecyclerView mFaRecyclerview;
    @Bind(R.id.m_fa_swiperefreshlayout)
    SwipeRefreshLayout mFaSwiperefreshlayout;
    List<ArticleListBean.ResultBean> mResultBeanList = new ArrayList<>();

    @Override
    protected void initView() {
        mFaSwiperefreshlayout.setColorSchemeResources(R.color.colorBlue, R.color.colorBlue, R.color.colorBlue, R.color.colorBlue);
        mFaSwiperefreshlayout.setOnRefreshListener(this);
        mFaRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity));
        mFaRecyclerview.setHasFixedSize(true);
        mFaRecyclerview.setAdapter(new ArticleAdapter(mActivity, mResultBeanList));
    }

    @Override
    protected void initData() {


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_article;
    }

    @Override
    public void onRefresh() {
        mFaSwiperefreshlayout.setRefreshing(false);
    }
}
