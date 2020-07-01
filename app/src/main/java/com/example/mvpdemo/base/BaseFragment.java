package com.example.mvpdemo.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends BasePresenter,V extends IBaseView> extends Fragment {

    protected T presenter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
        unRegisterSDK();
    }
}
