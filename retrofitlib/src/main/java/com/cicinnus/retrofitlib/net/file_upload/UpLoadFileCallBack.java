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

    public void onProgressChange(long bytesWritten, long contentLength) {
        onProgress((int) (bytesWritten*100 / contentLength));
    }


    //上传成功的回调
    public abstract void onUpLoadSuccess(ResponseBody t);

    //上传你失败回调
    public abstract void onUpLoadFail(Throwable e);
    public abstract void onProgress(int progress);

}
