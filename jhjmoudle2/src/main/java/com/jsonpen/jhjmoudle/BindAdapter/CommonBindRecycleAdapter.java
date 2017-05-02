package com.jsonpen.jhjmoudle.BindAdapter;

import android.databinding.ViewDataBinding;

import java.util.List;

/**
 * 单种条目的item的recycleview的公共adapter;
 */

public abstract class CommonBindRecycleAdapter<T, sv extends ViewDataBinding> extends RecyclerBaseAdapter<T, sv> {
    private int mLayoutId;
    private int brId;
    public CommonBindRecycleAdapter(List<T> mData,int mLayoutId,int brId) {
        super(mData);
        this.mLayoutId =mLayoutId;
        this.brId =brId;
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return mLayoutId;
    }

    @Override
    public int getVariableId(int viewType) {
        return brId;
    }

    @Override
    public int getItemTypePosition(int position) {
        return 0;
    }


    @Override
    public int getStartMode() {
        return 0;
    }

    @Override
    public void bindCustomData(RecyclerViewHolder holder, int position, T item) {

    }
}
