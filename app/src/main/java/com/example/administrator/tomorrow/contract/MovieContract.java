package com.example.administrator.tomorrow.contract;

import com.example.administrator.tomorrow.bean.MovieListBean;
import com.example.administrator.tomorrow.listener.OnHttpCallBackListener;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public class MovieContract {
    public interface MovieModel {
        void dealMovie(int start, int count, OnHttpCallBackListener<MovieListBean> onHttpCallBackListener);
    }

    public interface MoviePresenter {
        void dealMovieData();//M传递数据  V接受数据的地方

        void loadMovieDataMore();//加载更多的数据的地方
    }

    /**
     * 电影的View
     */
    public interface MovieView {
        void setBottom(int lastIndex);//实现换页效果

        void showProgress();

        void hideProgress();

        void showInfo(String info);//提示信息

        void showData(List<MovieListBean.SubjectsBean> subjectsBeanList);//网络获取的数据
    }
}
