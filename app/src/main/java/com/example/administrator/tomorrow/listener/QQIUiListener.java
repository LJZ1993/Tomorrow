package com.example.administrator.tomorrow.listener;

import android.content.Context;
import android.util.Log;

import com.example.administrator.tomorrow.ui.activity.LoginActivity;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/10/11.
 */
public abstract class QQIUiListener implements IUiListener {


    @Override
    public void onError(UiError uiError) {

    }

    @Override
    public void onCancel() {

    }
}


