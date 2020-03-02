package com.example.expandablelistviewdemo.net;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.expandablelistviewdemo.MainActivity;
import com.example.expandablelistviewdemo.bean.Chapter;
import com.example.expandablelistviewdemo.bean.ChapterItem;
import com.example.expandablelistviewdemo.dao.ChapterDao;
import com.example.expandablelistviewdemo.utils.HttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChapterBiz {
    ChapterDao chapterDao = new ChapterDao();//获取数据库操作对象

    /**
     * 下载数据方法
     * @param context  上下文
     * @param callBack  回调出去的接口，用于判断下载状态，失败则返回失败信息， 成功，则把获取到的数据集合回调出去，给UI显示
     * @param useCache   判断是否从本地数据库偶去数据的标记位
     */
    public void loadDatas(final Context context, final CallBack callBack, final boolean useCache) {
        Exception ex;   //用于判断网络获取数据出现异常时，返回异常信息
        /**
         * 参数： 入参，Boolean用于判断是否从本地获取数据
         * 参数2：进度，不用管，Void
         * 参数3，返回结果，这里要返回的是数据处理获得显示数据集合， List<Chapter>> asyncTask
         */
        AsyncTask<Boolean, Void, List<Chapter>> asyncTask = new AsyncTask<Boolean, Void, List<Chapter>>() {
            private Exception ex;

            /**
             *
             * @param booleans  入参
             * @return
             */
            @Override
            protected List<Chapter> doInBackground(Boolean... booleans) {
                try {//为了防止发生异常，添加try cache ，在异常时将异常信息回调出去
                    List<Chapter> chapterList = new ArrayList<>();//保存获取的数据的集合
                    if (useCache) {
                        /**
                         *  本地获取数据，通过数据库读取
                         * loadFromDb()  数据库获取数据的方法，查询数据
                         */

                   List<Chapter> list=chapterDao.loadFromDb(context);
                   Log.d(MainActivity.TAG,list.size()+"   list="+list);
                   chapterList.addAll(list);//添加数据到集合
                    }
                    /**
                     * chapterList.isEmpty()  判断chapterList集合是否为空，若果为空，则网络下载
                     * loadFromNet(context)  从网络下载数据的方法  返回处理好的数据集合
                     * chapterDao.insert2Db(context,chapterListFromNet);获得数据，计时将数据添加到本地数据库
                     */
                    //load from net
                    if (chapterList.isEmpty()) {
                        //load from net
                        List<Chapter> chapterListFromNet = loadFromNet(context);
                        //todo cache to db
                        chapterDao.insert2Db(context,chapterListFromNet);
                        chapterList.addAll(chapterListFromNet);//添加数据到结果集合
                    }
                    return chapterList;//将结果返回，到结果方法
                } catch (Exception ex) {
                    ex.printStackTrace();
                    this.ex = ex;//如果发生异常，将以此对象保存，便于后面回调出去
                    return null;//有异常，则下载失败，放回空的数据集
                }
            }

            /**
             * 下载完成后，处理结果的方法
             * @param chapters
             */
            @Override
            protected void onPostExecute(List<Chapter> chapters) {
                super.onPostExecute(chapters);
                if (ex != null) {//入伙有异常信息，则回调出去，
                    callBack.onFailed(ex);
                }
                callBack.onSuccess(chapters);//没有则回调结果集合出去，给UI使用
            }
        };
        asyncTask.execute(useCache);//每次要记得执行一步网络操作
    }

    /**
     * 从网络获取数据的方法
     * @param context
     * @return
     */
    private List<Chapter> loadFromNet(Context context) {
        List<Chapter> chapterList = new ArrayList<>();
        String url = "https://www.imooc.com/api/expandablelistview";
        //1,发请求获取string 数据
        String content = HttpUtils.doGet(url);//通过HttpUitls工具类的访问网络方法进行网络操作，将网络请求分离出去作为单独的类，方便调用
                                            //返回请求到的数据
        Log.d(MainActivity.TAG,"content="+content);
        //2，content-->>List<Chapter>
        if (content != null) {//如果请求到的数据不为空，则通过parseContent(）方法解析数据，将数据结构化为Chapter对象的数据保存
            chapterList = parseContent(content);
        }
        return chapterList;//最后返回这个结果
    }

    private List<Chapter> parseContent(String content) {//解析数据
        List<Chapter> chapterList = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(content);
            int errorCode = root.optInt("errorCode");
            if(errorCode==0){
                JSONArray jsonArray = root.optJSONArray("data");
                for (int i = 0; i <jsonArray.length() ; i++) {
                    JSONObject chapterObj = jsonArray.optJSONObject(i);
                    int id = chapterObj.optInt("id");
                    String name = chapterObj.optString("name");
                    Chapter chapter=new Chapter(id,name);
                    JSONArray itemArray  = chapterObj.optJSONArray("children");
                    for (int j = 0; j <itemArray.length() ; j++) {
                        JSONObject chapterItemObj = itemArray.optJSONObject(j);
                        int cid =  chapterItemObj.optInt("id");
                        String cname = chapterItemObj.optString("name");
                        ChapterItem chapterItem = new ChapterItem(cid,cname);
                        chapter.addChild(chapterItem);
                    }
                    chapterList.add(chapter);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chapterList;
    }

    public static interface CallBack {//回调接口

        void onSuccess(List<Chapter> chapterList);

        void onFailed(Exception ex);
    }

}
