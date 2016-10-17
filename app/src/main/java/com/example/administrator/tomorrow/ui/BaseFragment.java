package com.example.administrator.tomorrow.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/11.
 */
public abstract class BaseFragment extends Fragment {
    public FragmentActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mActivity = getActivity();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initData();
        initView();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(mActivity);
    }
}
