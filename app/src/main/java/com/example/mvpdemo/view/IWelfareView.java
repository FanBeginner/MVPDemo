package com.example.mvpdemo.view;

import com.example.mvpdemo.base.IBaseView;
import com.example.mvpdemo.bean.WelfareBean;

import java.util.List;

/**
 * Created by Fan on 2020/11/14
 */
public interface IWelfareView extends IBaseView {
    void showWelfare(List<WelfareBean.DataBean> dataBeans);
}
