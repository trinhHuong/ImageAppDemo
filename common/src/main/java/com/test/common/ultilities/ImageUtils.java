package com.test.common.ultilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.test.common.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtils {
    private static ImageUtils instance;
    private static LruCache<String, Bitmap> memoryCache;
    public static synchronized ImageUtils getInstance() {
        if (instance == null) {
            instance = new ImageUtils();
        }
        return instance;
    }

    public ImageUtils() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;

        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }
    public static void loadImageNormal(Context context, String imageUrl, ImageView imageView) {
        Bitmap bitmap = getBitmapFromMemCache(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageResource(R.drawable.imv_placeholder);
            BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            task.execute(imageUrl);
        }
    }

    public static void loadRoundedImage(Context context, String url, ImageView imageView) {
        Bitmap bitmap = getBitmapFromMemCache(url);
        if (bitmap != null) {
            imageView.setImageDrawable(getRoundedDrawable(context, bitmap));
        } else {
            imageView.setImageResource(R.drawable.imv_placeholder);
            BitmapWorkerTask task = new BitmapWorkerTask(imageView, true);
            task.execute(url);
        }
    }
    private static Drawable getRoundedDrawable(Context context, Bitmap bitmap) {
        RoundedBitmapDrawable circularBitmapDrawable =
                RoundedBitmapDrawableFactory.create(context.getResources(), bitmap);
        circularBitmapDrawable.setCornerRadius(20);
        return circularBitmapDrawable;
    }
    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            memoryCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmapFromMemCache(String key) {
        return memoryCache.get(key);
    }
    public static BitmapImageViewTarget getRoundedImageTarget(@NonNull final Context context, @NonNull final ImageView imageView,
                                                              final float radius) {
        return new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(final Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCornerRadius(radius);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        };
    }
    public static class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView;
        private boolean isRounded;

        public BitmapWorkerTask(ImageView imageView) {
            this.imageView = imageView;
            isRounded = false;
        }
        public BitmapWorkerTask(ImageView imageView, boolean isRounded) {
            this.imageView = imageView;
            this.isRounded = isRounded;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String stringUrl = params[0];
            Bitmap bmp = null;
            try{

                URL url = new URL(stringUrl);
                HttpURLConnection con = (HttpURLConnection)url.openConnection();
                con.setDoInput(true);
                con.connect();
                int responseCode = con.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK)
                {
                    InputStream in = con.getInputStream();
                    bmp = BitmapFactory.decodeStream(in);
                    in.close();
                }

            }
            catch(Exception ex){
                Log.e("Exception",ex.toString());
            }
            if (bmp != null) {
                addBitmapToMemoryCache(stringUrl, bmp);
            }
            return bmp;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                if (isRounded) {
                    imageView.setImageDrawable(getRoundedDrawable(imageView.getContext(), bitmap));
                } else {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }

}
