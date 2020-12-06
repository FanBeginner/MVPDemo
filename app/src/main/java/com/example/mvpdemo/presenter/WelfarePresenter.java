package com.example.mvpdemo.presenter;

import android.util.Log;

import com.example.mvpdemo.base.BasePresenter;
import com.example.mvpdemo.bean.WelfareBean;
import com.example.mvpdemo.model.WelfareModel;
import com.example.mvpdemo.netutils.OnSuccessAndFaultListener;
import com.example.mvpdemo.netutils.OnSuccessAndFaultSub;
import com.example.mvpdemo.utils.GsonUtils;
import com.example.mvpdemo.view.IWelfareView;

/**
 * Created by Fan on 2020/11/14
 */
public class WelfarePresenter extends BasePresenter<WelfareModel, IWelfareView> {
    public void fetch(int page,int count){
        if(getView()!= null && mModel != null){
            mModel.getImagesData(page,count,new OnSuccessAndFaultSub(new OnSuccessAndFaultListener<WelfareBean>() {
                @Override
                public void onSuccess(WelfareBean result) {
//                    WelfareBean welfareBean = GsonUtils.fromJson(result, WelfareBean.class);
                    getView().showWelfare(result.getData());
                }

                @Override
                public void onFault(String errorMsg) {
                    getView().showError(errorMsg);
                }
            }));
        }
    }
    @Override
    public WelfareModel getmModelInstance() {
        return new WelfareModel();
    }
}
