package com.jsonpen.jhjmoudle.ViewNeed;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * 没有滑动的recycleview的layoutmanager
 */

public class NoScrollRecycleManager extends LinearLayoutManager {
    private boolean isScrollEnabled = false;//默认为取消滑动的可以通过

    public NoScrollRecycleManager(Context context) {
        super(context);
    }

    public NoScrollRecycleManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public NoScrollRecycleManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //可以通过这个方法设置可以滑动
    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }

    @Override
    public boolean canScrollHorizontally() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollHorizontally();
    }
}
