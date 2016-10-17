package com.example.administrator.tomorrow.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.administrator.tomorrow.R;
import com.example.administrator.tomorrow.listener.QQIUiListener;
import com.example.administrator.tomorrow.ui.BaseActivity;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/10/11.
 */
public class UserLoginActivity extends BaseActivity {
    @Bind(R.id.m_login_qq)
    ImageButton mLoginQq;
    @Bind(R.id.m_login_wechat)
    ImageButton mLoginWechat;
    private Tencent mTencent;
    private String scope = "all";
    private static final String APPID = "222222";
    private QQIUiListener loginListener;
    private QQIUiListener userInfoListener;
    private UserInfo userInfo;

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        mTencent = Tencent.createInstance(APPID, UserLoginActivity.this);
        loginListener = new QQIUiListener() {
            @Override
            public void onComplete(Object o) {
                if (o == null) {
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) o;

                    String msg = jo.getString("msg");

                    System.out.println("json=" + String.valueOf(jo));

                    System.out.println("msg=" + msg);
                    if ("sucess".equals(msg)) {
                        String openID = jo.getString("openid");
                        String accessToken = jo.getString("access_token");
                        String expires = jo.getString("expires_in");
                        mTencent.setOpenId(openID);
                        mTencent.setAccessToken(accessToken, expires);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        };
        userInfoListener = new QQIUiListener() {
            @Override
            public void onComplete(Object o) {
                if (o == null) {
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) o;
                    int ret = jo.getInt("ret");
                    Log.w("nickName", "nickName  " + ret);
                    if (ret == 100030) {
                        Runnable r = new Runnable() {
                            public void run() {
                                mTencent.reAuth(UserLoginActivity.this, "all", new QQIUiListener() {
                                            @Override
                                            public void onComplete(Object o) {

                                            }
                                        }
                                );
                            }
                        };

                        UserLoginActivity.this.runOnUiThread(r);
                    } else {
                        String nickName = jo.getString("nickname");
                        String gender = jo.getString("gender");
                        Log.w("nickName", "nickName  " + nickName + gender);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        };

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_userlogin;
    }


    @OnClick({R.id.m_login_qq, R.id.m_login_wechat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.m_login_qq:
                login();
                break;
            case R.id.m_login_wechat:
                break;
        }
    }

    private void login() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(UserLoginActivity.this, scope, loginListener);
        }
        if (mTencent.getQQToken() == null) {
            System.out.println("qqtoken == null");
        }
        userInfo = new UserInfo(getApplicationContext(), mTencent.getQQToken());
        userInfo.getUserInfo(userInfoListener);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.RESULT_LOGIN) {
                Tencent.handleResultData(data, loginListener);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        if (mTencent != null) {
            mTencent.logout(UserLoginActivity.this);
        }
        super.onDestroy();
    }
}
