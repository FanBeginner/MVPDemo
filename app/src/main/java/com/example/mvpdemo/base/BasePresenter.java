package com.example.mvpdemo.base;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M extends BaseModel,T extends IBaseView> implements LifecycleObserver {
    //弱引用
    private WeakReference<T> mView;

    protected M mModel;

    public BasePresenter(){
        this.mModel = getmModelInstance();
    }
    public T getView(){
        return mView.get();
    }

    /*
     * 绑定
     * */
    public void attachView(T view){
        this.mView=new WeakReference<>(view);
    }

    /*
     * 解绑
     * */
    public void detachView(){
        if(mView != null){
            mView.clear();
            mView=null;
        }
    }

    public abstract M getmModelInstance();

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate(LifecycleOwner owner){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onStart(LifecycleOwner owner){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPause(LifecycleOwner owner){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume(LifecycleOwner owner){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    protected void onStop(LifecycleOwner owner){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy(LifecycleOwner owner){

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    protected void onAny(LifecycleOwner owner){

    }


}
