# RRMLib
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[ ![Download](https://api.bintray.com/packages/cicinnus0407/maven/RRMLib/images/download.svg) ](https://bintray.com/cicinnus0407/maven/RRMLib/_latestVersion)
</br>
Retrofit2+RxJava+MVP和常用工具类封装库
##用法
- 方式1
    - Android Studio
        ```
        com.cicinnus.RRMLib:rrmlib:newest-version'
        ```
- 方式2
    - Android Studio导入lib
    -
        ```
        compile project(':rrmlib')
        ```
## Retrofit网络请求用法
注：暂时没有为Retrofit提供类似okHttp的链式api，只提供了原生的注解形式的构建<br>，
    如果对retrofit注解的请求方式不熟悉请谨慎使用该库。

 - 1 .在Application中配置baseUrl<br>
 
        @param 1 OkHttpClient ,可根据自行需要进行配置（例如拦截Cookie和添加日志）
        @parma 2 BASE_URL ,必须填入，因为retorift在构建的时候必须传入一个baseUrl（格式为"http://192.168.191.1:8080/xxx/xx..."）
        RetrofitClient.initClient_BaseUrl(OkHttpManager.getInstance(), Api.BASE_URL);

        
 - 2 . get请求
 
        //接口定义       
        @GET("4/news/latest")
        Observable<ResultBean> load();
         
        RetrofitClient
                .getInstance()
                .create(Api.class)
                .load()
                .compose(SchedulersCompact.<ResultBean>applyIoSchedulers())
                .subscribe(new Consumer<ResultBean>() {
                    @Override
                    public void accept(@NonNull ResultBean resultBean) throws Exception {
                       //请求结束获取进行数据解析，
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                       //请求发生错误的时候调用
                       //封装部分错误信息
                      String msg = ExceptionHandle.handleException(throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                      //调用链结束
                    }
                });
## TODO ..期待更多的方法和功能
