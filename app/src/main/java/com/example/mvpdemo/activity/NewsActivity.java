package com.example.mvpdemo.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpdemo.R;
import com.example.mvpdemo.adapter.NewsRecycleViewAdapter;
import com.example.mvpdemo.base.BaseActivity;
import com.example.mvpdemo.bean.TitleData;
import com.example.mvpdemo.presenter.NewsPresenter;
import com.example.mvpdemo.view.INewsView;

import java.util.List;

public class NewsActivity extends BaseActivity<NewsPresenter, INewsView> implements INewsView{

    private RecyclerView news_recyclerView;
    private NewsRecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initView();
    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    private void initView() {
        news_recyclerView = (RecyclerView) findViewById(R.id.news_recyclerView);
        presenter.fetch();
    }

    @Override
    public void showNewsView(TitleData data) {
        adapter=new NewsRecycleViewAdapter(this,data);
        news_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        news_recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
