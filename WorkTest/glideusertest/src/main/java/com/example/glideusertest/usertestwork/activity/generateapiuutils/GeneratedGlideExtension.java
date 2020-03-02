package com.example.glideusertest.usertestwork.activity.generateapiuutils;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.BaseRequestOptions;
import com.example.glideusertest.R;

@GlideExtension
public class GeneratedGlideExtension {
    private GeneratedGlideExtension() {
    }
@GlideOption
    public static BaseRequestOptions<?> generated(BaseRequestOptions<?> options) {
        return options.placeholder(R.mipmap.loading)
                .error(R.mipmap.loader_error)
                .circleCrop();
    }
}
