package com.cicinnus.retrofitmvprxjava2;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Cicinnus on 2017/3/28.
 */

public interface Api   {

    String BASE_URL = "http://news-at.zhihu.com/api/";

    @GET("4/news/latest")
    Observable<ResultBean> load();

    @GET
    Observable<ResponseBody> get(@Url String url);

    @GET("http://192.168.191.1:8080/api/app/checkUser.do")
    Observable<ResponseBody> login(@Query("userName")String userName, @Query("password")String password);



}
