package com.jsonpen.jhjmoudle.ViewNeed.H5;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jsonpen.jhjmoudle.App;
import com.jsonpen.jhjmoudle.AppInstence;
import com.jsonpen.jhjmoudle.R;


/**
 * Created by user on 2016/10/27.
 */
public class WebFamelayout extends FrameLayout implements SwipeRefreshLayout
        .OnRefreshListener {
    /*private ArrayList<String> webStack = new ArrayList<>();//webview的栈*/
    public MyWebView mWebView;//webview组件
    public SwipeRefreshLayout mSwipeRefreshLayout;//下拉刷新布局
    private String mUrl;//用户加载的链接
    private String mHtmlSource;//用户加载的html源码
    public WebFamelayout(Context context) {
        this(context, null);
    }

    public WebFamelayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebFamelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    //******************************************

    /**
     * 加载html代码
     *
     * @param htmlData
     */
    public void loadHtmlSource(String htmlData) {
        this.mHtmlSource = htmlData;
        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");//设置默认为utf-8
        mWebView.loadData(htmlData, "text/html; charset=UTF-8", null);
    }

    /**
     * 外部调用的入口
     * @param mUrl
     */
    public void loadWebUrl(String mUrl) {
        mWebView.loadUrl(mUrl);
    }


    /**
     * 释放webview
     */
    public void release() {
        mWebView.clearCache(true);
        mWebView.clearHistory();
        this.mWebView.destroy();
    }


    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.webview_base, this);
        mWebView = (MyWebView) this.findViewById(R.id.id_webview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.id_swipe_refresh_layout);
        initSwipeRefreshLayout();
        initWebView();

    }


    /**
     * webview的设置
     */
    private void initWebView() {
        //对webview是否处于顶部进行监听，解决webview往下拉后无法往上拉的冲突（和SwipeRefreshLayout冲突）
        mWebView.setOnCustomScrollChanged(new MyWebView.IWebViewScroll() {
            @Override
            public void onTop() {
                mSwipeRefreshLayout.setEnabled(true);
            }

            @Override
            public void notOnTop() {
                mSwipeRefreshLayout.setEnabled(false);
            }
        });
        mWebView.requestFocus();
        WebSettings webSettings = mWebView.getSettings();
        //设置 缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAppCacheEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        //设置 缓存模式
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                          beforeLoading(view,url);
                                          if (url.contains("www.baidu.com")) {//定点拦截的url
                                          } else {
                                              return false;
                                          }
                                          return true;
                                      }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mSwipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                mSwipeRefreshLayout.setRefreshing(false);
                loadError();
            }
        });


    /*    mWebView.setVerticalScrollBarEnabled(false);//设置无垂直方向的scrollbar
        mWebView.setHorizontalScrollBarEnabled(false);//设置无水平方向的scrollbar
         WebSettings settings = mWebView.getSettings();
        settings.setSupportZoom(false); // 支持缩放
        settings.setBuiltInZoomControls(false); // 启用内置缩放装置
        // 设置WebView可触摸放大缩小
        settings.setBuiltInZoomControls(false);
       /* // 点击后退按钮,让WebView后退*/
    }


    /**
     * webview加载失败
     */
    public void loadError() {

    }

    /**
     * 加载url
     * @param view
     * @param url
     */

    public void beforeLoading(WebView view, String url) {
    }

    /**
     * 设置下拉刷新样式及监听
     */
    private void initSwipeRefreshLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }


    /**
     * 下拉刷新的监听
     */
    @Override
    public void onRefresh() {
        if (  AppInstence.appContext.isConnected()) {//有网络才允许重新刷新
            mWebView.reload();
        } else {
            mSwipeRefreshLayout.setRefreshing(false);//无网络
            Toast.makeText(getContext(),"网络未连接", Toast.LENGTH_SHORT).show();
        }
    }

    //隐藏刷新控件
    public void hintRefresh() {
//        mSwipeRefreshLayout.setRefreshing(false);
        mSwipeRefreshLayout.setEnabled(false);
    } //隐藏刷新控件

    public void showRefresh() {
//        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setEnabled(true);
    }


}
