package com.cicinnus.retrofitlib.net;

import android.support.v4.util.ArrayMap;

import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 手动管理Retrofit请求,取消请求方法
 */

public class RxApiManager {

    private static RxApiManager instance = null;

    //键值对形式保存单个请求
    private ArrayMap<String, Disposable> maps;
    //请求队列
    private CompositeDisposable compositeDisposable;

    public static RxApiManager getInstance() {
        if (instance == null) {
            synchronized (RxApiManager.class) {
                if (instance == null) {
                    instance = new RxApiManager();
                }
            }
        }
        return instance;
    }

    private RxApiManager() {
        maps = new ArrayMap<>();
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * 将请求添加到管理队列中
     *
     * @param tag        单个请求的标记
     * @param disposable 请求调用链
     */
    public void add(String tag, Disposable disposable) {
        maps.put(tag, disposable);
        compositeDisposable.add(disposable);
    }
    /**
     * 移除队列中某个请求
     * @param tag 添加到队列时的标记
     */
    public void remove(String tag) {
        if (!maps.isEmpty()) {
            maps.remove(tag);
        }
    }

    public void removeAll() {
        if (!maps.isEmpty()) {
            maps.clear();
        }
        if(compositeDisposable.size()>0){
            compositeDisposable.clear();
        }
    }

    /**
     * 取消某个请求
     * @param tag
     */
    public void cancelByTag(String tag) {
        if (maps.isEmpty()) {
            return;
        }
        if (maps.get(tag) == null) {
            return;
        }
        if (compositeDisposable.size() > 0) {
            compositeDisposable.remove(maps.get(tag));
            maps.remove(tag);
        }
    }

    /**
     * 取消全部请求
     */
    public void cancelAll() {
        if (maps.isEmpty()) {
            return;
        }
        Set<String> keys = maps.keySet();
        for (String apiKey : keys) {
            cancelByTag(apiKey);
        }
    }
}

