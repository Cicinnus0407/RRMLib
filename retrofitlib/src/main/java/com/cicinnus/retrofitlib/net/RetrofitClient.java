package com.cicinnus.retrofitlib.net;

import com.cicinnus.retrofitlib.net.file_download.FileDownLoadObserver;
import com.cicinnus.retrofitlib.net.file_upload.FileUploadObserver;
import com.cicinnus.retrofitlib.net.file_upload.UploadFileRequestBody;
import com.cicinnus.retrofitlib.utils.SchedulersCompact;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Cicinnus on 2017/3/28.
 */

public class RetrofitClient {
    private static RetrofitClient mInstance;
    private static Retrofit retrofit;
    private OkHttpClient mOkHttpClient;
    private static String mBaseUrl;

    private RetrofitClient(OkHttpClient okHttpClient, String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient != null ? okHttpClient : new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    /**
     * 配置自定义的OkHttpClient
     *
     * @param okHttpClient
     * @return
     */
    public static RetrofitClient initClient_BaseUrl(OkHttpClient okHttpClient, @NonNull String baseUrl) {
        mBaseUrl = baseUrl;
        if (mInstance == null) {
            synchronized (RetrofitClient.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitClient(okHttpClient, baseUrl);
                }
            }
        }
        return mInstance;
    }


    /**
     * 获取Retrofit的实例
     *
     * @return
     */
    public static RetrofitClient getInstance() {
        if (mBaseUrl == null) {
            throw new RuntimeException("Please initialize Your \"BaseUrl\" in Application before use");
        }
        if (mInstance == null) {
            throw new RuntimeException("Please initialize Your \"RetrofitClient\" in Application before use");
        }
        return mInstance;
    }

    /**
     * 构建请求
     *
     * @param clz 请求接口
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> clz) {
        return retrofit.create(clz);
    }


    /**
     * 单上传文件的封装
     *
     * @param url                   完整的接口地址
     * @param file                  需要上传的文件
     * @param fileUploadObserver  上传监听回调
     */
    public void upLoadFile(String url, File file, FileUploadObserver<ResponseBody> fileUploadObserver) {
        UploadFileRequestBody uploadFileRequestBody = new UploadFileRequestBody(file, fileUploadObserver);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), uploadFileRequestBody);
        create(BASE_API.class)
                .uploadFile(url, part)
                .compose(SchedulersCompact.<ResponseBody>applyIoSchedulers())
                .subscribe(fileUploadObserver);
    }


    /**
     * 下载单文件，可以是大文件，该方法不支持断点下载
     *
     * @param url                  文件地址
     * @param destDir              存储文件夹
     * @param fileName             存储文件名
     * @param fileDownLoadObserver 监听回调
     */
    public void downloadFile(@NonNull String url, final String destDir, final String fileName, final FileDownLoadObserver<File> fileDownLoadObserver) {
        create(BASE_API.class)
                .downLoadFile(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(@NonNull ResponseBody responseBody) throws Exception {
                        return fileDownLoadObserver.saveFile(responseBody, destDir, fileName);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileDownLoadObserver);
    }

}
