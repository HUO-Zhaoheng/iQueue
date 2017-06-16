package com.study.android.zhangsht.hqs.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhangsht on 2017/5/19.
 */

public class HttpTool {
    public static void sendRequest(final String address, final String params,
                                   final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    // 调用URL对象的openConnection方法获取HttpURLConnection的实例
                    byte[] postData = params.getBytes();
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    // 设置请求方式，GET或POST
                    connection.setRequestMethod("POST");
                    // 设置连接超时、读取超时的时间，单位为毫秒（ms）
                    connection.setConnectTimeout(8000);
                    // 设置是否使用缓存  默认是true
                    connection.setDoOutput(true);
                    // Post请求不能使用缓存
                    connection.setUseCaches(false);
                    connection.setInstanceFollowRedirects(true);
                    // 开始连接
                    Log.i("HttpURLConnection.GET","开始连接");
                    connection.connect();
                    DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
                    dos.write(postData);
                    dos.flush();
                    dos.close();

                    if (connection.getResponseCode() == 200) {
                        Log.i("HttpURLConnection.GET", "请求chenggong");
                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        if (listener != null) {
                            // 回调方法 onFinish()
                            listener.onFinish(response.toString());
                        }
                    } else {
                        Log.i("HttpURLConnection.GET", "请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null) {
                        // 回调方法 onError()
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}