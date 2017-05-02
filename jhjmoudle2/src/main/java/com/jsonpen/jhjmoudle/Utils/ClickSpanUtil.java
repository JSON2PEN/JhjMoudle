package com.jsonpen.jhjmoudle.Utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.DrawableRes;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.jsonpen.jhjmoudle.ViewNeed.ClickSpan.ImgClickSpan;
import com.jsonpen.jhjmoudle.ViewNeed.ClickSpan.TextClickSpan;


/**
 * Created by user on 2017/4/19.
 */

public class ClickSpanUtil {

    private CommonClick mCommonClick;

    public static ClickSpanUtil newInstence() {
        return new ClickSpanUtil();
    }

    public void addTextClick(TextView mTextView, String txt, String sign, final CommonClick mCommonClick) {
        this.mCommonClick = mCommonClick;
        SpannableString span = new SpannableString(txt);
        TextClickSpan clickSpan = new TextClickSpan(sign, new TextClickSpan.InClick() {
            @Override
            public void myClick() {
                mCommonClick.textClick();
            }
        });
        if (txt.indexOf(sign) == -1) {
            throw new Resources.NotFoundException("text not contains sign");
        }
        span.setSpan(clickSpan, txt.indexOf(sign),
                txt.indexOf(sign) + sign.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTextView.setText(span);
        mTextView.setMovementMethod(LinkMovementMethod
                .getInstance());//加上这句才有点击事件响应;
    }

    public void addImgClick(TextView mTextView, String txt, int position, @DrawableRes int imgDrawable, final CommonClick mCommonClick) {
        this.mCommonClick = mCommonClick;
        String sign = "icon";
        String str = txt.substring(0, position) + sign + txt.substring(position);
        //设置ClickSpan,为部分文字("icon")添加点击效果
        SpannableString span = new SpannableString(str);
        TextClickSpan clickSpan = new TextClickSpan(sign, new TextClickSpan.InClick() {
            @Override
            public void myClick() {
                mCommonClick.textClick();
            }
        });
        span.setSpan(clickSpan, position,
                position + sign.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置ImageSpan,占用可点击文字("icon")的位置

//        InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
//        Bitmap mBitmap = BitmapFactory.decodeStream(is);

        BitmapDrawable drawable = (BitmapDrawable) CommonUtils.getResoure().getDrawable(imgDrawable);
        drawable.setBounds(0, 0, 60, 60);
        span.setSpan(new ImgClickSpan(drawable),
                position, position + sign.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        //设置TextView
        mTextView.setText(span);
        mTextView.setHighlightColor(Color.TRANSPARENT);//消除点击时的背景色
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public interface CommonClick {
        void textClick();
    }
}
