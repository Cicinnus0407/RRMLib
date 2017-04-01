package com.cicinnus.retrofitlib.net.file_download;

/**
 * Created by Cicinnus on 2017/4/1.
 */

public interface ProgressResponseListener {
    void onResponseProgress(long bytesRead, long contentLength);
}
