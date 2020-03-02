package com.example.myapplicationdemo.awork.utils;

import android.text.TextUtils;

import com.example.myapplicationdemo.awork.bean.ArticleContent;
import com.example.myapplicationdemo.awork.bean.ArticleImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ParseUtils {
    public static final String SUCCESS = "成功";

    public static List<String> parseImagData(String imgResult) {//解析图片
        List<String> imgList = new ArrayList<>();
        try {
            if (imgResult != null) {
                JSONObject jsonObject = new JSONObject(imgResult);
                ArticleImage image = new ArticleImage();
                image.setStatus(jsonObject.optInt("status"));
                String msg = jsonObject.optString("msg");
                image.setMsg(msg);
                if (TextUtils.equals(SUCCESS, msg)) {
                    JSONObject imgObj = jsonObject.optJSONObject("data");
                    for (int i = 1; i < 4; i++) {
                        String imgUrl = imgObj.optString("pic" + i);
                        imgList.add(imgUrl);
                    }
                    image.setImgList(imgList);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return imgList;
    }

    public static ArticleContent parseArticleData(String articleResult) {//解析文章
        ArticleContent content = new ArticleContent();
        try {
            if (articleResult != null) {
                JSONObject jsonObject = new JSONObject(articleResult);
                content.setStatus(jsonObject.optInt("status"));
                String msg = jsonObject.optString("msg");
                content.setMsg(msg);
                if (TextUtils.equals(SUCCESS, msg)) {
                    JSONObject contentObj = jsonObject.optJSONObject("data");
                    content.setTitle(contentObj.optString("title"));
                    content.setAuthor(contentObj.optString("author"));
                    content.setContent(contentObj.optString("content"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return content;
    }
}
