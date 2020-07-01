package com.example.mvpdemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity<T extends BasePresenter,V extends IBaseView> extends AppCompatActivity {

    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //表示层的选择
        presenter=createPresenter();
        presenter.attachView((V) this);
        init();
        registerSDK();
    }

    protected abstract T createPresenter();
    protected void registerSDK(){}
    protected void unRegisterSDK(){}
    protected void init(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        unRegisterSDK();
    }
}
