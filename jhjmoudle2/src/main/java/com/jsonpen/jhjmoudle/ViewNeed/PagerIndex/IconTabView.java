package com.jsonpen.jhjmoudle.ViewNeed.PagerIndex;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IconTabView extends LinearLayout {

    private TextView text;
    private ImageView icon;

    public IconTabView(Context context) {
        super(context);
        initView();
    }

    public IconTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        if (null == text || null == icon) {
            this.removeAllViews();
            this.setOrientation(HORIZONTAL);
            this.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            text = new TextView(getContext());
            text.setSingleLine();
            text.setGravity(Gravity.CENTER);
            text.setBackgroundColor(Color.TRANSPARENT);
            this.addView(text, params);
            params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            icon = new ImageView(getContext());
            icon.setContentDescription("");
            icon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            icon.setBackgroundColor(Color.TRANSPARENT);
            params.setMargins(5, (int) (10 * getResources().getDisplayMetrics().density), 0, 0);
            this.addView(icon, params);
            icon.setVisibility(View.GONE);
        }
    }

    public void setText(String str) {
        initView();
        text.setText(str);
    }

    public void setText(int strId) {
        initView();
        text.setText(strId);
    }

    public void setTextColor(int color) {
        initView();
        text.setTextColor(color);
    }

    public void setTextSize(float size) {
        initView();
        text.setTextSize(size);
    }

    public void setTextSize(int unit, float size) {
        initView();
        text.setTextSize(unit, size);
    }

    public void setTypeface(Typeface tf, int style) {
        initView();
        text.setTypeface(tf, style);
    }

    public CharSequence getText() {
        initView();
        return text.getText();
    }

    public void setIcon(boolean isShow, int resId) {
        initView();
        if (isShow) {
            icon.setImageResource(resId);
            icon.setVisibility(View.VISIBLE);
        } else {
            icon.setVisibility(View.GONE);
        }
    }

}
