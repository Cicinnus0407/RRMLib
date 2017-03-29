package com.cicinnus.retrofitmvprxjava2;

import com.cicinnus.retrofitlib.base.BaseMVPActivity;
import com.cicinnus.retrofitlib.base.ICorePresenter;

/**
 * Created by Cicinnus on 2017/3/29.
 */

public class BaseActivity<T extends ICorePresenter> extends BaseMVPActivity  {
    protected T mPresenter;

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    protected ICorePresenter getCorePresenter() {
        return getPresenter();
    }

    protected ICorePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void initEventAndData() {

    }
}
