package com.example.mvpdemo.model;

import com.example.mvpdemo.bean.WelfareBean;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by Fan on 2020/11/14
 */
public interface IWelfareModel {
    void getImagesData(int page,int count, DisposableObserver<WelfareBean> subscribe);
}
