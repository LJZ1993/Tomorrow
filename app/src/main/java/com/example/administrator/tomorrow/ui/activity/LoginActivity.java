package com.example.administrator.tomorrow.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.administrator.tomorrow.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity {
    private static final String APPID = "222222";
    private Tencent mTencent;
    private IUiListener loginListener;
    private IUiListener userInfoListener;
    private String scope;

    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        setupViews();
        initData();
    }

    @Override
    protected void onDestroy() {
        if (mTencent != null) {
            mTencent.logout(LoginActivity.this);
        }
        super.onDestroy();
    }

    private void setupViews() {

        findViewById(R.id.m_login_qq).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        findViewById(R.id.m_login_wechat).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (mTencent.getQQToken() == null) {
                    System.out.println("qqtoken == null");
                }
                userInfo = new UserInfo(getApplicationContext(), mTencent.getQQToken());
                userInfo.getUserInfo(userInfoListener);
            }
        });
    }

    private void initData() {
        mTencent = Tencent.createInstance(APPID, LoginActivity.this);
        scope = "all";
        loginListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onComplete(Object value) {
                if (value == null) {
                    return;
                }

                try {
                    JSONObject jo = (JSONObject) value;

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

            @Override
            public void onCancel() {
                // TODO Auto-generated method stub

            }
        };

        userInfoListener = new IUiListener() {

            @Override
            public void onError(UiError arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onComplete(Object arg0) {
                if (arg0 == null) {
                    return;
                }
                try {
                    JSONObject jo = (JSONObject) arg0;
                    int ret = jo.getInt("ret");
                    Log.w("nickName", "nickName  " + ret);
                    if (ret == 100030) {
                        Runnable r = new Runnable() {
                            public void run() {
                                mTencent.reAuth(LoginActivity.this, "all", new IUiListener() {

                                    @Override
                                    public void onError(UiError arg0) {
                                        // TODO Auto-generated method stub

                                    }

                                    @Override
                                    public void onComplete(Object arg0) {
                                        // TODO Auto-generated method stub

                                    }

                                    @Override
                                    public void onCancel() {
                                        // TODO Auto-generated method stub

                                    }
                                });
                            }
                        };

                        LoginActivity.this.runOnUiThread(r);
                    } else {
                        String nickName = jo.getString("nickname");
                        String gender = jo.getString("gender");
                        Log.w("nickName", "nickName  " + nickName + gender);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }


            }

            @Override
            public void onCancel() {
                // TODO Auto-generated method stub

            }
        };
    }

    private void login() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(LoginActivity.this, scope, loginListener);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode ==Constants.RESULT_LOGIN) {
                Tencent.handleResultData(data, loginListener);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
