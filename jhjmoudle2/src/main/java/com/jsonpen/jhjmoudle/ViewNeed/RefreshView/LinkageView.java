package com.jsonpen.jhjmoudle.ViewNeed.RefreshView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * Created by 10398 on 2017/2/14.
 */

public class LinkageView extends HorizontalScrollView {
    private View mView;

    public LinkageView(Context context) {
        super(context);
    }

    public LinkageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinkageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //设置控件滚动监听，得到滚动的距离，然后让传进来的view也设置相同的滚动具体
        if (mView != null) {
            mView.scrollTo(l, t);
        }
    }

    /**
     * 设置跟它联动的view
     *
     * @param view
     */
    public void setScrollView(View view) {
        mView = view;
    }
}
