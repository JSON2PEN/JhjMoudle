package com.jsonpen.jhjmoudle.Basic.Base;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;

import com.jsonpen.jhjmoudle.R;
import com.jsonpen.jhjmoudle.Utils.statusbar.StatusBarUtil;
import com.trello.rxlifecycle.components.support.RxFragmentActivity;


/**
 * mvvm 的databinding的基类activity,没有任何公共布局的activity
 */
public abstract class BaseActivity<SV extends ViewDataBinding> extends RxFragmentActivity {

    // 布局view
    protected SV bindingView;

    /**
     * 这个方法可以省去findviewbyid;
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResID());
        initView();
        initData();
    }

    protected abstract int getResID();

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);
        getWindow().setContentView(bindingView.getRoot());
        // 设置透明状态栏
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorAccent), 0);
    }

    protected abstract void initView();

    protected abstract void initData();

    /**
     * 字体不受系统设置影响
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * Bundle保存activity实例
     */
    protected void initBundle(Bundle bundle){
    }
    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    protected void startActivityForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

}
