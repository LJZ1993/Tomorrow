package com.example.administrator.tomorrow.mvp;

/**
 * Created by Administrator on 2016/10/15.
 */
public abstract class BasePresenter<T> {
    public T mVIew;

    public void attach(T mVIew) {
        this.mVIew = mVIew;

    }

    public void dettach() {
        mVIew = null;
    }

    public abstract void noUserMethod();
}
