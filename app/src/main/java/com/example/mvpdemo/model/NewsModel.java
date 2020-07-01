package com.example.mvpdemo.model;

import com.example.mvpdemo.bean.TitleData;

import java.util.List;

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback;
import cn.finalteam.okhttpfinal.HttpRequest;

public class NewsModel implements INewsModel<String> {
    @Override
    public void loadNewsData(String url, final LoadTasksCallBack callBack) {

        HttpRequest.get(url,new BaseHttpRequestCallback<TitleData>(){
            @Override
            protected void onSuccess(TitleData titleData) {
                super.onSuccess(titleData);
                callBack.onSuccess(titleData);
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                super.onFailure(errorCode, msg);
                callBack.onFailed(msg);
            }
        });
    }
}
