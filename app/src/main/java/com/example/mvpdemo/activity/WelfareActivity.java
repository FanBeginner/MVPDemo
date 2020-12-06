package com.example.mvpdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.mvpdemo.R;
import com.example.mvpdemo.adapter.WelfareRecyclerViewAdapter;
import com.example.mvpdemo.base.BaseActivity;
import com.example.mvpdemo.bean.WelfareBean;
import com.example.mvpdemo.constant.BundleKeyConstant;
import com.example.mvpdemo.presenter.WelfarePresenter;
import com.example.mvpdemo.view.IWelfareView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelfareActivity extends BaseActivity<WelfarePresenter, IWelfareView> implements IWelfareView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_welfare)
    RecyclerView rvWelfare;
    private WelfareRecyclerViewAdapter mAdapter;
    int page=1,count=20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);
        ButterKnife.bind(this);
        mAdapter = new WelfareRecyclerViewAdapter(R.layout.item_gank_io_welfare);
        rvWelfare.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rvWelfare.setAdapter(mAdapter);

    }

    @Override
    protected void init() {
        super.init();
        showProgressDialog("加载中...");
        presenter.fetch(page,count);
    }

    @Override
    protected WelfarePresenter createPresenter() {
        return new WelfarePresenter();
    }

    @Override
    public void showWelfare(List<WelfareBean.DataBean> dataBeans) {
        if(mAdapter.getData().size() == 0){
            initRecyclerView(dataBeans);
        }else{
            mAdapter.loadMoreComplete();
            if(dataBeans.size() == 0){
                mAdapter.loadMoreEnd();
            }
            mAdapter.addData(dataBeans);
        }
        hideProgressDialog();
    }
    private void initRecyclerView(List<WelfareBean.DataBean> list){
        mAdapter = new WelfareRecyclerViewAdapter(R.layout.item_gank_io_welfare,list);
        rvWelfare.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rvWelfare.setAdapter(mAdapter);
        mAdapter.setOnLoadMoreListener(this,rvWelfare);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(WelfareActivity.this,ImageBrowseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(BundleKeyConstant.ARG_KEY_IMAGE_BROWSE_URL, ((WelfareBean.DataBean)adapter.getItem(position)).getUrl());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        hideProgressDialog();
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        presenter.fetch(page,count);
    }
}
