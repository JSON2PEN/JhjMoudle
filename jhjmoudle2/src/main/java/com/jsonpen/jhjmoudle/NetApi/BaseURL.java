package com.jsonpen.jhjmoudle.NetApi;

/**
 * 用户公共的请求头，图片请求头等
 */
public interface BaseURL {
    /**
     * banner图的请求前缀;
     */
    String URL_BANNER = "http://api.dongting.com";
    /**
     * 这个类是为了记录联网url,以及请求码,返回码的;http://192.168.1.102:8080/diaochan/indexAppController/home.action?userName=13501383557
     */
    String URL_SERVER ="http://hcttest.cytx360.com/";
    /**
     * 首页请求
     */
    String URL_HOME = URL_SERVER+"indexAppController/home.action?userName=13501383557";
    /**
     * 图片请求头
     */
     String URL_IMAGE_HEAD ="http://101.201.63.64/upload/slide/";
    /**
     * 商品图片请求头
    */
     String URL_GOODSIMAGE_HEAD ="http://101.201.63.64/upload/goods/";
    /**
     * 商品图片请求头
    */
     String URL_GOODSIMAGE_ACTIVE ="http://101.201.63.64/upload/active/";

    /**
     * 评价商品图片 http://101.201.63.64/upload/
     */
     String URL_GOODS_TICKLING_HEAD ="http://101.201.63.64/upload/";

    /**
     * 水票商品图片 http://101.201.63.64/upload/piao/png-201605140923055-81.png
     */
    String URL_GOODS_WATER_TICKETS_HEAD ="http://101.201.63.64/upload/piao/";

    /**
     * 购物车活动图片
     */
     String URL_GOODS_WATER_RECEIVEING_HEAD ="http://101.201.63.64/upload/dict/";

}
