package com.example.myapplicationdemo.awork.utils;
import com.example.myapplicationdemo.awork.bean.Article;
import com.example.myapplicationdemo.awork.bean.ArticleContent;
import java.util.ArrayList;
import java.util.List;

public class NetDataUtils {
    public static List<Article> net2ArticleList(List<ArticleContent> articleContentList, List<String> imgList) {
        List<Article> articleList = new ArrayList<>();
        if (articleContentList != null && imgList != null) {
            for (int i = 0; i < articleContentList.size(); i++) {
                Article article = new Article();
                ArticleContent articleContent = articleContentList.get(i);
                String string = imgList.get(i % 3);
                article.setAuthor(articleContent.getAuthor());
                article.setImg(string);
                article.setTitle(articleContent.getTitle());
                article.setContent(articleContent.getContent());
                articleList.add(article);
            }
        }
        return articleList;
    }
}
