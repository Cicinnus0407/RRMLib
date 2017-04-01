package com.cicinnus.retrofitlib.net.file_upload;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 上传的回调
 */

public abstract class UpLoadFileCallBack implements Callback<ResponseBody> {
    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        onUpLoadSuccess(response.body());
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        onUpLoadFail(t);
    }

    //上传成功的回调
    public abstract void onUpLoadSuccess(ResponseBody t);

    //上传失败回调
    public abstract void onUpLoadFail(Throwable e);

    //上传进度监听
    public abstract void onProgress(int progress);

}
