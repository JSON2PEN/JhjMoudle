package com.jsonpen.jhjmoudle.NetApi.NetObserver;





import com.jsonpen.jhjmoudle.NetApi.AppCreatRetrofit;
import com.jsonpen.jhjmoudle.NetApi.BaseURL;
import com.jsonpen.jhjmoudle.NetApi.NetInterface.BaseObserver;
import com.jsonpen.jhjmoudle.NetApi.ProjectNetApi;

import java.util.Map;

import rx.Observable;

public class NetObserverFactory extends BaseObserver {
    private Observable observable;

    public Map<String, String> map;

    //初始化api
    private static ProjectNetApi api = AppCreatRetrofit.getNewsRetrofit(ProjectNetApi.class, BaseURL.URL_SERVER);

    //初始化banner的baseurl
    private static ProjectNetApi bannerAPI =AppCreatRetrofit.getNewsRetrofit(ProjectNetApi.class, BaseURL.URL_BANNER);


    public NetObserverFactory() {
    }

    public NetObserverFactory(Map<String,String> map){
        super();
        this.map = map;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return observable;
    }

    /**
     * 这里写所有的调用方法
     * @param userid
     * @return
     */
    public NetObserverFactory testAP(String userid ){
        observable = api.testApi(userid);
        return this;
    }
    /**
     * banner图的url
     */
    public NetObserverFactory requestBanner( ){
        observable = bannerAPI.getFrontpage();
        return this;
    }
}
