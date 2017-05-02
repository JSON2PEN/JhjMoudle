package com.jsonpen.jhjmoudle.Utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.jsonpen.jhjmoudle.App;
import com.jsonpen.jhjmoudle.AppInstence;
import com.jsonpen.jhjmoudle.R;


/**
 * 弹自定义吐司的工具类
 */
public class MyToastUtils {
    private static TextView mTextView;
    private static Toast toastStart;
    private static WindowManager wm;
    private static View toastRoot;

    public static void showToast( String message) {

        //为控件设置属性

        //Toast的初始化
        if (toastRoot==null){
            //加载Toast布局
            toastRoot = LayoutInflater.from(  AppInstence.appContext).inflate(R.layout.toast, null);
            //初始化布局控件
            mTextView = (TextView) toastRoot.findViewById(R.id.message);
        }
        mTextView.setText(message);
        if (toastStart==null){
            toastStart = new Toast( AppInstence.appContext);
            //获取屏幕高度
            wm = (WindowManager)   AppInstence.appContext.getSystemService(Context.WINDOW_SERVICE);
            int height = wm.getDefaultDisplay().getHeight();
            //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
            toastStart.setGravity(Gravity.TOP, 0, height / 2);
            toastStart.setDuration(Toast.LENGTH_SHORT);
            toastStart.setView(toastRoot);
        }
        toastStart.show();
    }

}
