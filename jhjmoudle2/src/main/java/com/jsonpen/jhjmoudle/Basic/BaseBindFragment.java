package com.jsonpen.jhjmoudle.Basic;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;
import com.jsonpen.jhjmoudle.R;
import com.jsonpen.jhjmoudle.databinding.FragmentBindBaseBinding;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.concurrent.TimeUnit;


/**
 * 是没有title的Fragment
 */
public abstract class BaseBindFragment<SV extends ViewDataBinding> extends RxFragment {

    // 布局view
    protected SV bindingView;
    // fragment是否显示了
    protected boolean mIsVisible = false;

    private ImageButton ibTitleBack;

    private  TextView tvTitle;

    private TextView tvTitleRight;

    private RelativeLayout rlContent;

    private LinearLayout llErrorRefresh;

    private  LinearLayout llProgressBar;

    private FragmentBindBaseBinding mBaseBinding ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bindingView = DataBindingUtil.inflate(getActivity().getLayoutInflater(), setContent(), null, false);
        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_bind_base, null, true);
        initID();
        rlContent.addView(bindingView.getRoot());
        return mBaseBinding.getRoot();
    }

    private void initID() {
        rlContent=mBaseBinding.rlContent;
        llErrorRefresh =mBaseBinding.llErrorRefresh;
        llProgressBar =mBaseBinding.llProgressBar;
        tvTitle =mBaseBinding.bindTitle.tvTitle;
        tvTitleRight =mBaseBinding.bindTitle.tvTitleRight;
        ibTitleBack=mBaseBinding.bindTitle.ibTitleBack;
    }


    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    protected void onInvisible() {
    }

    /**
     * 显示时加载数据,需要这样的使用
     * 注意声明 isPrepared，先初始化
     * 生命周期会先执行 setUserVisibleHint 再执行onActivityCreated
     * 在 onActivityCreated 之后第一次显示加载数据，只加载一次
     */
    protected void loadVisiData() {
    }

    protected void onVisible() {
        loadVisiData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 按钮防抖1秒内只执行一次
        llErrorRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                onRefreshData();
            }
        });
        initTitle();
        initData();

    }
    /**
     * 布局
     */
    public abstract int setContent();

    protected abstract void initData();

    private void initTitle() {
        ibTitleBack.setVisibility(View.GONE);
        tvTitleRight.setVisibility(View.GONE);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitleText(String title) {
        tvTitle.setText(title);
    }

    protected <T extends View> T getView(int id) {
        return (T) getView().findViewById(id);
    }


    /**
     * 加载失败后点击后的操作
     */
    protected abstract void onRefreshData();

    /**
     * 显示加载中状态
     */
    protected void showLoading() {
        if (llProgressBar.getVisibility() != View.VISIBLE) {
            llProgressBar.setVisibility(View.VISIBLE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
        if (llErrorRefresh.getVisibility() != View.GONE) {
            llErrorRefresh.setVisibility(View.GONE);
        }
    }

    /**
     * 加载完成的状态
     */
    protected void showContentView() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        if (llErrorRefresh.getVisibility() != View.GONE) {
            llErrorRefresh.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载失败点击重新加载的状态
     */
    protected void showError() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        if (llErrorRefresh.getVisibility() != View.VISIBLE) {
            llErrorRefresh.setVisibility(View.VISIBLE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }
}
