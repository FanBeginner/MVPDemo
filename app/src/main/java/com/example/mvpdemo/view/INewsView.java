package com.example.mvpdemo.view;

import com.example.mvpdemo.base.IBaseView;
import com.example.mvpdemo.bean.TitleData;

import java.util.List;

public interface INewsView extends IBaseView {
    void showNewsView(TitleData data);
}
