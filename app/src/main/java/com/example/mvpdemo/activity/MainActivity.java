package com.example.mvpdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mvpdemo.R;
import com.example.mvpdemo.adapter.ListViewAdapter;
import com.example.mvpdemo.bean.Emoji;
import com.example.mvpdemo.presenter.EmojiPresenter;
import com.example.mvpdemo.base.BaseActivity;
import com.example.mvpdemo.view.IEmojiView;

import java.util.List;

import cn.finalteam.okhttpfinal.HttpRequest;

public class MainActivity extends BaseActivity<EmojiPresenter,IEmojiView> implements IEmojiView {

    private ListView listView;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected EmojiPresenter createPresenter() {
        return new EmojiPresenter();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);

        presenter.fetch();

    }

    @Override
    public void showEmojiView(List<Emoji> emojis) {
        adapter=new ListViewAdapter(this,emojis);
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

    public void startNewsActivity(View view) {
        startActivity(new Intent(MainActivity.this,NewsActivity.class));
    }
}
