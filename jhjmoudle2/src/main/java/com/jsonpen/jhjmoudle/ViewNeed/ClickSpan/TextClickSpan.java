package com.jsonpen.jhjmoudle.ViewNeed.ClickSpan;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by user on 2017/4/19.
 */

public class TextClickSpan extends ClickableSpan {

    private String txt;
private InClick inClick;
    public TextClickSpan(String txt ,InClick inClick){
        this.txt = txt;
        this.inClick =inClick;
    }
    @Override
    public void onClick(View widget) {
        inClick.myClick();
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        //根据自己的需求定制文本的样式
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }
    public interface InClick{
        void myClick();
    }
}
