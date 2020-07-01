package com.example.mvpdemo.view;

import com.example.mvpdemo.base.IBaseView;
import com.example.mvpdemo.bean.Emoji;

import java.util.List;

/*
* UI逻辑
* */
public interface IEmojiView extends IBaseView {
    //显示图片
    void showEmojiView(List<Emoji> emojis);
    //加载进度条
}
