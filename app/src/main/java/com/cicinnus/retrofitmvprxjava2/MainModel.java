package com.cicinnus.retrofitmvprxjava2;

import android.util.Log;

import com.cicinnus.retrofitlib.net.RetrofitClient;
import com.cicinnus.retrofitlib.utils.SPUtils;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Cicinnus on 2017/3/28.
 */

public class MainModel {

    /**
     * 联网获取数据
     *
     * @return
     */
    public Observable<ResultBean> getMainData() {
        return RetrofitClient
                .getInstance()
                .create(Api.class)
                .load()
                .doOnNext(new Consumer<ResultBean>() {
                    @Override
                    public void accept(@NonNull ResultBean resultBean) throws Exception {
                        Log.d("doOnNext--", "accept: " + "保存数据到缓存");
                        SPUtils.getInstance(BaseApplication.getInstance(), "cache")
                                .putString("data", "缓存的数据")
                                .apply();
                    }
                });

    }

    public String getCacheData() {
        return SPUtils.getInstance(BaseApplication.getInstance(), "cache")
                .getString("data","");
    }
}
