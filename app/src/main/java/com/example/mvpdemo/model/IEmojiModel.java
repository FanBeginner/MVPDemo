package com.example.mvpdemo.model;

import com.example.mvpdemo.bean.Emoji;

import java.util.List;

/*
* 数据源
* */
public interface IEmojiModel {
    void loadEmojiData(OnLoadListener onLoadListener);
    interface OnLoadListener{
        void onComplete(List<Emoji> emojis);
        void onError(String error);
    }
}
