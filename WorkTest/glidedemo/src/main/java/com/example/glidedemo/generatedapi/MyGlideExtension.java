package com.example.glidedemo.generatedapi;


import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.glidedemo.R;

@GlideExtension
public class MyGlideExtension {
    private MyGlideExtension() {
    }

    /**
     * 全局统一配置
     *
     * @param options
     */
    @GlideOption
    public static BaseRequestOptions<?> injectOptions(BaseRequestOptions<?> options) {
       return options.placeholder(R.mipmap.loading)//设置加载中的提示等待图片
                .error(R.mipmap.loader_error)//设置加载失败的图片
                .circleCrop();//设置显示为圆角图片
    }
}
