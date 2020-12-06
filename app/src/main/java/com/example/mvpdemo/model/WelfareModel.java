package com.example.mvpdemo.model;

import com.example.mvpdemo.base.BaseModel;
import com.example.mvpdemo.bean.WelfareBean;
import com.example.mvpdemo.netsubscribe.WelfareSubscribe;
import com.example.mvpdemo.netutils.OnSuccessAndFaultListener;
import com.example.mvpdemo.netutils.OnSuccessAndFaultSub;
import com.example.mvpdemo.netutils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created by Fan on 2020/11/14
 */
public class WelfareModel extends BaseModel implements IWelfareModel {

    @Override
    public void getImagesData(int page, int count, DisposableObserver<WelfareBean> subscribe) {
        Observable<WelfareBean> observable = RetrofitFactory.getInstance().getHttpApi().getImagesData(page,count);
        RetrofitFactory.getInstance().toSubscribe(observable,subscribe);
    }
}
