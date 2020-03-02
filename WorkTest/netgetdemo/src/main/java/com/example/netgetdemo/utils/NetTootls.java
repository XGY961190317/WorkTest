package com.example.netgetdemo.utils;

import android.util.Log;
import android.widget.Toast;

import com.example.netgetdemo.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 此工具类处理网络相关的方法
 */
public class NetTootls {
    private String imooc = "imooc";
    private String numbar = "15111305811";
    /**
     * 网络操作  Post
     *
     * @param
     * @return
     */
    private String requestDataByPost() {
        String urlString = "http://www.imooc.com/api/teacher?";
        String result = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30 * 1000);//请求超时时间
            connection.setRequestMethod("POST");//设置为post请求方式
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setDoOutput(true);//设置运行时允许写入
            connection.setDoInput(true);//设置运行时允许读取
            connection.setUseCaches(false);//设置不允许用缓存
            connection.connect();//发起请求
            //post提交的数据处理
            String data = "username=" + Tootls.getEncodeValue(imooc) + "&numbar=" + Tootls.getEncodeNumbar(numbar);//例如
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();

            String str = connection.getResponseMessage();
            int requestCode = connection.getResponseCode();
            if (requestCode == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                result = Tootls.streamToString(is);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * Get
     *
     * @param urlString
     * @return
     */
    public static String requestDataByGet(String urlString) {
        String result = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30 * 1000);//请求超时时间
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.connect();//发起请求
            String str = connection.getResponseMessage();
            int requestCode = connection.getResponseCode();
            if (requestCode == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                result = Tootls.streamToString(is);
                Log.d(MainActivity.TAG,result);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static GetDataentity getStringToJson(String resultString) {
        GetDataentity dataentity = new GetDataentity();
        try {

            JSONObject jsonObject = new JSONObject(resultString);
            dataentity.setStatus(jsonObject.getInt("status"));
            dataentity.setMsg(jsonObject.getString("msg"));
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            List<DataItemEntity>listData = new ArrayList<>();
            if(jsonArray!=null&&jsonArray.length()>0){
            for (int index = 0; index <jsonArray.length() ; index++) {
                JSONObject data = jsonArray.getJSONObject(index);
                DataItemEntity dataItemEntity = new DataItemEntity();
                dataItemEntity.setId(data.getInt("id"));
                dataItemEntity.setDescription(data.getString("description"));
                dataItemEntity.setName(data.getString("name"));
                dataItemEntity.setPicBig(data.getString("picBig"));
                dataItemEntity.setPicSmall(data.getString("picSmall"));
                dataItemEntity.setLearner(data.getInt("learner"));
                listData.add(dataItemEntity);
            }
            }
            dataentity.setData(listData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataentity;
    }
}
