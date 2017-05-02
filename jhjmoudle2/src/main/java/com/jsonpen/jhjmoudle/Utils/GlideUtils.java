package com.jsonpen.jhjmoudle.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * 图片加载的工具类.
 */
public class GlideUtils {
    /**
     * @param context
     * @param imgUrl   图片的网络路径
     * @param errorRes 加载失败的填图片
     * @param img      记载图片的控件
     */
    public static void loadNetImage(Context context, String imgUrl, int errorRes, ImageView img) {
        Glide.with(context).load(imgUrl).
                diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(errorRes).
                error(errorRes).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                //显示错误信息
                Log.w("GLIDE", "onException: ", e);
                //打印请求URL
                Log.d("GLIDE", "onException: " + model);
                //打印请求是否还在进行
                Log.d("GLIDE", "onException: " + target.getRequest().isRunning());
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //imageView.setImageDrawable(resource);
                return false;
            }
        }).into(img);
    }

    /**
     * @param context
     * @param imgUrl   图片的网络路径
     * @param errorRes 加载失败的填图片
     * @param img      记载图片的控件
     * @param radius   圆角直径
     */
    public static void loadNetRoundImage(Context context, String imgUrl, int errorRes, ImageView img, int radius) {
        Glide.with(context).load(imgUrl).
                diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(errorRes).
                transform(new GlideRoundTransform(context, radius)).
                error(errorRes).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                //显示错误信息
                Log.w("GLIDE", "onException: ", e);
                //打印请求URL
                Log.d("GLIDE", "onException: " + model);
                //打印请求是否还在进行
                Log.d("GLIDE", "onException: " + target.getRequest().isRunning());
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //imageView.setImageDrawable(resource);
                return false;
            }
        }).into(img);

    }

    /**
     * @param context  上下文
     * @param imgUrl   图片的本地路径或者网络路径
     * @param errorRes 加载失败展示
     * @param radius   圆角度直径
     * @return
     */

    public static Bitmap getImageBitmap(Context context, String imgUrl, int errorRes, int radius) {
        Bitmap myBitmap = null;
        try {
            myBitmap = Glide.with(context)
                    .load(imgUrl)
                    .asBitmap() //必须
                    .centerCrop()
                    .into(radius, radius)
                    .get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return myBitmap;

    }

    /**
     * @param context  上下文
     * @param imgUrl   图片的本地路径或者网络路径
     * @param errorRes 加载失败展示
     * @param radius   圆角度直径
     * @return
     */

    public static void getImageBitmap(final Context context, final File imgUrl, int errorRes, final int radius, final BitmapHelper helper) {
        new AsyncTask<Void, Void, Bitmap>() {
            Bitmap myBitmap;
            @Override
            protected Bitmap doInBackground(Void... params) {
                try {
                    myBitmap  = Glide.with(context)
                            .load(imgUrl)
                            .asBitmap() //必须
                            .centerCrop()
                            .into(radius, radius)
                            .get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                return myBitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                helper.getBitmap(bitmap);
            }
        }.execute();
    }

    /**
     * 子线程获得图片的bitmap的回调接口
     */
    public interface BitmapHelper {
        void getBitmap(Bitmap bitmap);
    }



    /**
     * glide 设置圆角图片
     */
    public static class GlideRoundTransform extends BitmapTransformation {
        private float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }
}
