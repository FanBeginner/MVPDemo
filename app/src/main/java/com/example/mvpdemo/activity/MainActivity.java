package com.example.mvpdemo.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.adapter.ListViewAdapter;
import com.example.mvpdemo.base.BaseActivity;
import com.example.mvpdemo.bean.Emoji;
import com.example.mvpdemo.presenter.EmojiPresenter;
import com.example.mvpdemo.view.IEmojiView;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<EmojiPresenter, IEmojiView> implements IEmojiView {

    @BindView(R.id.btn_welfare)
    Button btnWelfare;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.btn_getNewsData)
    Button btnGetNewsData;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if(!aBoolean){
                            Toast.makeText(mContext, "未授予权限，部分功能无法使用！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        initView();
    }

    @Override
    protected EmojiPresenter createPresenter() {
        return new EmojiPresenter();
    }

    private void initView() {

        presenter.fetch();

    }

    @Override
    public void showEmojiView(List<Emoji> emojis) {
        adapter = new ListViewAdapter(this, emojis);
        listView.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void init() {
        super.init();
        getLifecycle().addObserver(presenter);
    }


    @OnClick({R.id.btn_welfare,R.id.btn_getNewsData})
    public void onViewClicked(View view) {
        switch(view.getId()){
            case R.id.btn_getNewsData:
                startActivity(new Intent(MainActivity.this, NewsActivity.class));
                break;
            case R.id.btn_welfare:
                startActivity(new Intent(this,WelfareActivity.class));
                break;
        }
    }

}
