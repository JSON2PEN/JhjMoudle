package com.jsonpen.jhjmoudle.NetApi;




import com.jsonpen.jhjmoudle.Beans.BannerBean;
import com.jsonpen.jhjmoudle.Beans.GoldsInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by shiqiang on 2016/7/11.
 *
 * 1.导入retrofit的依赖库
 *
 *
 *
 */
public interface ProjectNetApi {


    /**
     * 首页轮播图
     */
    @GET("/frontpage/frontpage")
    Observable<BannerBean> getFrontpage();

/**
     * 测试api
     */
    @GET(BaseURL.URL_SERVER+"Service/ScoresGoldsService.svc/GetGoldsList/{UserId}")
    Observable<List<GoldsInfo>> testApi(@Path("UserId") String userId);

    /**
     * 上传一张图片
     * @param description
     * @param imgs
     * @return
     *//*
    @Multipart
    @POST("/upload")
    Call<String> uploadImage(@Part("fileName") String description,
                             @Part("file\"; filename=\"image.png\"") RequestBody imgs);


    *//**
     * 通过get请求请求数据类型 repos为网址后缀的repos/owner/repo/contributors
     * @path 为{owner}的替代
     * @param owner
     * @param repo
     * @return 是请求到的response体可以转化为json数据
     *//*
    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsBySimpleGetCall(@Path("owner") String owner, @Path("repo") String repo);

    *//**
     * 进行post请求
     * @param owner
     * @param repo
     * @return
     *//*

     @POST("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> contributorsBySimpleGetCallPost(@Path("owner") String owner, @Path("repo") String repo);





    *//**
     * 通过get 将请求的数据直接转化为javabean对象
     * @param owner
     * @param repo
     * @return
     *//*
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributorsByAddConverterGetCall(@Path("owner") String owner, @Path("repo") String repo);


    *//**
     * 通过get请求携带网络请求头
     * @param owner
     * @param repo
     * @return
     *//*
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: RetrofitBean-Sample-App",
            "name:ljd"
    })
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributorsAndAddHeader(@Path("owner") String owner, @Path("repo") String repo);


    *//**
     * 通过get请求进行网络请求,携带键值对表单数据 q = shi ;
     * @param owner
     * @param time
     * @param page
     * @param per_Page
     * @return
     *//*
    @GET("search/repositories")
    Call<RetrofitBean> queryRetrofitByGetCall(@Query("q") String owner,
                                              @Query("since") String time,
                                              @Query("page") int page,
                                              @Query("per_page") int per_Page);


    *//**
     * FormUrlEncoded 表单Post请求的标示
     * @param owner
     * @param time
     * @param page
     * @param per_Page
     * @return
     *//*
    @FormUrlEncoded
    @POST("search/repositories")
    Call<RetrofitBean> queryRetrofitByPostCall(@Query("q") String owner,
                                               @Query("since") String time,
                                               @Query("page") int page,
                                               @Query("per_page") int per_Page);


    *//**
     * 封装成Map集合病使用QueryMap添加进来
     * @param map
     * @return
     *//*
    @GET("search/repositories")
    Call<RetrofitBean> queryRetrofitByGetCallMap(@QueryMap Map<String, String> map);


    *//**
     * 通过get请求封装 Rxjava 得到数据类型使用订阅者模式不在封装为Call 而是Observable
     * @param owner
     * @param repo
     * @return
     *//*
    @GET("repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> contributorsByRxJava(@Path("owner") String owner,
                                                       @Path("repo") String repo);*/


}
