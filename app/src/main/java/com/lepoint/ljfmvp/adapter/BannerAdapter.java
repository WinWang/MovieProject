package com.lepoint.ljfmvp.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

import cn.droidlover.xdroidmvp.imageloader.ILFactory;

public class BannerAdapter extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ILFactory.getLoader().loadNet(imageView, path.toString(), null);
    }
}
