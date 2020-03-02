package utils;

import com.bumptech.glide.request.RequestOptions;
import com.example.glidedemo.R;

public class GlideOptions {
    /**
     * 自己封装好这个GlideOptions这个类，进行RequestOptions的配置
     * @return
     */
    public static RequestOptions baseOptions(){
        return new RequestOptions().placeholder(R.mipmap.loading)//设置加载中的提示等待图片
                .error(R.mipmap.loader_error);//设置加载失败的图片

    }
    public static RequestOptions circleCropOption(){
        return baseOptions().circleCrop();//设置显示为圆角图片
    }
}
