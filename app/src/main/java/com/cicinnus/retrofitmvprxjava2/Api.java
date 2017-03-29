package com.cicinnus.retrofitmvprxjava2;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Cicinnus on 2017/3/28.
 */

public interface Api   {

    String BASE_URL = "http://news-at.zhihu.com/api/";

    @GET("4/news/latest")
    Observable<ResultBean> load();

}
