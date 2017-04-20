package com.cicinnus.retrofitmvprxjava2;

import android.app.ProgressDialog;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cicinnus.retrofitlib.base.BaseMVPActivity;
import com.cicinnus.retrofitlib.net.RetrofitClient;
import com.cicinnus.retrofitlib.net.file_download.FileDownLoadObserver;

import java.io.File;

public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainContract.IMainView {


    TextView tvContent;

    private ProgressDialog progressDialog;
    private ProgressDialog progressNum;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getCorePresenter() {
        return new MainPresenter(mContext, this);
    }

    @Override
    protected void initEventAndData() {
        progressDialog = new ProgressDialog(mContext);
        tvContent = (TextView) findViewById(R.id.content);
        progressNum = new ProgressDialog(mContext);
        progressNum.setMax(100);
        progressNum.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void showContent() {
        progressDialog.dismiss();
        Log.d("showContent----", "showContent: ");
    }

    @Override
    public void showError(String errorMsg) {
        progressDialog.dismiss();
        Log.e("错误信息----", "showError: " + errorMsg);
    }

    @Override
    public void addMainData(String data) {
        progressDialog.dismiss();
        tvContent.setText(data);
        Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
    }

    public void getData(View view) {
        mPresenter.getMainData();

    }

    public void download(View v) {
        progressNum.show();

        RetrofitClient
                .getInstance()
                .downloadFile("url",
                        Environment.getExternalStorageDirectory().getAbsolutePath(), "test.txt", new FileDownLoadObserver<File>() {
                            @Override
                            public void onDownLoadSuccess(File file) {
                                progressNum.dismiss();
                                Toast.makeText(mContext, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onDownLoadFail(Throwable throwable) {
                                progressNum.dismiss();
                                Toast.makeText(mContext, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onProgress(int progress,long total) {
                                progressNum.setProgress(progress);
                            }
                        });
    }
}
