package com.example.administrator.tomorrow.listener;

/**
 * 网络请求回调接口
 */
public interface OnHttpCallBackListener<T> {
    void onSuccess(T t);

    void onFailed(String errorMessage);
}
