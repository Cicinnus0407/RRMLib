package com.cicinnus.retrofitmvprxjava2;

import com.cicinnus.retrofitlib.base.ICoreLoadingView;

/**
 * Created by Cicinnus on 2017/3/28.
 */

public class MainContract {
    public interface IMainView extends ICoreLoadingView{
        void addMainData(String string);
    }
    public interface IMainPresenter{
        void getMainData();
    }
}
