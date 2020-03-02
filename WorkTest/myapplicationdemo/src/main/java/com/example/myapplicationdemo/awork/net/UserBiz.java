package com.example.myapplicationdemo.awork.net;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myapplicationdemo.awork.activity.WorkIndexActivity;
import com.example.myapplicationdemo.awork.bean.Article;
import com.example.myapplicationdemo.awork.bean.ArticleContent;
import com.example.myapplicationdemo.awork.utils.HttpUtils;
import com.example.myapplicationdemo.awork.utils.L;
import com.example.myapplicationdemo.awork.utils.NetDataUtils;
import com.example.myapplicationdemo.awork.utils.ParseUtils;
import com.example.myapplicationdemo.awork.db.Dao;

import java.util.ArrayList;
import java.util.List;

public class UserBiz {
    private Dao dao = new Dao();

    public void loadData(final Context context, final CallBack callBack, final boolean isCache) {
        AsyncTask<Boolean, Void, List<Article>> asyncTask = new AsyncTask<Boolean, Void, List<Article>>() {
            private Exception ex;

            @Override
            protected List<Article> doInBackground(Boolean... booleans) {
                List<Article> articleList = new ArrayList<>();//数据的结果集合
                try {
                    if (isCache) {//第一次进入应用，先从本地数据库获取一次数据
                        List<Article> listDbResult = dao.loadFromDb(context);
                        if (listDbResult != null) {
                            articleList.addAll(listDbResult);
                        }
                    }
                    if (articleList.isEmpty()) {//第一次进入应用，当数据库获取的数为空时，执行网络操作获取数据，
                        List<Article> listNetResult = loadFromNet(context);
                        if (listNetResult != null) {
                            dao.insert2Db(context, listNetResult);//通过网络获取到数据，在回调给UI显示的同事，添加到数据库中，方便以后使用
                            articleList.addAll(listNetResult);
                        }
                    }
                } catch (Exception ex) {
                    this.ex = ex;
                }
                return articleList;
            }

            @Override
            protected void onPostExecute(List<Article> articleList) {
                super.onPostExecute(articleList);
                if (ex != null) {
                    callBack.onFail(ex);
                }
                if (articleList != null) {
                    callBack.onSccess(articleList);
                }

            }
        };
        asyncTask.execute(isCache);
    }

    private List<Article> loadFromNet(Context context) {
        String imgUrl = "http://www.imooc.com/api/teacher?type=1";
        String contentUrl = "http://www.imooc.com/api/teacher?type=3&cid=";
        String imgResult = HttpUtils.doGetNetData(imgUrl);
        L.d(WorkIndexActivity.TAG, "imgResult:" + imgResult);//查看img数据是否载入
        List<String> imgList = ParseUtils.parseImagData(imgResult);
        List<ArticleContent> contentResultList = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            String cotentResult = HttpUtils.doGetNetData(contentUrl + i);
            L.d(WorkIndexActivity.TAG, "cotentResult:" + contentUrl);//查看content数据是否载入
            ArticleContent articleContent = ParseUtils.parseArticleData(cotentResult);
            contentResultList.add(articleContent);
        }
        List<Article> articResultLeList = NetDataUtils.net2ArticleList(contentResultList, imgList);//将img。content两种数据集中到一个对象里，并生成数据集合
        return articResultLeList;//将这个集合作为结果返回出去
    }

    /**
     * Callback 回调
     * 当成功获取数据时，调用onSuccess()方法将数据回调出去，提供给UI使用
     * 当获取数据失败或出现异常时，onFail()方法将错误信息回调出去，提醒用户
     */
    public interface CallBack {
        void onSccess(List<Article> articleList);

        void onFail(Exception ex);
    }
}
