package com.example.glideusertest.usertestwork.activity.optionsutils;

import com.bumptech.glide.request.RequestOptions;
import com.example.glideusertest.R;

public class RequestOptionsUtils {
    public static RequestOptions baseRequestOptions() {
        return new RequestOptions()
                .placeholder(R.mipmap.loading)
                .error(R.mipmap.loader_error);
    }

    public static RequestOptions circleCrop() {
        return baseRequestOptions().circleCrop();//设置图片显示为圆角
    }
}
