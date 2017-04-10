package com.cicinnus.retrofitmvprxjava2;

import android.app.Application;

import com.cicinnus.retrofitlib.net.RetrofitClient;

/**
 * Created by Cicinnus on 2017/3/29.
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RetrofitClient.initClient_BaseUrl(null, Api.BASE_URL);
    }
}
