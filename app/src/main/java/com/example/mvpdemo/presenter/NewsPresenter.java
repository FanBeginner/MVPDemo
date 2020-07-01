package com.example.mvpdemo.presenter;

import android.util.Log;

import com.example.mvpdemo.base.BasePresenter;
import com.example.mvpdemo.bean.TitleData;
import com.example.mvpdemo.model.INewsModel;
import com.example.mvpdemo.model.NewsModel;
import com.example.mvpdemo.view.INewsView;

import java.util.List;

public class NewsPresenter extends BasePresenter<INewsView> {
    private static final String TAG = "NewsPresenter";
    //持有modle层
    NewsModel newsModel=new NewsModel();
    String url="http://v.juhe.cn/toutiao/index?type=top&key=cc651913ae067cf88c7d9ec710fe5b3a";

    public void fetch(){
        if(getView()!=null&&newsModel!=null){
            newsModel.loadNewsData(url,new INewsModel.LoadTasksCallBack<TitleData>() {
                @Override
                public void onSuccess(TitleData data) {
                    Log.e(TAG, "onSuccess: "+data.getResult().getData().get(0).getTitle());
                    getView().showNewsView(data);
                }

                @Override
                public void onFailed(String msg) {
                    getView().showError(msg);
                }
            });
        }
    }
}
