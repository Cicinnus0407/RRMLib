package com.cicinnus.retrofitlib.net;

import io.reactivex.observers.DefaultObserver;

/**
 * 基础回调
 */

public abstract class BaseObserver<T> extends DefaultObserver<T> {


    @Override
    protected void onStart() {
        super.onStart();
        onSubscribeStart();
    }
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
    }

    @Override
    public void onComplete() {
        onSubscribeComplete();
    }


    /**
     * 可选开始请求的回到，可加入Loading显示
     */
    protected void onSubscribeStart(){

    }
    /**
     * 请求成功回调
     * @param t bean
     */
    public abstract void onSuccess(T t);

    /**
     * 请求失败的回调
     * @param t 异常
     */
    public abstract void onFail(Throwable t);

    /**
     * 整个Rx流结束，可用于隐藏Loading
     */
    protected void onSubscribeComplete(){

    }

}
