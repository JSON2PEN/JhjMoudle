package com.jsonpen.jhjmoudle;

import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsonpen.jhjmoudle.ViewNeed.CustomProgress;
import com.jsonpen.jhjmoudle.receiver.NetworkStateReceiver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * 项目的application
 * 项目描述:
 *      1,baselibrary,是一个关于recycleview的anim,layout,上拉加载,下拉刷新各种头布局,根布局,以及recycle的适配器,多类型条目复用;
 *      2,zoomlibrary,是一个集图片查看,图片缩放于一体的类库
 */
public class App extends Application {

    public static Context mContext;
    private static ProgressDialog progressDialog;
    private static App app;
    private static CustomProgress customProgress;

    private boolean isEnablaWifi;
    private boolean isWifi;
    private boolean isMobile;
    private boolean isConnected;
    private NetworkStateReceiver networkStateReceiver;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //监听网络状态的变化
        registerNetworkState();
        app = this;
       /* //初始化异常管理工具
        Recovery.getInstance()//界面处理在activity_recovery界面中;
                .debug(true)//关闭后 在错误统一管理页面不显示异常数据
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(MainActivity.class)//恢复页面
                .init(this);*/
    }
    public static App getInstance() {
        return app;
    }
    public static Context getApplication() {
        return mContext;
    }
    //提示对话框
    public static void baseDialog(Context context,String title) {
        final  Dialog  dialog = new Dialog(context, R.style.SingleBtnDialog);
        View dialog_view = View.inflate(context,
                R.layout.dialog_title, null);
        dialog.setContentView(dialog_view);
        TextView  tv_content = (TextView) dialog_view.findViewById(R.id.tv_content);
        RelativeLayout rl_ok = (RelativeLayout) dialog_view.findViewById(R.id.rl_ok);
        tv_content.setText(title);
        rl_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 加载进度dialog
     * @param msg
     * @param context
     */
    public static void showProgressDialog(String msg, Context context) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        try {
            progressDialog.show();
        } catch (WindowManager.BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 加载自定义的progressdialog
     *
     */
    public static void showMyPogressDialog(Context context,String msg ){
        customProgress = new CustomProgress(context,msg);
        customProgress.show();


    }
    /**
     * 隐藏进度dialog
     */
    public static void disMissDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void registerNetworkState() {

        networkStateReceiver = new NetworkStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        registerReceiver(networkStateReceiver, filter);
    }

    /**
     * WiFi状态
     *
     * @param isEnable
     */
    public void setEnablaWifi(boolean isEnable) {

        isEnablaWifi = isEnable;
    }

    /**
     * 连接的是否是WiFi
     *
     * @param isWifi
     */
    public void setWifi(boolean isWifi) {

        this.isWifi = isWifi;

    }

    /**
     * 连接的是否是移动数据
     *
     * @param isMobile
     */
    public void setMobile(boolean isMobile) {
        this.isMobile = isMobile;
    }

    /**
     * 网络是否连接
     *
     * @param isConnected
     */
    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public boolean isConnected() {
        return isWifi || isMobile;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public boolean isWifi() {
        return isWifi;
    }

    /**
     * WiFi是否打开
     *
     * @return
     */
    public boolean isEnablaWifi() {
        return isEnablaWifi;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}
