package com.example.mvpdemo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.example.mvpdemo.R;
import com.example.mvpdemo.constant.BundleKeyConstant;
import com.example.mvpdemo.utils.FileUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Horrarndoo on 2017/9/27.
 * <p>
 * 图片查看Activity，只有加载图片以及保存图片等简单逻辑，不采取MVP框架模式
 */

public class ImageBrowseActivity extends AppCompatActivity {
    @BindView(R.id.pv_pic)
    PhotoView pvPic;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pb_pic_browse)
    ProgressBar pbPicBrowse;
    @BindView(R.id.fab_save_pic)
    FloatingActionButton fabSavePic;

    private String mImageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_browse);
        ButterKnife.bind(this);
        initView();
    }

    @SuppressLint("NewApi")
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mImageUrl = bundle.getString(BundleKeyConstant.ARG_KEY_IMAGE_BROWSE_URL);
        }

        initBar("");
        setBarColor(this, Color.BLACK);
        pvPic.enable();
        if (mImageUrl.contains("gif")) {
            loadGif();
        }else {
            loadImage();
        }
    }
    @SuppressLint("NewApi")
    private void initBar(String title){
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 设置状态栏背景色
     * 4.4以下不处理
     * 4.4使用默认沉浸式状态栏
     *
     * @param color 要为状态栏设置的颜色值
     */
    public void setBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = activity.getWindow();
            View decorView = win.getDecorView();
            win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式状态栏(4.4-5.0透明，5
            // .0以上半透明)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//android5.0以上设置透明效果
                win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                //清除flag，为了android5.0以上也全透明效果
                //让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | option);
                win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                win.setStatusBarColor(color);//设置状态栏背景色
            }
        }
    }
    @OnClick(R.id.fab_save_pic)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_save_pic:
                saveImageToLocal(mImageUrl);
                break;
            default:
                break;
        }
    }

    /**
     * 保存图片到本地
     *
     * @param fileName 文件名
     */
    private void saveImageToLocal(final String fileName) {
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> e) throws Exception {
                e.onNext(Glide.with(ImageBrowseActivity.this)
                        .load(mImageUrl)
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get());
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<File>() {
            @Override
            public void accept(File file) throws Exception {
                saveImage(fileName, file);
            }
        });
    }

    /**
     * 加载gif
     */
    private void loadGif() {
        Glide.with(ImageBrowseActivity.this)
                .load(mImageUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new GlideDrawableImageViewTarget(pvPic) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<?
                            super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        //在这里添加一些图片加载完成的操作
                        pbPicBrowse.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * 加载静态图片
     */
    private void loadImage() {
        Glide.with(ImageBrowseActivity.this)
                .load(mImageUrl)
                .fitCenter()
                .crossFade()
                .into(new GlideDrawableImageViewTarget(pvPic) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        //在这里添加一些图片加载完成的操作
                        pbPicBrowse.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * 保存图片，并且回调提示
     *
     * @param fileName 文件名
     * @param file     文件file
     */
    private void saveImage(String fileName, File file) {
        FileUtils.saveImage(ImageBrowseActivity.this, fileName, file, new FileUtils
                .SaveResultCallback() {
            @Override
            public void onSavedSuccess() {
                Snackbar.make(fabSavePic, "保存成功", Snackbar.LENGTH_SHORT).setActionTextColor
                        (getResources().getColor(R.color.white)).show();
            }

            @Override
            public void onSavedFailed() {
                Snackbar.make(fabSavePic, "保存失败", Snackbar.LENGTH_SHORT).setActionTextColor
                        (getResources().getColor(R.color.white)).show();
            }
        });
    }
}
