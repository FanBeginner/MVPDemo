package com.example.mvpdemo.model;

public interface INewsModel<T> {
    void loadNewsData(T url,LoadTasksCallBack callBack);
    interface LoadTasksCallBack<T>{
        void onSuccess(T data);

        void onFailed(String msg);
    }
}
