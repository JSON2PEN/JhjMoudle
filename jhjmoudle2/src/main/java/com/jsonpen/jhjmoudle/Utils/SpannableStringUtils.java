package com.jsonpen.jhjmoudle.Utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;

/**
 * 富文本工具类,凭借字符串
 * Created by xiaodi on 2016/5/17.
 */
public class SpannableStringUtils   {

    /**
     * 超链接
     */
    private void addUrlSpan(String str) {
        SpannableString spanString = new SpannableString(str);
        //url的链接
        URLSpan span = new URLSpan("tel:0123456789");
        //str的长度
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

    }


    /**
     * 文字背景颜色
     */
    private void addBackColorSpan() {
        SpannableString spanString = new SpannableString("颜色2");
        BackgroundColorSpan span = new BackgroundColorSpan(Color.YELLOW);
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


    /**
     * 文字颜色
     */
    public static SpannableString addForeColorSpan(int number) {

        String numberStr = number + "";
        int i = numberStr.length();
        SpannableString spanString = new SpannableString(numberStr);

        //颜色更改
        ForegroundColorSpan span = new ForegroundColorSpan(Color.argb(255,90,201,251));
        spanString.setSpan(span, 0, i, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString;
    }


    /**
     * 字体大小
     */
    private void addFontSpan() {
        SpannableString spanString = new SpannableString("36号字体");
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(36);
        spanString.setSpan(span, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


    /**
     * 粗体，斜体
     */
    private void addStyleSpan() {
        SpannableString spanString = new SpannableString("BIBI");
        StyleSpan span = new StyleSpan(Typeface.BOLD_ITALIC);
        spanString.setSpan(span, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }


    /**
     * 删除线
     */
    private void addStrikeSpan() {
        SpannableString spanString = new SpannableString("删除线");
        StrikethroughSpan span = new StrikethroughSpan();
        spanString.setSpan(span, 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    /**
     * 返回下划线
     */
    public static SpannableString addUnderLineSpan(String str) {
        SpannableString spanString = new SpannableString(str);
        UnderlineSpan span = new UnderlineSpan();
        //颜色更改
        ForegroundColorSpan span2 = new ForegroundColorSpan(Color.WHITE);
        spanString.setSpan(span2, 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(span, 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanString ;
    }



    /**
     * 图片
     */
    private void addImageSpan() {
//        SpannableString spanString = new SpannableString(" ");
////        Drawable d = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
//        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
//        spanString.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tv.append(spanString);
    }




}
