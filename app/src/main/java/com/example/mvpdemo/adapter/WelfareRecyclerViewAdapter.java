package com.example.mvpdemo.adapter;

import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mvpdemo.R;
import com.example.mvpdemo.bean.WelfareBean;

import java.util.List;

/**
 * Created by Fan on 2020/11/14
 */
public class WelfareRecyclerViewAdapter extends BaseQuickAdapter<WelfareBean.DataBean, BaseViewHolder> {
    public WelfareRecyclerViewAdapter(int layoutResId) {
        super(layoutResId);
    }

    public WelfareRecyclerViewAdapter(int layoutResId, @Nullable List<WelfareBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WelfareBean.DataBean item) {
        Glide.with(mContext)
                .load(item.getUrl())
                .crossFade(500)
                .placeholder(R.mipmap.img_default_meizi)
                .into((ImageView) helper.getView(R.id.iv_item_image));
    }
}
