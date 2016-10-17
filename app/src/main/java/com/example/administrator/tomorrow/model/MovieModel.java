package com.example.administrator.tomorrow.model;

import com.example.administrator.tomorrow.bean.MovieListBean;
import com.example.administrator.tomorrow.contract.MovieContract;
import com.example.administrator.tomorrow.listener.OnHttpCallBackListener;
import com.example.administrator.tomorrow.utils.Constants;
import com.example.administrator.tomorrow.utils.RetrofitUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MoviewActivity的M
 */

public class MovieModel implements MovieContract.MovieModel {
    @Override
    public void dealMovie(int start, int count, final OnHttpCallBackListener<MovieListBean> onHttpCallBackListener) {
        RetrofitUtils.setBaseUrl(Constants.MOVIE_TOP250_URL)
                .getMovies(start, count)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieListBean movieListBean) {
                        onHttpCallBackListener.onSuccess(movieListBean);
                    }
                });

    }
}
