package com.example.mvpdemo.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity<P extends BasePresenter,V extends IBaseView> extends AppCompatActivity {

    protected P presenter;
    protected Context mContext;
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //表示层的选择
        presenter=createPresenter();
        presenter.attachView((V) this);
        mContext = this;
        init();
        registerSDK();
    }

    protected abstract P createPresenter();
    protected void registerSDK(){}
    protected void unRegisterSDK(){}
    protected void init(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }
    /**
     * 显示提示框
     *
     * @param msg 提示框内容字符串
     */
    protected void showProgressDialog(String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    /**
     * 隐藏提示框
     */
    protected void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        unRegisterSDK();
    }
}
