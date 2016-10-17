package com.example.administrator.tomorrow.mvp;

import android.content.Context;

/**
 * Created by Administrator on 2016/10/15.
 */
public class MvpPresenter extends BasePresenter<MvpView> {
    private Context mContext;
    MvpModel mMvpModel;
    public MvpPresenter(Context context) {
        mContext=context;
         mMvpModel = new MvpModel();
    }

    @Override
    public void noUserMethod() {

    }
    public void onResume(){

    }
}
