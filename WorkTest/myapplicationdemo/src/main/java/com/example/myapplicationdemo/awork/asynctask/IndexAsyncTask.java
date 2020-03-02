package com.example.myapplicationdemo.awork.asynctask;

import android.content.Context;
import android.os.AsyncTask;

import com.example.myapplicationdemo.awork.activity.WorkIndexActivity;
import com.example.myapplicationdemo.awork.bean.Article;
import com.example.myapplicationdemo.awork.bean.ArticleContent;
import com.example.myapplicationdemo.awork.db.Dao;
import com.example.myapplicationdemo.awork.utils.HttpUtils;
import com.example.myapplicationdemo.awork.utils.L;
import com.example.myapplicationdemo.awork.utils.NetDataUtils;
import com.example.myapplicationdemo.awork.utils.ParseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一次机内，先从网络获取数据，以后进入从数据库获取，工具
 */
public class IndexAsyncTask {

    public void loadData(Context context, final NetDataTask.CallBack callBack, final boolean isCache) {
        NetDataTask asyncTask = new NetDataTask(context, callBack);
        asyncTask.execute(isCache);
    }

    public static class NetDataTask extends AsyncTask<Boolean, Void, List<Article>> {
        private Dao dao = new Dao();
        private Context mContext;
        private CallBack mCallback;
        private Exception ex;

        public NetDataTask(Context context, CallBack mCallback) {
            this.mContext = context;
            this.mCallback = mCallback;
        }

        @Override
        protected List<Article> doInBackground(Boolean... booleans) {
            List<Article> articleList = new ArrayList<>();
            try {
                boolean isCache = booleans[0];
                if (isCache) {
                    List<Article> listDbResult = dao.loadFromDb(mContext);
                    if (listDbResult != null) {
                        articleList.addAll(listDbResult);
                    }
                }
                if (articleList.isEmpty()) {
                    List<Article> listNetResult = loadFromNet(mContext);
                    if (listNetResult != null) {
                        dao.insert2Db(mContext, listNetResult);
                        articleList.addAll(listNetResult);
                    }

                }
            } catch (Exception ex) {
                this.ex = ex;
            }
            return articleList;
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            super.onPostExecute(articles);
            if (ex != null) {
                mCallback.onFail(ex);
            }
            if (articles != null) {
                mCallback.onSccess(articles);
            }
        }

        public interface CallBack {
            void onSccess(List<Article> articleList);

            void onFail(Exception ex);
        }

        private List<Article> loadFromNet(Context context) {
            String imgUrl = "http://www.imooc.com/api/teacher?type=1";
            String contentUrl = "http://www.imooc.com/api/teacher?type=3&cid=";
            String imgResult = HttpUtils.doGetNetData(imgUrl);
            L.d(WorkIndexActivity.TAG, "imgResult:" + imgResult);
            List<String> imgList = ParseUtils.parseImagData(imgResult);
            List<ArticleContent> contentResultList = new ArrayList<>();
            for (int i = 1; i < 31; i++) {
                String cotentResult = HttpUtils.doGetNetData(contentUrl + i);
                L.d(WorkIndexActivity.TAG, "cotentResult:" + contentUrl);
                ArticleContent articleContent = ParseUtils.parseArticleData(cotentResult);
                contentResultList.add(articleContent);
            }
            List<Article> articResultLeList = NetDataUtils.net2ArticleList(contentResultList, imgList);
            return articResultLeList;
        }

    }
}
