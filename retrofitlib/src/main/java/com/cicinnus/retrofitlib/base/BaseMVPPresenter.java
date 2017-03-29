package com.cicinnus.retrofitlib.base;

import android.app.Activity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * BaseMVPPresenter，封装调用链
 */

public class BaseMVPPresenter<T> implements ICorePresenter {
    private static final String TAG = "BaseMVPPresenter";
    protected Activity mActivity;//与Activity交互
    protected T mView;//Presenter持有的View
    private CompositeDisposable disposables2Stop;// 管理Stop取消订阅者者
    private CompositeDisposable disposables2Destroy;// 管理Destroy取消订阅者者



    public BaseMVPPresenter(Activity activity, T view) {
        this.mActivity = activity;
        this.mView = view;
    }


    //将调用链加入，直到onStop触发
    protected void addSubscribeUntilStop(Disposable disposable) {
        if (disposables2Stop == null) {
            disposables2Stop = new CompositeDisposable();
        }
        disposables2Stop.add(disposable);
    }

    //onStop触发停止调用链
    private void unSubscribeWhenStop() {
        if (disposables2Stop != null) {
            disposables2Stop.dispose();
            disposables2Stop = null;
        }
    }

    //将调用链 加入，直到调用onDestroy
    protected void addSubscribeUntilDestroy(Disposable disposable) {
        if (disposables2Destroy == null) {
            disposables2Destroy = new CompositeDisposable();
        }
        disposables2Destroy.add(disposable);
    }

    /**
     * onDestroy触发停止调用链
     */
    private void unSubscribeWhenDestroy() {
        if (disposables2Destroy != null) {
            disposables2Destroy.dispose();
            disposables2Destroy = null;
        }
    }

    /**
     * 移除一个或多个调用方法
     */
    protected void removeDisposable(Disposable... disposables) {
        if (disposables2Destroy != null) {
            for (Disposable d : disposables) {
                disposables2Destroy.remove(d);
            }
        }
        if (disposables2Stop != null) {
            for (Disposable d : disposables) {
                disposables2Stop.remove(d);
            }
        }
    }

    @Override
    public void onViewStop() {
        unSubscribeWhenStop();
    }

    @Override
    public void onViewDestroy() {
        unSubscribeWhenDestroy();
    }
}
