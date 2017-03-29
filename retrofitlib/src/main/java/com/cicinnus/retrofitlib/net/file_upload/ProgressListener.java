package com.cicinnus.retrofitlib.net.file_upload;

/**
 * Created by Cicinnus on 2017/3/20.
 */

public interface ProgressListener {
    void onProgress(long hasWrittenLen, long totalLen, boolean finish);
}
