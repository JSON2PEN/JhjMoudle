package com.jsonpen.jhjmoudle.ViewNeed;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2017/3/15.
 */

public class ChoicePriceBd extends View {
    public ChoicePriceBd(Context context) {
        super(context);
    }

    public ChoicePriceBd(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChoicePriceBd(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint =new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#ff0000"));
        paint.setStrokeWidth(15);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(50,50,40,paint);
        canvas.drawCircle(150,50,40,paint);
        canvas.drawCircle(250,50,40,paint);
        canvas.drawCircle(350,50,40,paint);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(20);
        canvas.drawLine(20,50,370,50,paint);
}
}
