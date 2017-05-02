package com.jsonpen.jhjmoudle.Utils;

import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.jsonpen.jhjmoudle.Basic.BaseBindActivity;
import com.jsonpen.jhjmoudle.Basic.BaseBindFragment;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

/**
 * 主要集成一些常用rxview的点击事件之类的!
 */

public class RxUtils {

    //按钮的防误触点击操作 activity 0.3s内不重复
    public static void click(View view, BaseBindActivity svBaseBindAct, final RxcallBack rxcallBack){
        RxView.clicks(view).compose(svBaseBindAct.bindToLifecycle()).throttleFirst(300, TimeUnit.MILLISECONDS).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                rxcallBack.toDo();
            }
        });
    } //按钮的防误触点击操作 fragment
    public static void click(View view, BaseBindFragment svBaseBindfragment, final RxcallBack rxcallBack){
        RxView.clicks(view).compose(svBaseBindfragment.bindToLifecycle()).throttleFirst(300, TimeUnit.MILLISECONDS).subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                rxcallBack.toDo();
            }
        });
    }
    public  interface RxcallBack{
        void toDo();
    }
}
