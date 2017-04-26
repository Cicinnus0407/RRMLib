package com.cicinnus.retrofitlib.net;

/**
 * 基础回调
 */

public abstract class BaseObserver<T>  {


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
