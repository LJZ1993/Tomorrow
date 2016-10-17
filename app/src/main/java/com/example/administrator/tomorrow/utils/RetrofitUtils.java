package com.example.administrator.tomorrow.utils;

import com.example.administrator.tomorrow.Api;
import com.example.administrator.tomorrow.api.ApiService;
import com.example.administrator.tomorrow.bean.MovieListBean;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/13.
 */
public class RetrofitUtils {
    public static ApiService service;

    public static ApiService setBaseUrl(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
        return service;
    }


}
