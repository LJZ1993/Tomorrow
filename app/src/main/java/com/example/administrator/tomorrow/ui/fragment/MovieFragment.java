package com.example.administrator.tomorrow.ui.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.tomorrow.R;
import com.example.administrator.tomorrow.adapter.MovieAdapter;
import com.example.administrator.tomorrow.bean.MovieListBean;
import com.example.administrator.tomorrow.contract.MovieContract;
import com.example.administrator.tomorrow.listener.LoadingMoreBottomListener;
import com.example.administrator.tomorrow.presenter.MoviePresenter;
import com.example.administrator.tomorrow.ui.BaseFragment;
import com.example.administrator.tomorrow.view.MovieView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/10/14.
 */
public class MovieFragment extends BaseFragment implements MovieContract.MovieView {
    private static final String TAG = "MovieFragment";
    @Bind(R.id.m_db_rv)
    RecyclerView mDbRv;
    @Bind(R.id.m_db_swiperefresh)
    SwipeRefreshLayout mDbSwiperefresh;
    MoviePresenter mPresenter;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        mDbSwiperefresh.setRefreshing(false);
        mPresenter = new MoviePresenter(this);
        mPresenter.dealMovieData();
        mDbRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mDbRv.setHasFixedSize(true);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void setBottom(int lastIndex) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showInfo(String info) {

    }

    @Override
    public void showData(final List<MovieListBean.SubjectsBean> subjectsBeanList) {
        final Handler handler = new Handler();
        final MovieAdapter adapter = new MovieAdapter(mActivity, mDbRv, subjectsBeanList);
        mDbRv.setAdapter(adapter);
        adapter.setLoadingMoreBottomListener(new LoadingMoreBottomListener() {
            @Override
            public void onLoadingMore() {
                subjectsBeanList.add(null);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //取消progress
                        subjectsBeanList.remove(subjectsBeanList.size() - 1);
                        adapter.notifyDataSetChanged();
                        //加载更多的数据设置
                        mPresenter.loadMovieDataMore();
                        adapter.notifyDataSetChanged();
                        adapter.unLoading();
                    }
                }, 3000);
            }
        });
    }
}