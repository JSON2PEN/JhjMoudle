package com.jsonpen.jhjmoudle.NetApi;

import android.content.Context;
import android.text.TextUtils;

import com.jsonpen.jhjmoudle.App;
import com.jsonpen.jhjmoudle.AppInstence;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppCreatRetrofit {


    private static volatile Retrofit retrofit;

    private AppCreatRetrofit(){

    }

    public static <T> T getNewsRetrofit(Class<T> clazz, String baseUrl) {

        if (TextUtils.isEmpty(baseUrl)) {
            throw new IllegalArgumentException("BaseUrl cannot be null");
        }
//



        if(retrofit == null){

            synchronized (AppCreatRetrofit.class){

                if (retrofit == null){

                    retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl) //刚刚添加进来的请求头
                            .client(getOKHTTP())
//                                .client(getCacheOkHttpClient(App.getApplication()))  //使用缓存,Interceptor截获每次网络请求用于缓存数据
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //添加Rxjava


                            //添加Gson解析
                            .addConverterFactory(GsonConverterFactory.create())

                            .build();

                }

            }

        }

        return retrofit.create(clazz);
    }

    /**
     * 判断是否需要缓存数据,默认为false,可以用单利在每次请求之前设置值
     */
    private static OkHttpClient getOKHTTP() {
        //是否有缓冲
//        if (App.getIsChach()){
        if (false){
            return   getCacheOkHttpClient(AppInstence.appContext.mContext);

        }else {

            return  getOKHttpClient() ;


        }

    }

    private static OkHttpClient getOKHttpClient(){
        return new OkHttpClient.Builder()
//                .connectTimeout(20000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
    }


    private static OkHttpClient getCacheOkHttpClient(final Context context){
        final File baseDir = context.getCacheDir();
        final File cacheDir = new File(baseDir, "HttpResponseCache");
//        Timber.e(cacheDir.getAbsolutePath());
        Cache cache = new Cache(cacheDir, 10 * 1024 * 1024);   //缓存可用大小为10M

        //Retrofit网络请求时候的截获类Interceptor
        Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if(!AppInstence.appContext.getInstance().isConnected()){
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response originalResponse = chain.proceed(request);
                if (AppInstence.appContext.getInstance().isConnected()) {
                    int maxAge = 60;                  //在线缓存一分钟
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();

                } else {
                    int maxStale = 60 * 60 * 24 * 4 * 7;     //离线缓存4周
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)

                            .build();
                }


            }
        };


        return new OkHttpClient.Builder()
                .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
    }


}
