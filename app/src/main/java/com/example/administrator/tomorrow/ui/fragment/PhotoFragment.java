package com.example.administrator.tomorrow.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.administrator.tomorrow.R;
import com.example.administrator.tomorrow.adapter.PhotoAdapter;
import com.example.administrator.tomorrow.ui.BaseFragment;

import butterknife.Bind;

/**
 * 照片的Fragment
 */
public class PhotoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "PhotoFragment";
    @Bind(R.id.m_fp_rv)
    RecyclerView mFmRv;
    @Bind(R.id.m_fp_swiperefresh)
    SwipeRefreshLayout mFmSwiperefresh;
    private PhotoAdapter mPhotoAdapter;

    @Override
    protected void initView() {
        mFmSwiperefresh.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mFmSwiperefresh.setOnRefreshListener(this);
        mFmRv.setHasFixedSize(true);
        mFmRv.setLayoutManager(new LinearLayoutManager(mActivity));
        mPhotoAdapter = new PhotoAdapter(mActivity);
        mFmRv.setAdapter(mPhotoAdapter);
        Log.w("onBindViewHolder", "onBindViewHolderdgfdsagsd");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_photo;
    }

    @Override
    public void onRefresh() {
        //对RV进行数据的更新
        Log.w(TAG, "对RV进行数据的更新");
        mFmSwiperefresh.setRefreshing(false);
    }
}

