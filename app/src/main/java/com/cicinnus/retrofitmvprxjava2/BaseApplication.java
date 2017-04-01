package com.cicinnus.retrofitmvprxjava2;

import android.app.Application;

import com.cicinnus.retrofitlib.net.RetrofitClient;

/**
 * Created by Cicinnus on 2017/3/29.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient.initClient_BaseUrl(null,Api.BASE_URL);
    }
}
