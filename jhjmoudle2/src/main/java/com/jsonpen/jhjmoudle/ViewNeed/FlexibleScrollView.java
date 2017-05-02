package com.jsonpen.jhjmoudle.ViewNeed;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 弹性滑动的scrollview
 */
public class FlexibleScrollView extends ScrollView {  
  
    Context mContext;  
    private View mView;  
    private Rect mRect = new Rect();  
    private float touchY;  
  
    private static final int MAX_SCROLL_HEIGHT = 200;//弹性距离
    private static final float SCROLL_RATIO = 0.5f;//阻尼系数
    public FlexibleScrollView(Context context) {  
        super(context);  
        this.mContext = context;
        setHorizontalFadingEdgeEnabled(false);
    }  
  
    public FlexibleScrollView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        this.mContext = context;
        setHorizontalFadingEdgeEnabled(false);
    }  
  
    public FlexibleScrollView(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
        this.mContext = context;
        setHorizontalFadingEdgeEnabled(false);
    }  
  
    @Override  
    protected void onFinishInflate() {  
        if (getChildCount() > 0) {  
            this.mView = getChildAt(0);  
        }  
    }  
  
    @Override  
    public boolean onInterceptTouchEvent(MotionEvent arg0) {  
        if (arg0.getAction() == MotionEvent.ACTION_DOWN) {  
            touchY = arg0.getY();  
        }  
        return super.onInterceptTouchEvent(arg0);  
    }  
  
    @Override  
    public boolean onTouchEvent(MotionEvent ev) {  
        if (mView == null) {  
            return super.onTouchEvent(ev);  
        } else {  
            commonOnTouchEvent(ev);  
        }  
        return super.onTouchEvent(ev);  
    }  
  
    private void commonOnTouchEvent(MotionEvent ev) {  
        int action = ev.getAction();  
        switch (action) {  
        case MotionEvent.ACTION_DOWN:  
            touchY = ev.getY();
            break;  
        case MotionEvent.ACTION_UP:  
            if (isNeedAnimation()) {  
                animation();  
            }  
            break;  
        case MotionEvent.ACTION_MOVE:  
            final float preY = touchY;  
            float nowY = ev.getY();  
            int deltaY = (int) (preY - nowY);  
            touchY = nowY;  
            if (isNeedMove()) {  
                if (mRect.isEmpty()) {  
                    mRect.set(mView.getLeft(), mView.getTop(), mView.getRight(), mView.getBottom());  
                }  
                int offset = mView.getTop() - deltaY;  
                if (offset < MAX_SCROLL_HEIGHT && offset > -MAX_SCROLL_HEIGHT) {  
                    mView.layout(mView.getLeft(), mView.getTop() - (int) (deltaY * SCROLL_RATIO), mView.getRight(), mView.getBottom()  
                            - (int) (deltaY * SCROLL_RATIO));  
                }  
            }  
            break;  
        default:  
            break;  
        }  
    }  
  
    private boolean isNeedMove() {  
        int viewHight = mView.getMeasuredHeight();  
        int srollHight = getHeight();  
        int offset = viewHight - srollHight;  
        int scrollY = getScrollY();  
  
        if (scrollY == 0 || scrollY == offset) {  
            return true;  
        }  
        return false;  
    }  
  
    private boolean isNeedAnimation() {  
        return !mRect.isEmpty();  
    }  
  
    private void animation() {  
        TranslateAnimation ta = new TranslateAnimation(0, 0, mView.getTop(), mRect.top);  
        ta.setDuration(200);  
        mView.startAnimation(ta);  
        mView.layout(mRect.left, mRect.top, mRect.right, mRect.bottom);  
        mRect.setEmpty();  
  
    }
  
}  