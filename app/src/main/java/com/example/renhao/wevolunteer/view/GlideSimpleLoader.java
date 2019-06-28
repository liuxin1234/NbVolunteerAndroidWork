package com.example.renhao.wevolunteer.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.ielse.imagewatcher.ImageWatcher;

/**
 * Created by
 * 项目名称：com.example.renhao.wevolunteer.activity.show
 * 项目日期：2019/5/7
 * 作者：liux
 * 功能：
 *
 * @author 750954283(qq)
 */

public class GlideSimpleLoader implements ImageWatcher.Loader {
    public void load(Context context, Uri uri, final ImageWatcher.LoadCallback lc) {
        Glide.with(context).load(uri)
                .into(new SimpleTarget<GlideDrawable>() {

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        lc.onResourceReady(resource);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        lc.onLoadFailed(errorDrawable);
                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {
                        lc.onLoadStarted(placeholder);
                    }


                });
    }
}
