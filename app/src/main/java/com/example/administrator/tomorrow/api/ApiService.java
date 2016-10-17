package com.example.administrator.tomorrow.api;

import com.example.administrator.tomorrow.bean.ArticleListBean;
import com.example.administrator.tomorrow.bean.MovieListBean;
import com.example.administrator.tomorrow.bean.PhotoClassifyBean;
import com.example.administrator.tomorrow.bean.PhotoListBean;
import com.example.administrator.tomorrow.bean.PhotoShowBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * api接口
 */
public interface ApiService {

    /**
     * 查询豆瓣排名前250的电影
     *
     * @param start 从第几部开始
     * @param count 几页(一页有12部)
     * @return
     */
    @GET("v2/movie/top250")
    Observable<MovieListBean> getMovies(@Query("start") int start, @Query("count") int count);

    /**
     * @param Typepath
     * @param pageCount
     * @param page
     * @return
     */
    @GET("data/{Typepath}/{pageCount}/{page}")
    Observable<ArticleListBean> getArticleData(@Path("Typepath") String Typepath, @Path("pageCount") int pageCount, @Path("page") int page);

    @GET("list")
    Call<PhotoListBean> getPhotoList(@Header("apikey") String apikey, @Query("page") int page, @Query("rows") int rows);

    @GET("classify")
    Observable<PhotoClassifyBean> getPhotoClassify(@Header("apikey") String apikey);

    @GET("show")
    Observable<PhotoShowBean> getPhotoShow(@Header("apikey") String apikey, @Query("id") int id);
}
