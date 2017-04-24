package com.cicinnus.retrofitlib.base;

/**
 * Created by Cicinnus on 2017/3/28.
 */

public interface ICoreLoadingView {
    void showLoading();
    void showContent();
    void showError(String errorMsg);
}
