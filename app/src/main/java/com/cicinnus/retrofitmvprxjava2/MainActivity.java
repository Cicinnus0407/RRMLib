package com.cicinnus.retrofitmvprxjava2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cicinnus.retrofitlib.base.BaseMVPActivity;
import com.cicinnus.retrofitlib.net.RetrofitClient;
import com.cicinnus.retrofitlib.net.RxApiManager;
import com.cicinnus.retrofitlib.net.file_upload.FileUploadObserver;
import com.cicinnus.retrofitlib.rx.SchedulersCompact;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainContract.IMainView {

    @BindView(R.id.content)
    TextView tvContent;

    private ProgressDialog progressDialog;
    private ProgressDialog progressNum;
    private Disposable d;
    //    private Disposable d;

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
        progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mPresenter.removeDisposableByTag();
                RxApiManager.getInstance().cancelByTag("d");
            }
        });
//        tvContent = (TextView) findViewById();
        progressNum = new ProgressDialog(mContext);
        progressNum.setMax(100);
        progressNum.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressNum.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                RxApiManager.getInstance().cancelByDisposable(d);
            }
        });
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
//        mPresenter.getMainData();
        progressDialog.show();

        RetrofitClient
                .getInstance()
                .create(Api.class)
                .get("http://httpbin.org/get?username=cicinnus&age=22")
                .compose(SchedulersCompact.<ResponseBody>applyIoSchedulers())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        tvContent.setText(responseBody.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        progressDialog.dismiss();
                        Log.e("错误信息----", "showError: " + throwable.getMessage());

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        progressDialog.dismiss();
                        Log.d("showContent----", "showContent: ");
                    }
                });
//        RxApiManager.getInstance().add("d", d);

    }

    public void download(View v) {
        progressNum.show();


//        d = RetrofitClient
//                .getInstance()
//                .downloadFile("http://192.168.191.1:8080/UploadFileServer/download?fileName=libmupdf.so",
//                        Environment.getExternalStorageDirectory().getAbsolutePath(), "libmupdf.so", new FileDownLoadObserver<File>() {
//                            @Override
//                            public void onDownLoadSuccess(File file) {
//                                progressNum.dismiss();
//                                Toast.makeText(mContext, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                            }
//
//                            @Override
//                            public void onDownLoadFail(Throwable throwable) {
//                                progressNum.dismiss();
//                                Toast.makeText(mContext, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onProgress(int progress, long total) {
//                                progressNum.setProgress(progress);
//                            }
//                        });
        d = RetrofitClient
                .getInstance()
                .upLoadFile("http://192.168.191.1:8080/UploadFileServer/upload", new File(Environment.getExternalStorageDirectory() + "/ddmsrec.mp4"), new FileUploadObserver<ResponseBody>() {
                    @Override
                    public void onUpLoadSuccess(ResponseBody responseBody) {
                        progressNum.dismiss();
                        try {
                            Toast.makeText(mContext, responseBody.string(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onUpLoadFail(Throwable e) {
                        progressNum.dismiss();
                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgress(int progress) {
                        progressNum.setProgress(progress);
                    }
                });
        RxApiManager.getInstance().add(d);
    }
}
