package com.example.mvpdemo.presenter;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;

import com.example.mvpdemo.base.BasePresenter;
import com.example.mvpdemo.bean.Emoji;
import com.example.mvpdemo.model.EmojiModel;
import com.example.mvpdemo.model.IEmojiModel;
import com.example.mvpdemo.view.IEmojiView;

import java.util.List;

public class EmojiPresenter extends BasePresenter<IEmojiView> {
    //持有View层
//    IEmojiView iEmojiView;

    //持有model层
    IEmojiModel iEmojiModel = new EmojiModel();


    //执行UI逻辑
    public void fetch() {
        if (getView() != null && iEmojiModel != null) {
            iEmojiModel.loadEmojiData(new IEmojiModel.OnLoadListener() {
                @Override
                public void onComplete(List<Emoji> emojis) {
                    getView().showEmojiView(emojis);
                }

                @Override
                public void onError(String error) {
                    getView().showError(error);
                }
            });
        }
    }

    @Override
    protected void onCreate(LifecycleOwner owner) {
        super.onCreate(owner);
        Log.e("test", "onCreate: " );
    }


    @Override
    protected void onDestroy(LifecycleOwner owner) {
        super.onDestroy(owner);
        Log.e("test", "onDestroy: ");
    }

    @Override
    protected void onPause(LifecycleOwner owner) {
        super.onPause(owner);
        Log.e("test", "onPause: ");
    }

    @Override
    protected void onResume(LifecycleOwner owner) {
        super.onResume(owner);
        Log.e("test", "onResume: " );
    }
}
