package com.cicinnus.retrofitmvprxjava2;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cicinnus.retrofitlib.base.BaseMVPActivity;

public class MainActivity extends BaseMVPActivity<MainPresenter> implements MainContract.IMainView{



    TextView tvContent;

    private ProgressDialog progressDialog;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter getCorePresenter() {
        return new MainPresenter(mContext,this);
    }

    @Override
    protected void initEventAndData() {
        progressDialog = new ProgressDialog(mContext);
        tvContent = (TextView) findViewById(R.id.content);
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
        Log.e("错误信息----", "showError: "+errorMsg);
    }

    @Override
    public void addMainData(String data) {
        progressDialog.dismiss();
        tvContent.setText(data);
        Toast.makeText(mContext,"添加成功",Toast.LENGTH_SHORT).show();
    }

    public void getData(View view) {
       mPresenter.getMainData();
    }
}
