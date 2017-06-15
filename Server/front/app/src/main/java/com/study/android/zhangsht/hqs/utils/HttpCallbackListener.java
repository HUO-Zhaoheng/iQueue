package com.study.android.zhangsht.hqs.utils;

/**
 * Created by zhangsht on 2017/5/19.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
