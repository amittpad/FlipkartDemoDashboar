package com.app.birlasoft.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


public class GlideConnector {
    Context context;
    @SuppressLint("StaticFieldLeak")
    private static GlideConnector instance = null;

    private GlideConnector() {

    }

    public static GlideConnector getInstance() {
        if (instance == null) {
            instance = new GlideConnector();
        }
        return instance;
    }

    public void loadImageDirectly(Context context, int imageURL, ImageView imageView) {
        Glide.with(context)
                .load(imageURL)
                .centerInside()
                .dontAnimate()
                .into(imageView);
    }




//    public void loadImageDirectly(Context context, String imageURL, ImageView imageView) {
//        Glide.with(context)
//                .load(imageURL)
//                .signature(new ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000))) // here you add some value , if the next time you add the same value then it will load from cache otherwise if you put new value you will download , then save in cache
//                .thumbnail(0.5f)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .apply(new RequestOptions().override(400, 200))
//                .format(DecodeFormat.PREFER_RGB_565)
//                .centerInside()
//                .dontAnimate()
//                .into(imageView);
//    }

  /*  public void loadImageDirectly(Context context, String imageURL, ImageView imageView) {
        Glide.with(context)
                .load(imageURL)
                .signature(new ObjectKey(System.currentTimeMillis() / (24 * 60 * 60 * 1000))) // here you add some value , if the next time you add the same value then it will load from cache otherwise if you put new value you will download , then save in cache
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(new RequestOptions().override(400, 200))
                .format(DecodeFormat.PREFER_RGB_565)
                .centerInside()
                .dontAnimate()
                .into(imageView);
    }*/



}
