package com.example.administrator.tomorrow.presenter;

import com.example.administrator.tomorrow.bean.MovieListBean;
import com.example.administrator.tomorrow.contract.MovieContract;
import com.example.administrator.tomorrow.listener.OnHttpCallBackListener;
import com.example.administrator.tomorrow.model.MovieModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public class MoviePresenter implements MovieContract.MoviePresenter {
    public MovieContract.MovieView mMovieView;
    public MovieModel mMovieModel;
    public List<MovieListBean.SubjectsBean> mSubjectsBeanList = new ArrayList<>();
    public int start = 0;
    public int count = 4;

    public MoviePresenter(MovieContract.MovieView movieView) {
        this.mMovieView = movieView;
        this.mMovieModel = new MovieModel();
    }

    @Override
    public void dealMovieData() {
        mMovieView.showProgress();//显示进度条
        //V获取M传递过来的数据
        mMovieModel.dealMovie(start, count, new OnHttpCallBackListener<MovieListBean>() {
            @Override
            public void onSuccess(MovieListBean movieListBean) {
                mMovieView.hideProgress();
                List<MovieListBean.SubjectsBean> subjects = movieListBean.getSubjects();
                mSubjectsBeanList.addAll(subjects);
                mMovieView.showData(mSubjectsBeanList);
                mMovieView.setBottom(start - 5);
            }

            @Override
            public void onFailed(String errorMessage) {
                mMovieView.hideProgress();
                mMovieView.showInfo(errorMessage);
            }
        });
        start=start+4;
    }

    @Override
    public void loadMovieDataMore() {
            dealMovieData();
    }


}
