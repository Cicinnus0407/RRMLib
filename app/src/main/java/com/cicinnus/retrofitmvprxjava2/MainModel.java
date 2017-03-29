package com.cicinnus.retrofitmvprxjava2;

import com.cicinnus.retrofitlib.net.RetrofitClient;
import com.cicinnus.retrofitlib.utils.SchedulersCompact;

import io.reactivex.Observable;

/**
 * Created by Cicinnus on 2017/3/28.
 */

public class MainModel {
    public Observable<ResultBean> getMainData(){
        return RetrofitClient
                .getInstance()
                .create(Api.class)
                .load()
                .compose(SchedulersCompact.<ResultBean>applyIoSchedulers());

    }
}
