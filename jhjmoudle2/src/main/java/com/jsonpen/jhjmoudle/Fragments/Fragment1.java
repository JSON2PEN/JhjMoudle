package com.jsonpen.jhjmoudle.Fragments;

import android.content.Context;
import android.util.Base64;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.jsonpen.jhjmoudle.Basic.Notitle.BaseNoTitleFragment;
import com.jsonpen.jhjmoudle.R;
import com.jsonpen.jhjmoudle.Utils.MyDateUtils;
import com.jsonpen.jhjmoudle.ViewNeed.H5.MyWebView;
import com.jsonpen.jhjmoudle.ViewNeed.H5.WebFamelayout;
import com.jsonpen.jhjmoudle.databinding.FragmentWebBinding;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Created by 10398 on 2017/2/16.
 */

public class Fragment1 extends BaseNoTitleFragment<FragmentWebBinding> {


    private WebFamelayout myWebViewFamelayout;
    private MyWebView mWebView;
    public static String KEY_64 = "HcttEKey";
    public static String IV_64 = "HcttEIvv";

    @Override
    public int setContent() {
        return R.layout.fragment_web;
    }

    @Override
    protected void initData() {
        myWebViewFamelayout =bindingView.webView;
        mWebView = myWebViewFamelayout.mWebView;
        bindingView.titleFragmentbase.ibTitleBack.setVisibility(View.GONE);
        bindingView.titleFragmentbase.tvTitle.setText("Fragment基类");

        String UserId = "";
        mWebView.addJavascriptInterface(new JsInterface(getActivity()), "hctou");
        UserMix userMix = new UserMix(UserId, "asdsadsads", MyDateUtils.getCurrentDate("yyyy/MM/dd HH:mm:ss"));
        String content = new Gson().toJson(userMix);
        String params = null;
        try {
            params = encrypt(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用WebView组件显示网页
        String postData = null;
        try {
            postData = URLEncoder.encode(params, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        final String postdata = postData;
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                System.out.println("加载进度为：" + newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        myWebViewFamelayout.loadWebUrl("http://hcttest.cytx360.com/WebService/OrgComment/OrgList.aspx?mix=" + postdata);
    }



    @Override
    protected void onRefreshData() {

    }

    //Base64加密
    public static String encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(KEY_64.getBytes("ASCII"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(IV_64.getBytes("ASCII"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] buf = cipher.doFinal(message.getBytes("UTF-8"));
        String encrypedValue = Base64.encodeToString(buf, Base64.DEFAULT);
        return encrypedValue;
    }

    class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void goPlannerInfo(String param) {

//            Intent intent_bottom=new Intent(BaseApplication.mContext,PlanerInfoAct.class);
//            intent_bottom.putExtra("UserId",param);
//            mContext.startActivity(intent_bottom);

        }

        @JavascriptInterface
        public void login() {
//            Intent intent=new Intent(BaseApplication.mContext,LoginAct.class);
//            intent.putExtra("inType", "5");
//            mContext.startActivity(intent);

        }

        @JavascriptInterface
        public void setPageTitle(String param) {
        }
    }

    public class UserMix implements Serializable {
        public String UserId ;
        public String OpenId ;
        public String CreateDate ;

        public UserMix(String userId, String openId, String createDate) {
            UserId = userId;
            OpenId = openId;
            CreateDate = createDate;
        }

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String userId) {
            UserId = userId;
        }

        public String getOpenId() {
            return OpenId;
        }

        public void setOpenId(String openId) {
            OpenId = openId;
        }
    }
}
